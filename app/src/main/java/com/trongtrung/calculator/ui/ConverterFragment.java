package com.trongtrung.calculator.ui;

import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trongtrung.calculator.ConverterItemSpinner;
import com.trongtrung.calculator.ConverterSpinnerAdapter;
import com.trongtrung.calculator.Element;
import com.trongtrung.calculator.GeneralArray;
import com.trongtrung.calculator.GeneralCharacter;
import com.trongtrung.calculator.KeyboardAdapter;
import com.trongtrung.calculator.R;
import com.trongtrung.calculator.converter.Converter;
import com.trongtrung.calculator.converter.Unit;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

/**
 * Created by NguyenTrongTrung on 29 January 2020
 */
public class ConverterFragment extends Fragment {
    private View root;
    private Spinner listUnitInput, listUnitOutput, listConverter;
    private TextView inputField, outputField;
    private GridView keyboard;
    private List<Converter> converter;
    private List<ConverterItemSpinner> listConverterItem;
    private ImageButton exchangeButton;
    private ConverterBuilder builder;
    private static String inputDefault = "0";
    private static int converterItemChoice = 0;
    private static int unitItemInputChoice = 0;
    private static int unitItemOutputChoice = 0;
    private static Display display;
    private static Point size;

    private enum TemperatureUnitCode{
        C("C"),F("F"),K("K");
        private String unitCode;
        TemperatureUnitCode(String unitCode)
        {
            this.unitCode = unitCode;
        }
        public  String getCode()
        {
            return this.unitCode;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_converter, container, false);

        initialize();

        initializeSpinner();

