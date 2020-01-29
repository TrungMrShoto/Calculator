package com.trongtrung.calculator.converter;

/**
 * Created by NguyenTrongTrung on 21 January 2020
 */
public class WeightConverter implements Converter{
    private final int BORDER_SI_US = 3;
    private enum WeightUnitcode
    {
        POUND(5),STONE(6);
        private int unitCode;
        WeightUnitcode(int unitCode) {
            this.unitCode = unitCode;
        }

        public int getCode() {
            return this.unitCode;
        }
    }
    private double listOfUnit[] =
            {
                    Unit.MILLI, Unit.BASE, Unit.KILO, Unit.MEGA,
                    Unit.OUNCE_TO_GRAM, Unit.POUND_TO_GRAM, Unit.STONE_TO_GRAM

            };

    public WeightConverter(){}

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

            if (inputUnit==WeightUnitcode.STONE.getCode())
            {
                if (outputUnit == WeightUnitcode.POUND.getCode())
                    return inputValue*Unit.STONE_TO_POUND;
                return inputValue*Unit.STONE_TO_POUND*Unit.POUND_TO_OUNCE;
            }

            return inputValue*Unit.POUND_TO_OUNCE;

        }
    }

    private double convertStandardUnit(double inputValue,double inputUnit, double outputUnit)
    {
        return inputValue*inputUnit/outputUnit;
    }
}
