package project.data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderRequest {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("petId")
    private String petId;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("shipDate")
    private Date shipDate;
    @JsonProperty("status")
    private String status;
    @JsonProperty("complete")
    private Boolean complete;

    public Long getId() {
        return id;
    }

    public String getPetId() {
        return petId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
