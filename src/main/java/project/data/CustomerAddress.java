package project.data;

public class CustomerAddress {

    private final String city;
    private final String postCode;
    private final String country;

    public CustomerAddress(Builder builder) {
        this.city = builder.city;
        this.postCode = builder.postCode;
        this.country = builder.country;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }

    public static class Builder {

        private String city;
        private String postCode;
        private String country;

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder postCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public CustomerAddress build() {
            return new CustomerAddress(this);
        }
    }
}
