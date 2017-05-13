package com.epam.xmlparser.type;

public enum ScienceType {
    SPACE("space"), NATURE("nature"), ECONOMICS("economics");
    private String value;

    ScienceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static ScienceType getEnum(String value) {
        for (ScienceType v : values())
            if (v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
