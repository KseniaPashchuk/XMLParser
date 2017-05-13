package com.epam.xmlparser.type;

public enum EntertainingType {
    FASHION("fashion"), CULINARY("culinary"), HOME_TIPS("home-tips");
    private String value;

    EntertainingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static EntertainingType getEnum(String value) {
        for(EntertainingType v : values())
            if(v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
