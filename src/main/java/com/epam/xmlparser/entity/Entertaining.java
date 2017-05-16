package com.epam.xmlparser.entity;

import com.epam.xmlparser.type.EntertainingType;

public class Entertaining extends Paper {

    private EntertainingType theme;

    public Entertaining() {
        theme = EntertainingType.FASHION;
    }


    public EntertainingType getTheme() {
        return theme;
    }

    public void setTheme(EntertainingType theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "Entertaining{" + super.toString()+
                ", theme=" + theme +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Entertaining that = (Entertaining) o;

        return theme == that.theme;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        return result;
    }
}
