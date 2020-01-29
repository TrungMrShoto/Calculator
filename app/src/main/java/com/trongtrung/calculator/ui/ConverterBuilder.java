package com.trongtrung.calculator.ui;

import android.content.Context;

import com.trongtrung.calculator.R;
import com.trongtrung.calculator.converter.Converter;
import com.trongtrung.calculator.converter.LengthConverter;
import com.trongtrung.calculator.converter.NullConverter;
import com.trongtrung.calculator.converter.SpeedConverter;
import com.trongtrung.calculator.converter.TemperatureConverter;
import com.trongtrung.calculator.converter.WeightConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by NguyenTrongTrung on 29 January 2020
 */
public class ConverterBuilder {
    private List<String> listConverterString;
    private Context context;

    public ConverterBuilder(Context context) {
        this.context = context;
        listConverterString = Arrays.asList(context.getResources().getStringArray(R.array.array_converter));
    }

    public int getConverterItem(String input)
    {
        int position =  listConverterString.indexOf(input);
        return position;
    }

    public List<Converter> getListConverter()
    {
        List<Converter> list = new ArrayList<>();
        for(String str : listConverterString)
        {
            list.add(this.getConverter(str));
        }
        return list;
    }

    private Converter getConverter(String input)
    {
        if (input.equals(context.getString(R.string.menu_length)))
            return new LengthConverter();
        else if (input.equals(context.getString(R.string.menu_weight)))
            return new WeightConverter();
        else if (input.equals(context.getString(R.string.menu_temp)))
            return new TemperatureConverter();
        else if (input.equals(context.getString(R.string.menu_speed)))
            return new SpeedConverter();
        else
            return new NullConverter();
    }

    public int getConverterLayout(String input)
    {
        if (input.equals("Length"))
            return R.array.array_length_unit;
        else if (input.equals("Weight"))
            return R.array.array_weight_unit;
        else if (input.equals("Temperature"))
            return R.array.array_temperature_unit;
        else if (input.equals("Speed"))
            return R.array.array_speed_unit;
        else
            return R.array.array_null;
    }
}
