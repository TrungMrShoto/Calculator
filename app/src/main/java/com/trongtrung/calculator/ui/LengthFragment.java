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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;

import com.trongtrung.calculator.GeneralArray;
import com.trongtrung.calculator.GeneralCharacter;
import com.trongtrung.calculator.KeyboardAdapter;
import com.trongtrung.calculator.R;
import com.trongtrung.calculator.converter.Converter;
import com.trongtrung.calculator.converter.LengthConverter;
import com.trongtrung.calculator.converter.Unit;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class LengthFragment extends Fragment {

    private Spinner listLengthUnitInput, listLengthUnitOutput;
    private GridView keyboard;
    private TextView inputField, outputField;
    private GeneralArray array_keyboard;
    private View root;
    private Converter converter;
    private ImageButton exchange;
    private static String input="0";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_length, container, false);

        initialize();
        createSpinner();

//        if (root.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)

        createKeyboardVertical();
        updateResult(input);

        keyboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String character = (String) keyboard.getItemAtPosition(position);
                String input = inputField.getText().toString();
                int numOfDigit = 0;
                //check zero
                if (input.equals("0")) {
                    inputField.setText("");
                }

                if (input.contains(GeneralCharacter.POINT))
                    numOfDigit+=1;


                String inputValue;

                switch (character){
                    case GeneralCharacter.SPACE:
                        return;
                    case GeneralCharacter.CE:
                        inputValue = "0";
                        input = "0";
                        break;
                    case GeneralCharacter.DEL:
                        if (input.length() > 1) input = input.substring(0,input.length()-1);
                        else
                            input = "0";
                        inputValue= input;
                        break;
                    case GeneralCharacter.POINT:
                        if (input.contains(GeneralCharacter.POINT))
                            return;
                        if (input.equals("0")) {
                            inputValue = input + character;
                            break;
                        }
                        default:
                            if (input.equals("0"))
                                input = "";
                            inputValue = input + character;
                            break;
                }
                if (input.length()-numOfDigit >= Unit.PRECISION)
                    return;

                updateResult(inputValue);
            }
        });

        listLengthUnitInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateResult(inputField.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listLengthUnitOutput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                int position = listLengthUnitInput.getSelectedItemPosition();
                listLengthUnitInput.setSelection(listLengthUnitOutput.getSelectedItemPosition());
                listLengthUnitOutput.setSelection(position);
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
        converter = new LengthConverter();
        exchange = root.findViewById(R.id.exchangeUnit);
        listLengthUnitInput = root.findViewById(R.id.input_length_unit);
        listLengthUnitOutput = root.findViewById(R.id.output_length_unit);
        keyboard = root.findViewById(R.id.length_keyboard);
        inputField = root.findViewById(R.id.input_length);
        outputField = root.findViewById(R.id.output_length);
    }

    private void createSpinner()
    {
        ArrayAdapter<CharSequence> length_adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.array_length_unit,android.R.layout.simple_spinner_item);
        length_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listLengthUnitInput.setAdapter(length_adapter);
        listLengthUnitOutput.setAdapter(length_adapter);
    }

    private void updateResult(String inputValue)
    {
        double result = converter.convert(Double.parseDouble(inputValue),
                listLengthUnitInput.getSelectedItemPosition(),
                listLengthUnitOutput.getSelectedItemPosition());
        inputField.setText(inputValue);
        input = inputValue;
        outputField.setText(formatOutput(result));
    }


    private void createKeyboardVertical()
    {
        array_keyboard = new GeneralArray();
        KeyboardAdapter adapter = new KeyboardAdapter(getActivity(), array_keyboard.getListOfConverterNoSub(),R.layout.key_layout);
        keyboard.setAdapter(adapter);
    }


}