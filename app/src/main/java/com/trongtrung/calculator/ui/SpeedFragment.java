package com.trongtrung.calculator.ui;

import android.content.res.Configuration;
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
import com.trongtrung.calculator.converter.SpeedConverter;
import com.trongtrung.calculator.converter.Unit;

import java.math.BigDecimal;
import java.math.MathContext;

public class SpeedFragment extends Fragment {

    private Spinner listSpeedUnitInput, listSpeedUnitOutput;
    private GridView keyboard;
    private TextView inputField, outputField;
    private View root;
    private Converter converter;
    private ImageButton exchange;
    private static String inputDefault ="0";
    private static int inputUnit = 0;
    private static int outputUnit = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_speed, container, false);

        initialize();
        createSpinner();
        if (root.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            createKeyboardHorizontal();
        else
            createKeyboardVertical();
        listSpeedUnitOutput.setSelection(inputUnit);
        listSpeedUnitInput.setSelection(outputUnit);
        updateResult(inputDefault);

        keyboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String character = (String) keyboard.getItemAtPosition(position);
                String input = inputField.getText().toString();
                String inputValue;
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

        listSpeedUnitInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateResult(inputField.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listSpeedUnitOutput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                int position = listSpeedUnitInput.getSelectedItemPosition();
                listSpeedUnitInput.setSelection(listSpeedUnitOutput.getSelectedItemPosition());
                listSpeedUnitOutput.setSelection(position);
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
        converter = new SpeedConverter();
        exchange = root.findViewById(R.id.exchangeUnit);
        listSpeedUnitInput = root.findViewById(R.id.input_speed_unit);
        listSpeedUnitOutput = root.findViewById(R.id.output_speed_unit);
        keyboard = root.findViewById(R.id.speed_keyboard);
        inputField = root.findViewById(R.id.input_speed);
        outputField = root.findViewById(R.id.output_speed);
    }

    private void createSpinner()
    {
        ArrayAdapter<CharSequence> speed_adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.array_speed_unit,android.R.layout.simple_spinner_item);
        speed_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listSpeedUnitInput.setAdapter(speed_adapter);
        listSpeedUnitOutput.setAdapter(speed_adapter);
    }

    private void updateResult(String inputValue)
    {
        double result = converter.convert(Double.parseDouble(inputValue),
                listSpeedUnitInput.getSelectedItemPosition(),
                listSpeedUnitOutput.getSelectedItemPosition());
        inputDefault = inputValue;
        inputField.setText(inputValue);
        outputField.setText(formatOutput(result));

        inputUnit = listSpeedUnitInput.getSelectedItemPosition();
        outputUnit = listSpeedUnitOutput.getSelectedItemPosition();
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

    private void createKeyboardVertical()
    {
        KeyboardAdapter adapter = new KeyboardAdapter(
                getActivity(),
                GeneralArray.getListOfConverterNoSub(),
                R.layout.key_layout,
                25.0f);
        keyboard.setAdapter(adapter);
    }

    private void createKeyboardHorizontal() {
        KeyboardAdapter adapter = new KeyboardAdapter(
                getActivity(),
                GeneralArray.getListOfConverterNoSub(),
                R.layout.key_layout,
                25.0f);
        keyboard.setAdapter(adapter);
    }
}