package test.reader.model;

/**
 * @author wangwei
 * @since 2022/8/8
 */
public class Demo {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Demo{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}
