package project.data;

public class CustomerAddress {

    private final String city;
    private final String postCode;
    private final String street;

    public CustomerAddress(Builder builder) {
        this.city = builder.city;
        this.postCode = builder.postCode;
        this.street = builder.street;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getStreet() {
        return street;
    }

    public static class Builder {

        private String city;
        private String postCode;
        private String street;

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder postCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public CustomerAddress build() {
            return new CustomerAddress(this);
        }
    }
}
