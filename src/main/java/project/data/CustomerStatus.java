package project.data;

import lombok.Data;

@Data
public class CustomerStatus {
  private String value;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
