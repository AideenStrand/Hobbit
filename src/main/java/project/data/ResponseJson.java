package project.data;

import lombok.Data;

@Data
public class ResponseJson {

    private String CompleteName;
    private CostumerInformation costumerInformation;

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
}