        if (root.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            initializeKeyboardHorizontal(size.y * 14.5f / (1080));
        else
            initializeKeyboardVertical(size.y * 20f / 1776);

        updateResult(inputDefault);

        listUnitInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateResult(inputField.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        listUnitOutput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateResult(inputField.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listConverter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != converterItemChoice){
                    converterItemChoice = position;
                    unitItemInputChoice = 0;
                    unitItemOutputChoice = 0;
                    inputDefault = "0";
                }
                initializeUnitSpinner();
                updateResult(inputDefault);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        exchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTemperatureConverter()){
                    String outputValue = String.valueOf(listUnitOutput.getSelectedItem());
                    double inputTemp = Double.valueOf(inputField.getText().toString());
                    if (outputValue.contains(TemperatureUnitCode.K.getCode()) && inputTemp < 0)
                        inputField.setText(getString(R.string.default_value));
                    else if (outputValue.contains(TemperatureUnitCode.C.getCode()) && inputTemp < Unit.CELSIUS_MIN)
                        inputField.setText(String.valueOf(Unit.CELSIUS_MIN));
                    else
                    if (inputTemp <Unit.FAHRENHEIT_MIN)
                        inputField.setText(String.valueOf(Unit.FAHRENHEIT_MIN));
                }
                int position = listUnitInput.getSelectedItemPosition();
                listUnitInput.setSelection(listUnitOutput.getSelectedItemPosition());
                listUnitOutput.setSelection(position);
                updateResult(inputField.getText().toString());
            }
        });

        keyboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Element item = (Element) keyboard.getItemAtPosition(position);
                String character = item.getName();
                String input = inputField.getText().toString();
                String inputValue;
                int numOfDigit = 0;

                if (input.contains(GeneralCharacter.POINT))
                    numOfDigit+=1;

                //check zero
                if (input.equals("0") || input.equals("-0")) {
                    inputField.setText("");
                }

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
                        if (isTemperatureConverter())
                        {
                            ConverterItemSpinner itemConverter = (ConverterItemSpinner) listConverter.getSelectedItem();
                            if(itemConverter.getItemName().contains(TemperatureUnitCode.K.getCode()) ||
                                    (input.charAt(0) != '-' && checkMinimumTemperature("-"+input)))
                            {
                                inputValue = input;
                                break;
                            }
                        }
                        else {
                            inputValue = input;
                            break;
                        }
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
                        if (isTemperatureConverter() && checkMinimumTemperature(input+character))
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


        return root;
    }



    private void initialize() {
        if (builder==null)
            builder = new ConverterBuilder(getActivity());
        if (display == null)
            display = getActivity().getWindowManager().getDefaultDisplay();
        if (size == null)
            size = new Point();
        display.getSize(size);
        keyboard = root.findViewById(R.id.converter_keyboard);
        converter = builder.getListConverter();
        listConverterItem = builder.getListConverterItemSpinner();
        listConverter = root.findViewById(R.id.converter_choice);
        inputField = root.findViewById(R.id.input_converter);
        listUnitInput = root.findViewById(R.id.input_converter_unit);
        exchangeButton = root.findViewById(R.id.exchangeUnit);
        outputField = root.findViewById(R.id.output_converter);
        listUnitOutput = root.findViewById(R.id.output_converter_unit);

    }

    private void initializeSpinner(){

        ConverterSpinnerAdapter adapter = new ConverterSpinnerAdapter(
                getActivity(), R.layout.custom_converter_spinner, listConverterItem);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.array_converter, android.R.layout.simple_spinner_item);
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listConverter.setAdapter(adapter);
        listConverter.setSelection(converterItemChoice);
        initializeUnitSpinner();
    }

    private void initializeUnitSpinner()
    {

        ConverterItemSpinner itemConverter = (ConverterItemSpinner) listConverter.getItemAtPosition(converterItemChoice);
        int converterPosition = builder.getConverterLayout(itemConverter.getItemName());
        System.out.println(converterPosition);
        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(getActivity(),
                converterPosition,
                android.R.layout.simple_spinner_item);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listUnitOutput.setAdapter(unitAdapter);
        listUnitInput.setAdapter(unitAdapter);
        listUnitInput.setSelection(unitItemInputChoice);
        listUnitOutput.setSelection(unitItemOutputChoice);

    }

    private void initializeKeyboardVertical(float textSize)
    {
        KeyboardAdapter adapter = new KeyboardAdapter(getActivity(),
                GeneralArray.getListOfConverterSub(), R.layout.key_layout, textSize);
        keyboard.setAdapter(adapter);
    }

    private void initializeKeyboardHorizontal(float textSize)
    {
        KeyboardAdapter adapter = new KeyboardAdapter(getActivity(),
                GeneralArray.getListOfConverterSubHorizontal(), R.layout.key_layout, textSize);
        keyboard.setAdapter(adapter);
    }

    private void updateResult(String inputValue) {
        double result = converter.get(converterItemChoice).convert(Double.parseDouble(inputValue),
                listUnitInput.getSelectedItemPosition(),
                listUnitOutput.getSelectedItemPosition());
        inputField.setText(inputValue);
        inputDefault = inputValue;
        outputField.setText(formatOutput(result));

        unitItemInputChoice = listUnitInput.getSelectedItemPosition();
        unitItemOutputChoice = listUnitOutput.getSelectedItemPosition();
        converterItemChoice = listConverter.getSelectedItemPosition();

    }

    private String formatOutput(double value)
    {
        String result = (new BigDecimal(value,  new MathContext(Unit.PRECISION))).toString();
        return Formater.formatString(result);
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

    private boolean isTemperatureConverter()
    {
        ConverterItemSpinner itemConverter = (ConverterItemSpinner) listConverter.getSelectedItem();
        return itemConverter.getItemName().equals(getString(R.string.menu_temp));
    }

    private boolean checkMinimumTemperature(String input)
    {
        String unitPosition = String.valueOf(listUnitInput.getSelectedItem());
        if (unitPosition.contains(TemperatureUnitCode.C.getCode()))
        {
            return Double.valueOf(input) < Unit.CELSIUS_MIN;
        }
        else if (unitPosition.contains(TemperatureUnitCode.F.getCode()))
        {
            return Double.valueOf(input) < Unit.FAHRENHEIT_MIN;
        }
        return false;
    }

//    private int getNavigationBarHeight(int orientation) {
////        Resources resources = root.getResources();
////
////        int id = resources.getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape",
////                "dimen", "android");
////        if (id > 0) {
////            return resources.getDimensionPixelSize(id);
////        }
////        return 0;
////    }
}
