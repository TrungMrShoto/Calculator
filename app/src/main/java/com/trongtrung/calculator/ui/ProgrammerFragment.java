package com.trongtrung.calculator.ui;

import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.trongtrung.calculator.Element;
import com.trongtrung.calculator.GeneralArray;
import com.trongtrung.calculator.GeneralCharacter;
import com.trongtrung.calculator.KeyboardAdapter;
import com.trongtrung.calculator.R;

public class ProgrammerFragment extends Fragment {

    private enum Base {
        BIN(3,2), OCT(2,8), DEC(1,10), HEX(0,16);
        private int baseCode;
        private int radix;
        Base(int baseCode, int radix){this.baseCode = baseCode; this.radix = radix;}
        public int getCode(){ return this.baseCode;}
        public int getRadix() {return  this.radix;}
    }

    private View root;
    private GridView keyboard;
    private TextView inputField, hexResult, decResult, octResult, binResult, inputExpression;
    private RadioButton hexRadioButton, decRadioButton, octRadioButton, binRadioButton;
    private HorizontalScrollView scrollView;
    private static Display display;
    private static Point size;

    private static boolean[] listRadioButtonState = {false, false, false, false};
    private static final String BIN_DIGIT = "01";
    private static final String OCT_DIGIT = BIN_DIGIT + "234567";
    private static final String DEC_DIGIT = OCT_DIGIT + "89";
    //private static int radioButtonChecked = Base.DEC.getCode();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_programmer, container, false);
        initialize();
        if (root.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            createKeyboardHorizontal(size.y*12f/1080);
        else
            createKeyboard(size.y*18f/1776);

        hexRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = inputExpression.getText().toString();
                if (temp.length() == 0 || temp.contains("=")) changelistRadioButtonState(Base.HEX.getCode());
            }
        });

        decRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = inputExpression.getText().toString();
                if (temp.length() == 0 || temp.contains("=")) changelistRadioButtonState(Base.DEC.getCode());
            }
        });

        octRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = inputExpression.getText().toString();
                if (temp.length() == 0 || temp.contains("=")) changelistRadioButtonState(Base.OCT.getCode());
            }
        });

        binRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = inputExpression.getText().toString();
                if (temp.length() == 0 || temp.contains("=")) changelistRadioButtonState(Base.BIN.getCode());
            }
        });

        keyboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Element item = (Element) keyboard.getItemAtPosition(position);
                addCharacter(item.getName());
            }
        });
        return root;
    }

    private void initialize() {
        if (display == null)
            display = getActivity().getWindowManager().getDefaultDisplay();
        if (size == null)
            size = new Point();
        display.getSize(size);

        scrollView = root.findViewById(R.id.scroll);
        inputField = root.findViewById(R.id.programmer_result);
        inputExpression = root.findViewById(R.id.programmer_input);

        hexRadioButton = root.findViewById(R.id.HEX);
        hexResult = root.findViewById(R.id.hex_result);
        decRadioButton = root.findViewById(R.id.DEC);
        decResult = root.findViewById(R.id.dec_result);
        octRadioButton = root.findViewById(R.id.OCT);
        octResult = root.findViewById(R.id.oct_result);
        binRadioButton = root.findViewById(R.id.BIN);
        binResult = root.findViewById(R.id.bin_result);

        keyboard = root.findViewById(R.id.program_keyboard);
    }

    private void createKeyboard(float textSize) {
        KeyboardAdapter adapter = new KeyboardAdapter(
                getActivity(),
                GeneralArray.getListOfProgrammerVertical(),
                R.layout.key_layout,
                textSize
        );
        keyboard.setAdapter(adapter);
    }

    private void createKeyboardHorizontal(float textSize) {
        KeyboardAdapter adapter = new KeyboardAdapter(
                getActivity(),
                GeneralArray.getListOfProgrammerHorizontal(),
                R.layout.key_layout,
                textSize
        );
        keyboard.setAdapter(adapter);
    }

    private void changelistRadioButtonState(int postion) {
        for(int i = 0; i<4;i++)
        {
            listRadioButtonState[i] = i == postion;
        }
        changeRadioButtonState();
    }

    private void changeRadioButtonState(){
        hexRadioButton.setChecked(listRadioButtonState[Base.HEX.getCode()]);
        decRadioButton.setChecked(listRadioButtonState[Base.DEC.getCode()]);
        octRadioButton.setChecked(listRadioButtonState[Base.OCT.getCode()]);
        binRadioButton.setChecked(listRadioButtonState[Base.BIN.getCode()]);
    }

    private void addCharacter(String character)
    {
        String input = inputField.getText().toString();
        String inputValue = "";

//        if (input.equals("0") || input.equals("-0"))
//            inputField.setText("");

        switch (character){
            case GeneralCharacter.NOT:
                inputExpression.append("not(");
                break;
            case GeneralCharacter.AND:
            case GeneralCharacter.OR:
            case GeneralCharacter.XOR:
            case GeneralCharacter.XNOR:
                inputExpression.append(character.toLowerCase());
                break;
            case GeneralCharacter.SPACE:
            case GeneralCharacter.POINT:
                break;
            case GeneralCharacter.CE:
                inputExpression.setText("");
                inputField.setText("");
                hexResult.setText("0");
                decResult.setText("0");
                octResult.setText("0");
                binResult.setText("0");
                break;
            case GeneralCharacter.DEL:
                inputValue = getStringAfterDeleting(input);
                break;
            case GeneralCharacter.ADD_AND_SUB:
                if (decRadioButton.isChecked())
                {
                    if (input.length()>0 && input.charAt(0)=='-')
                    {
                        inputValue = input.substring(1);
                    }
                    else
                        inputValue = "-" + input;
                }
                else
                    inputValue = input;
                break;
            case GeneralCharacter.LEFT_PARENTHESIS:
            case GeneralCharacter.RIGHT_PARENTHESIS:
            case GeneralCharacter.ANS:
            case GeneralCharacter.MOD:
            case GeneralCharacter.DIV:
            case GeneralCharacter.ADD:
            case GeneralCharacter.SUB:
                scrollView.fullScroll(View.FOCUS_RIGHT);
                inputExpression.append(inputField.getText().toString());
                inputExpression.append(character);
                inputField.setText("");
                break;
            case GeneralCharacter.EQUAL:
                scrollView.fullScroll(View.FOCUS_RIGHT);
                inputExpression.append(inputField.getText().toString());
                if (decRadioButton.isChecked() && input.equals("-"))
                    inputExpression.append("0");
                inputExpression.append(character);
                inputField.setText("");
                updateValue();
                break;
            default:
                inputValue = input;
                if (binRadioButton.isChecked()){
                    if (BIN_DIGIT.contains(character))
                    {
                        if (checkIntervalValue(input+character,Base.BIN.getCode()))
                        {
                            inputValue +=character;
                        }
                        else
                        {
                            notifyOutOfRange();
                            return;
                        }

                    }
                } else if (octRadioButton.isChecked()){
                    if (OCT_DIGIT.contains(character)){
                        if (checkIntervalValue(input+character,Base.OCT.getCode()))
                        {
                            inputValue +=character;
                        }
                        else
                        {
                            notifyOutOfRange();
                            return;
                        }
                    }
                } else if (decRadioButton.isChecked()){
                    if (DEC_DIGIT.contains(character)){
                        if (checkIntervalValue(input+character,Base.DEC.getCode()))
                        {
                            inputValue +=character;
                        }
                        else
                        {
                            notifyOutOfRange();
                            return;
                        }
                    }
                }else{
                    if (checkIntervalValue(input+character,Base.HEX.getCode()))
                    {
                        inputValue +=character;
                    }
                    else
                    {
                        notifyOutOfRange();
                        return;
                    }
                }
                break;
        }
        scrollView.fullScroll(View.FOCUS_RIGHT);
        inputField.setText(inputValue);
    }
    private void updateValue() {

    }

    private String getStringAfterDeleting(String input)
    {
        if (input.length()==0) return "";
        if (input.charAt(0) == '-') {
            if (input.equals("-0")) input = "0";
            //like -27
            else if (input.length() > 2)
                input = input.substring(0, input.length() - 1);
            else
                input = "-0";
        }
        else
        {
            if (input.length() > 1) input = input.substring(0,input.length()-1);
            else
                input = "";
        }

        return input;
    }

    private boolean checkIntervalValue(String input, int baseCode)
    {
        try {
            if (baseCode == Base.HEX.getCode()) {
                System.out.println(Integer.valueOf(input,Base.HEX.getRadix()));
                return true;
            } else if (baseCode == Base.DEC.getCode()) {
                Integer.valueOf(input);
                return true;
            } else if (baseCode == Base.BIN.getCode()) {
                Integer.valueOf(input,Base.BIN.getRadix());
                return true;
            } else {
                Integer.valueOf(input,Base.OCT.getRadix());
                return true;
            }
        }
        catch (NumberFormatException e)
        {
            return false;
        }

    }

    private void notifyOutOfRange()
    {
        Toast.makeText(getActivity(),"Out of range",Toast.LENGTH_SHORT).show();
    }

}