package com.trongtrung.calculator.converter;

/**
 * Created by NguyenTrongTrung on 20 January 2020
 */

public class LengthConverter implements Converter{

    private final int BORDER_SI_US = 6;
    private enum LengthUnitCode
    {
        INCHES(7), FEET(8), YARDS(9);
        private int unitCode;
        LengthUnitCode(int unitCode) {
            this.unitCode = unitCode;
        }

        public int getCode() {
            return this.unitCode;
        }
    }
    private double listOfUnit[] =
            {
                    Unit.NANO, Unit.MICRO, Unit.MILLI,
                    Unit.CENTI, Unit.BASE, Unit.KILO,
                    Unit.NMI_TO_METER, Unit.INCH_TO_METER, Unit.FEET_TO_METER,
                    Unit.YARD_TO_METER, Unit.MILE_TO_METER
            };
    public double convert(double inputValue, int inputUnit, int outputUnit)
    {
        if(inputUnit == outputUnit)
        {
            return inputValue;
        }

        if (inputUnit < outputUnit)
        {
            return inputValue*1.0/convert(1.0,outputUnit,inputUnit);
        }
        else
        {
            if (outputUnit<=BORDER_SI_US)
            {
                return convertStandardUnit(inputValue,listOfUnit[inputUnit], listOfUnit[outputUnit]);
            }

            if (inputUnit<=BORDER_SI_US)
            {
                return convertStandardUnit(inputValue*listOfUnit[inputUnit],Unit.BASE,listOfUnit[outputUnit]);
            }

            //convert feet to inches
            if (inputUnit == LengthUnitCode.FEET.getCode()
                    && outputUnit == LengthUnitCode.INCHES.getCode())
                return inputValue*Unit.FEET_TO_INCH;

            //convert yard to ...
            if (inputUnit == LengthUnitCode.YARDS.getCode())
            {
                //... inches
                if (outputUnit == LengthUnitCode.INCHES.getCode()) return inputValue*Unit.YARD_TO_INCH;

                //... feet
                return inputValue*Unit.YARD_TO_FEET;
            }

            //convert mile to...
            //... inches
            if (inputUnit == LengthUnitCode.INCHES.getCode()) return inputValue*Unit.MILE_TO_INCH;
            //... feet
            if (inputUnit == LengthUnitCode.FEET.getCode()) return inputValue*Unit.MILE_TO_FEET;
            //... yard
            return inputValue*Unit.MILE_TO_YARD;
        }
    }

    private double convertStandardUnit(double inputValue,double inputUnit, double outputUnit)
    {
        return inputValue*inputUnit/outputUnit;
    }
}