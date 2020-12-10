package project.data;

public enum Attribute {

    ID("id"),
    NAME("name");

    private String value;

    Attribute(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
