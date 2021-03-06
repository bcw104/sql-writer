package co.mewf.sqlwriter.builders;

import co.mewf.sqlwriter.dialects.Dialect;
import co.mewf.sqlwriter.mapping.ColumnInfo;



public class QualifierBuilder {

  private final Object builder;
  private final StringBuilder order = new StringBuilder();
  private int limit = -1;
  private int offset = -1;
  private final Dialect dialect;

  static enum Order {
    ASC, DESC;
  }

  QualifierBuilder(Object builder, Dialect dialect) {
    this.builder = builder;
    this.dialect = dialect;
  }

  public boolean hasLimit() {
    return limit > -1;
  }

  public int getLimit() {
    return limit;
  }

  QualifierBuilder limit(int limit) {
    this.limit = limit;
    return this;
  }

  void offset(int offset) {
    this.offset = offset;
  }

  void orderBy(ColumnInfo column, QualifierBuilder.Order sortOrder) {
    if (order.length() == 0) {
      order.append(" ORDER BY ");
    } else {
      order.append(", ");
    }
    order.append(column).append(" ").append(sortOrder);
  }

  @Override
  public String toString() {
    return dialect.qualify(order.toString(), limit, offset);
  }
}
