package com.trongtrung.calculator.converter;
/**
 * Created by NguyenTrongTrung on 21 January 2020
 */
public class SpeedConverter implements Converter{
    private enum SpeedUnitCode{
        mps(0), kmph(1), knot(2), mph(3), ftps(4);
        private int unitCode;
        SpeedUnitCode(int unitCode){ this.unitCode = unitCode;}
        public int getCode() {return this.unitCode;}
    }

    public double convert(double inputValue, int inputUnit, int outputUnit)
    {
        if (inputUnit == outputUnit) return inputValue;
        if (inputUnit<outputUnit)
        {
             return inputValue*1.0/convert(1.0,outputUnit,inputUnit);
        }
        else
        {
            if (inputUnit == SpeedUnitCode.ftps.getCode())
            {
                if(outputUnit == SpeedUnitCode.mps.getCode())
                    return inputValue*Unit.FTPS_TO_MPS;
                if(outputUnit == SpeedUnitCode.kmph.getCode())
                    return inputValue*Unit.FTPS_TO_KMPH;
                if (outputUnit == SpeedUnitCode.mph.getCode())
                    return inputValue*Unit.HOUR_TO_SECOND/Unit.MILE_TO_FEET;
                return inputValue*Unit.HOUR_TO_SECOND*Unit.FEET_TO_METER/Unit.NMI_TO_METER;

            }
            if (inputUnit == SpeedUnitCode.knot.getCode())
            {
                if (outputUnit == SpeedUnitCode.mps.getCode())
                    return inputValue*Unit.NMI_TO_METER/Unit.HOUR_TO_SECOND;
                if (outputUnit == SpeedUnitCode.kmph.getCode())
                    return inputValue*Unit.KNOT_TO_KMPH;
                return inputValue*Unit.NMI_TO_METER/Unit.MILE_TO_METER;
            }
            if (inputUnit == SpeedUnitCode.mph.getCode())
            {
                if (outputUnit == SpeedUnitCode.mps.getCode())
                    return inputValue*Unit.MPH_TO_MPS;
                return inputValue*Unit.MPS_TO_KMPH;
            }

            return inputValue*1.0/Unit.MPS_TO_KMPH;

        }

    }


}
