package com.trongtrung.calculator.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.trongtrung.calculator.GeneralArray;
import com.trongtrung.calculator.GeneralCharacter;
import com.trongtrung.calculator.KeyboardAdapter;
import com.trongtrung.calculator.R;
import com.trongtrung.calculator.converter.Converter;
import com.trongtrung.calculator.converter.TemperatureConverter;
import com.trongtrung.calculator.converter.Unit;

import java.math.BigDecimal;
import java.math.MathContext;

public class TemperatureFragment extends Fragment {

    private Spinner listTempUnitInput, listTempUnitOutput;
    private GridView keyboard;
    private TextView inputField, outputField;
    private GeneralArray array_keyboard;
    private View root;
    private Converter converter;
    private ImageButton exchange;
    private static String inputDefault="0";
    private static int inputUnit = 0;
    private static int outputUnit = 0;
    KeyboardAdapter adapter;

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_temp, container, false);

        initialize();
        createSpinner();

//        if (root.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)

        createKeyboardVertical();

        listTempUnitInput.setSelection(inputUnit);
        listTempUnitOutput.setSelection(outputUnit);

        updateResult(inputDefault);

        keyboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String character = (String) keyboard.getItemAtPosition(position);
                String input = inputField.getText().toString();
                String inputValue = "0";
                int numOfDigit = 0;
                //check zero
                if (input.equals("0") || input.equals("-0")) {
                    inputField.setText("");
                }

                if (input.contains(GeneralCharacter.POINT))
                    numOfDigit+=1;

                switch (character){
                    case GeneralCharacter.SPACE:
                        return;
                    case GeneralCharacter.CE:
                        inputValue = "0";
                        input = "0";
                        break;
                    case GeneralCharacter.DEL:
                        input = getStringAfterDeleting(input);
                        inputValue= input;
                        break;
                    case GeneralCharacter.ADD_AND_SUB:
                        //check negative for K temperature
                        if (listTempUnitInput.getSelectedItemPosition() == TemperatureUnitCode.K.getCode()) break;

                        if (input.charAt(0) == '-')
                            inputValue = input.substring(1);
                        else
                            inputValue = "-" + input;
                        break;
                    case GeneralCharacter.POINT:
                        if (input.contains(GeneralCharacter.POINT))
                            return;
                        if (input.equals("0") || input.equals("-0")) {
                            inputValue = input + character;
                            break;
                        }
                    default:
                        if (input.equals("0"))
                            input = "";
                        else if (input.equals("-0"))
                            input = "-";

                        if (checkMinimumTemperature(input+character))
                            return;
                        inputValue = input + character;
                        break;
                }

                if (inputValue.contains("-"))
                    numOfDigit+=1;
                if (input.length()-numOfDigit >= Unit.PRECISION)
                    return;

                updateResult(inputValue);
            }
        });

        listTempUnitInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateResult(inputField.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listTempUnitOutput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateResult(inputField.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = listTempUnitOutput.getSelectedItemPosition();
                double outputTemp = Double.valueOf(inputField.getText().toString());
                if (position == TemperatureUnitCode.K.getCode() && outputTemp < 0)
                    inputField.setText("0");
                else if (position == TemperatureUnitCode.C.getCode() && outputTemp < Unit.CELSIUS_MIN)
                    inputField.setText(String.valueOf(Unit.CELSIUS_MIN));
                else
                    if (outputTemp <Unit.FAHRENHEIT_MIN)
                        inputField.setText(String.valueOf(Unit.FAHRENHEIT_MIN));

                position = listTempUnitInput.getSelectedItemPosition();
                listTempUnitInput.setSelection(listTempUnitOutput.getSelectedItemPosition());
                listTempUnitOutput.setSelection(position);
                updateResult(inputField.getText().toString());
            }
        });
        return root;
    }

    private String formatOutput(double value)
    {

//        return String.format("%s",value);

        String result = (new BigDecimal(value,  new MathContext(Unit.PRECISION))).toString();
        return Formater.formatString(result);
        //return result;
    }

    private void initialize()
    {
        converter = new TemperatureConverter();
        exchange = root.findViewById(R.id.exchangeUnit);
        listTempUnitInput = root.findViewById(R.id.input_temp_unit);
        listTempUnitOutput = root.findViewById(R.id.output_temp_unit);
        keyboard = root.findViewById(R.id.temp_keyboard);
        inputField = root.findViewById(R.id.input_temp);
        outputField = root.findViewById(R.id.output_temp);
    }

    private void createSpinner()
    {
        ArrayAdapter<CharSequence> temp_adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.array_temperature_unit,android.R.layout.simple_spinner_item);
        temp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listTempUnitInput.setAdapter(temp_adapter);
        listTempUnitOutput.setAdapter(temp_adapter);
    }

    private void updateResult(String inputValue)
    {
        double result = converter.convert(Double.parseDouble(inputValue),
                listTempUnitInput.getSelectedItemPosition(),
                listTempUnitOutput.getSelectedItemPosition());
        inputField.setText(inputValue);
        inputDefault = inputValue;
        outputField.setText(formatOutput(result));

        inputUnit = listTempUnitInput.getSelectedItemPosition();
        outputUnit = listTempUnitOutput.getSelectedItemPosition();
    }


    private void createKeyboardVertical()
    {
        array_keyboard = new GeneralArray();
        adapter = new KeyboardAdapter(getActivity(), array_keyboard.getListOfConverterSub(),R.layout.key_layout);
        keyboard.setAdapter(adapter);
    }



    private String getStringAfterDeleting(String input)
    {
        if (input.charAt(0) == '-') {
            if (input.equals("-0"))
                input = "0";
            else if (input.length() > 2)
                input = input.substring(0, input.length() - 1);
            else
                input = "-0";
        }
        else
        {
            if (input.length() > 1) input = input.substring(0,input.length()-1);
            else
                input = "0";
        }

        return input;
    }


    private boolean checkMinimumTemperature(String input)
    {
        int unitPosition = listTempUnitInput.getSelectedItemPosition();
        if (unitPosition == TemperatureUnitCode.C.getCode())
        {
            if (Double.valueOf(input) < Unit.CELSIUS_MIN) return true;
        }
        else if (unitPosition == TemperatureUnitCode.F.getCode())
        {
            if (Double.valueOf(input) < Unit.FAHRENHEIT_MIN) return true;
        }
        return false;
    }
}