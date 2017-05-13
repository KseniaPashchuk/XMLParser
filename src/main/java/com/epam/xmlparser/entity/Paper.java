package com.epam.xmlparser.entity;

import com.epam.xmlparser.type.PaperType;

public abstract class Paper {
    private PaperType type;
    private String title;
    private boolean isColored;
    private boolean isMonthly;
    protected Chars chars;

    public PaperType getType() {
        return type;
    }

    public void setType(PaperType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isColored() {
        return isColored;
    }

    public void setColored(boolean colored) {
        isColored = colored;
    }

    public boolean isMonthly() {
        return isMonthly;
    }

    public void setMonthly(boolean monthly) {
        isMonthly = monthly;
    }

    public Chars getChars() {
        return chars;
    }

    public void setChars(Chars chars) {
        this.chars = chars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper paper = (Paper) o;

        if (isColored != paper.isColored) return false;
        if (isMonthly != paper.isMonthly) return false;
        if (type != paper.type) return false;
        if (title != null ? !title.equals(paper.title) : paper.title != null) return false;
        return chars != null ? chars.equals(paper.chars) : paper.chars == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isColored ? 1 : 0);
        result = 31 * result + (isMonthly ? 1 : 0);
        result = 31 * result + (chars != null ? chars.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", isColored=" + isColored +
                ", isMonthly=" + isMonthly +
                ", chars=" + chars +
                '}';
    }
}
