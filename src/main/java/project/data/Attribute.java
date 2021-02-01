package project.data;

public enum Attribute {

    REPLACE("replace"),
    ADD("add");

    private String value;

    Attribute(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
