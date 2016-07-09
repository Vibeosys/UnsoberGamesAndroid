package com.unsober.data;

/**
 * Created by akshay on 07-07-2016.
 */
public enum ParentCategory {


    Games(1), Cocktails(2), Cures(3);
    private final int value;

    ParentCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
