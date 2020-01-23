package com.trongtrung.calculator;

/**
 * Created by NguyenTrongTrung on 23 January 2020
 */
public class Element {
    private String name;
    private int color;

    public Element(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}
