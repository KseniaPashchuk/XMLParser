package com.epam.xmlparser.entity;

public class Chars {
    private boolean isGlossy;
    private int pageNumber;
    private int subscribeIndex;

    public boolean isGlossy() {
        return isGlossy;
    }

    public void setGlossy(boolean glossy) {
        isGlossy = glossy;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getSubscribeIndex() {
        return subscribeIndex;
    }

    public void setSubscribeIndex(int subscribeIndex) {
        this.subscribeIndex = subscribeIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chars chars = (Chars) o;

        if (isGlossy != chars.isGlossy) return false;
        if (pageNumber != chars.pageNumber) return false;
        return subscribeIndex == chars.subscribeIndex;

    }

    @Override
    public int hashCode() {
        int result = (isGlossy ? 1 : 0);
        result = 31 * result + pageNumber;
        result = 31 * result + subscribeIndex;
        return result;
    }

    @Override
    public String toString() {
        return
                "isGlossy=" + isGlossy +
                ", pageNumber=" + pageNumber +
                ", subscribeIndex=" + subscribeIndex;
    }
}
