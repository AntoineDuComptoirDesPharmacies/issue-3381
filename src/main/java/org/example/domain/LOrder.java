package org.example.domain;

import io.ebean.Model;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class LOrder extends Model {

  @Id
  long id;


  private String dirty;

  private BigDecimal total;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
  @Column(nullable = true)
  private List<LOrderLine> orderLines;

  @Version
  long version;

  public LOrder(String dirty) {
    this.dirty = dirty;
  }

  /**
   * Only used to demonstrate "stateless update".
   */
  public LOrder() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDirty() {
    return dirty;
  }

  public void setDirty(String dirty) {
    this.dirty = dirty;
  }

  public List<LOrderLine> getOrderLines() {
    return orderLines;
  }

  public void setOrderLines(List<LOrderLine> orderLines) {
    this.orderLines = orderLines;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

}
