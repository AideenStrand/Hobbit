package project.data;

import lombok.Data;

@Data
public class ResponseJson {

    private String CompleteName;
    private CostumerInformation costumerInformation;
    private CustomerAddress customerAddress;

    public void setCompleteName(String completeName) {
        CompleteName = completeName;
    }

    public void setCostumerInformation(CostumerInformation costumerInformation) {
        this.costumerInformation = costumerInformation;
    }

    public String getCompleteName() {
        return CompleteName;
    }

    public CostumerInformation getCostumerInformation() {
        return costumerInformation;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }
}


