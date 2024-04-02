package org.example.domain;

import io.ebean.DB;
import io.ebean.Transaction;
import io.ebean.UpdateQuery;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class UpdateTest {


  @Test
  public void test() {
    // Produce 5 order with the exact same content

    LOrder o1 = new LOrder();
    o1.setOrderLines(Arrays.asList(
      new LOrderLine(BigDecimal.valueOf(10)),
      new LOrderLine(BigDecimal.valueOf(10)),
      new LOrderLine(BigDecimal.valueOf(10))
    ));
    o1.setDirty("abc");

    LOrder o2 = new LOrder();
    o2.setOrderLines(Arrays.asList(
      new LOrderLine(BigDecimal.valueOf(10)),
      new LOrderLine(BigDecimal.valueOf(10)),
      new LOrderLine(BigDecimal.valueOf(10))
    ));
    o2.setDirty("abc");

    LOrder o3 = new LOrder();
    o3.setOrderLines(Arrays.asList(
      new LOrderLine(BigDecimal.valueOf(10)),
      new LOrderLine(BigDecimal.valueOf(10)),
      new LOrderLine(BigDecimal.valueOf(10))
    ));
    o3.setDirty("abc");


    LOrder o4 = new LOrder();
    o4.setOrderLines(Arrays.asList(
      new LOrderLine(BigDecimal.valueOf(10)),
      new LOrderLine(BigDecimal.valueOf(10)),
      new LOrderLine(BigDecimal.valueOf(10))
    ));
    o4.setDirty("abc");

    LOrder o5 = new LOrder();
    o5.setOrderLines(Arrays.asList(
      new LOrderLine(BigDecimal.valueOf(10)),
      new LOrderLine(BigDecimal.valueOf(10)),
      new LOrderLine(BigDecimal.valueOf(10))
    ));
    o5.setDirty("abc");

    DB.saveAll(Arrays.asList(o1, o2, o3, o4, o5));

    // Compute the sum of total for each order

    List<LOrder> elementsToProcess;
    try (Transaction transaction = DB.beginTransaction()) {
      elementsToProcess = DB.find(LOrder.class)
        .setLazyLoadBatchSize(3)
        .where()
        .isNotNull("dirty")
        .setMaxRows(15)
        .findList();

      for (LOrder lorder: elementsToProcess) {
        UpdateQuery query = DB.update(LOrder.class);
        BigDecimal totalComputed = lorder.getOrderLines()
          .stream()
          .map(LOrderLine::getTotal)
          .reduce(BigDecimal.ZERO, BigDecimal::add);

        query.set("dirty", null)
          .set("total", totalComputed);

        query.where()
          .eq("id", lorder.getId())
          .eq("dirty", lorder.getDirty())
          .update();

        System.out.println("Total : " + totalComputed);

        Assert.assertTrue(totalComputed.compareTo(BigDecimal.valueOf(30)) == 0);
      }

      transaction.commit();
    }
  }
}
