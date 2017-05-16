package com.epam.xmlparser.entity;

import com.epam.xmlparser.type.ScienceType;

public class Science extends Paper {
    private ScienceType theme;

    public Science() {
        theme = ScienceType.NATURE;
    }

    public ScienceType getTheme() {
        return theme;
    }

    public void setTheme(ScienceType theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "Science{" + super.toString()+
                ", theme=" + theme +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Science science = (Science) o;

        return theme == science.theme;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        return result;
    }
}
