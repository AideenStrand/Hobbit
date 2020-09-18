package project.data;

public class CostumerInformation {

    private String name;
    private String family;
    private String personalId;

    public CostumerInformation(MyBuilder builder) {
        this.name = builder.name;
        this.family = builder.family;
        this.personalId = builder.personalId;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getPersonalId() {
        return personalId;
    }

    public static class MyBuilder {

        private String name;
        private String family;
        private String personalId;

        public MyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MyBuilder family(String family) {
            this.family = family;
            return this;
        }

        public MyBuilder personalId(String personalId) {
            this.personalId = personalId;
            return this;
        }

        public CostumerInformation myBuild() {
            CostumerInformation costumerInformation = new CostumerInformation(this);
            return costumerInformation;
        }

    }

}
