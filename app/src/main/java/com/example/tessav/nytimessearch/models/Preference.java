package com.example.tessav.nytimessearch.models;

/**
 * Created by tessavoon on 9/25/17.
 */

public class Preference {
    int beginYear;

    public int getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(int beginYear) {
        this.beginYear = beginYear;
    }

    public int getBeginMonth() {
        return beginMonth;
    }

    public void setBeginMonth(int beginMonth) {
        this.beginMonth = beginMonth;
    }

    public int getBeginDay() {
        return beginDay;
    }

    public void setBeginDay(int beginDay) {
        this.beginDay = beginDay;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isArt() {
        return isArt;
    }

    public void setArt(boolean art) {
        isArt = art;
    }

    public boolean isSports() {
        return isSports;
    }

    public void setSports(boolean sports) {
        isSports = sports;
    }

    public boolean isFashion() {
        return isFashion;
    }

    public void setFashion(boolean fashion) {
        isFashion = fashion;
    }

    int beginMonth;
    int beginDay;
    String sortOrder;
    boolean isArt;
    boolean isSports;
    boolean isFashion;

    public Preference() { }
}
