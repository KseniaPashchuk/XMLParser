package com.epam.xmlparser.type;

public enum PaperEnum {
    PAPERS("papers"),
    ENTERTAINING("entertaining"),
    SCIENCE("science"),
    TITLE("title"),
    TYPE("type"),
    COLORED("colored"),
    MONTHLY("monthly"),
    CHARS("chars"),
    GLOSSY("glossy"),
    PAGES("pages"),
    SUBSCRIBE_INDEX("subscribe-index"),
    THEME("theme");
    private String value;

    PaperEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaperEnum getEnum(String value) {
        for (PaperEnum v : values())
            if (v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
