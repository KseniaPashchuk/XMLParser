package com.epam.xmlparser.type;

public enum PaperType {
    BOOK("book"), MAGAZINE("magazine"), BOOKLET("booklet");
    private String value;

    PaperType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static PaperType getEnum(String value) {
        for (PaperType v : values())
            if (v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
