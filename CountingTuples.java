package com.company;

public class CountingTuples {

    private String value;
    private int count_In_s1;
    private int count_In_s2;

    public CountingTuples(String value) {
        this.value = value;
        this.count_In_s1 = 0;
        this.count_In_s2 = 0;
    }

    public String getValue() {
        return value;

    }


    public int getCount_In_s1() {
        return count_In_s1;
    }

    public void setCount_In_s1(int count_In_s1) {
        this.count_In_s1 = count_In_s1;
    }

    public int getCount_In_s2() {
        return count_In_s2;
    }

    public void setCount_In_s2(int count_In_s2) {
        this.count_In_s2 = count_In_s2;
    }
}
