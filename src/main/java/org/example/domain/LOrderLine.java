package org.example.domain;

import io.ebean.Model;
import javax.persistence.*;

import java.math.BigDecimal;

@Entity
public class LOrderLine extends Model {

  @Id
  long id;


  private BigDecimal total;

  @ManyToOne
  @Column(nullable = true)
  private LOrder order;

  @Version
  long version;


  /**
   * Only used to demonstrate "stateless update".
   */
  public LOrderLine(BigDecimal total) {
    this.setTotal(total);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
