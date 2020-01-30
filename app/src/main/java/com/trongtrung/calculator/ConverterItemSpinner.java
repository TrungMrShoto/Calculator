package com.trongtrung.calculator;

import android.graphics.drawable.Drawable;

/**
 * Created by NguyenTrongTrung on 30 January 2020
 */
public class ConverterItemSpinner {
    private String itemName;
    private int image;

    public ConverterItemSpinner(String itemName, int image) {
        this.itemName = itemName;
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public int getImage() {
        return image;
    }
}
