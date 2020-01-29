package com.trongtrung.calculator.converter;

/**
 * Created by NguyenTrongTrung on 29 January 2020
 */
public class NullConverter implements Converter {
    public NullConverter(){}
    @Override
    public double convert(double inputValue, int inputUnit, int outputUnit) {
        return 0.0;
    }
}
