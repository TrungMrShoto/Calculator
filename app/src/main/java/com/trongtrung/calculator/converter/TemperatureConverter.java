package com.trongtrung.calculator.converter;

/**
 * Created by NguyenTrongTrung on 20 January 2020
 */
public class TemperatureConverter implements Converter{

    private enum TemperatureUnitCode{
        C(0),F(1),K(2);
        private int unitCode;
        TemperatureUnitCode(int unitCode)
        {
            this.unitCode = unitCode;
        }
        public  int getCode()
        {
            return this.unitCode;
        }
    }
    public TemperatureConverter(){}
    public double convert(double inputValue, int inputUnit, int outputUnit)
    {
        if (inputUnit == outputUnit)
            return inputValue;

        if (inputUnit < outputUnit)
        {
            if (inputUnit == TemperatureUnitCode.C.getCode())
            {
                if (outputUnit == TemperatureUnitCode.F.getCode()) return convertCelsiusToFahrenheit(inputValue);

                return convertCelsiusToKelvin(inputValue);
            }

            return convertFahrenheitToKelvin(inputValue);
        }
        else
        {

            if (inputUnit==TemperatureUnitCode.K.getCode())
            {
                if (outputUnit == TemperatureUnitCode.C.getCode()) return convertKelvinToCelsius(inputValue);

                return convertKelvinToFahrenheit(inputValue);
            }

            return convertFahrenheitToCelsius(inputValue);
        }
    }

    private double convertCelsiusToFahrenheit(double inputValue)
    {
        return inputValue*9.0/5.0 + 32.0;
    }

    private double convertCelsiusToKelvin(double inputValue)
    {
        return inputValue + 273.15;
    }

    private double convertFahrenheitToCelsius(double inputValue)
    {
        return (inputValue-32.0)*5.0/9.0;
    }

    private double convertFahrenheitToKelvin(double inputValue)
    {
        return (inputValue+459.67)*5.0/9.0;
    }

    private double convertKelvinToFahrenheit(double inputValue)
    {
        return inputValue*9.0/5.0-459.67;
    }

    private double convertKelvinToCelsius(double inputValue)
    {
        return inputValue - 273.15;
    }
}
