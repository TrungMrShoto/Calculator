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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.trongtrung.calculator.Element;
import com.trongtrung.calculator.EvalutateExpression;
import com.trongtrung.calculator.GeneralArray;
import com.trongtrung.calculator.GeneralCharacter;
import com.trongtrung.calculator.KeyboardAdapter;
import com.trongtrung.calculator.R;

public class StandardFragment extends Fragment {

    private View root;
    private GridView keyboard;
    private static Display display;
    private static Point size;
    private TextView inputField, outputField;
    private HorizontalScrollView inputScroll, resultScroll;
    public static final String OPERATOR = GeneralCharacter.ADD+ GeneralCharacter.SUB+ GeneralCharacter.MUL + GeneralCharacter.DIV;
    private static String expression="",result="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        root = inflater.inflate(R.layout.fragment_standard, container, false);

        initialize();

        if (root.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            createKeyboard(size.y*20f/1080, 28);
        else
            createKeyboard(size.y*25f/1776,92);

        keyboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Element item = (Element) keyboard.getItemAtPosition(position);
                String character = item.getName();
                String input = inputField.getText().toString();
                int input_length = input.length();
                switch (character) {
                    case GeneralCharacter.POINT:
                        if (checkPointCondition(input)) inputField.append(character);
                        break;
                    case GeneralCharacter.CE:
                        outputField.setText("");
                        inputField.setText("");
                        break;
                    case GeneralCharacter.DEL:
                        if (input_length>0) {
                            inputField.setText("");
                            if(input_length>=3 && input.substring(input_length-3).equals(GeneralCharacter.ANS))
                                inputField.append(input.substring(0, input_length - 3));
                            else
                                inputField.append(input.substring(0, input_length - 1));
                        }
                        break;
                    case GeneralCharacter.ADD_AND_SUB:
                        if (input_length>0 && input.charAt(input_length-1) == '-')
                        {
                            inputField.setText("");
                            inputField.append(input.substring(0, input_length - 1));
                        }
                        else if (input_length == 0 || isOperatorAtLastExpression(input))
                            inputField.append("-");
                        break;
                    case GeneralCharacter.DIV:
                    case GeneralCharacter.MUL:
                    case GeneralCharacter.ADD:
                    case GeneralCharacter.SUB:
                        if (input_length>0 && (!isOperatorAtLastExpression(input))) {
                            inputField.append(character);
                        }
                        break;
                    case GeneralCharacter.EQUAL:
                        if(input_length==0) {
                            inputField.setText(GeneralCharacter.ANS);
                            input = inputField.getText().toString();
                        }
                        if(!isOperatorAtLastExpression(input))
                        {
                            String result = EvalutateExpression.evaluateExpression(input);
                            outputField.setText(result);
                        }
                        break;
                    case GeneralCharacter.ANS:
                        if(input_length==0 || isOperatorAtLastExpression(input))
                            inputField.append(character);
                        break;
                    default:
                        if(input_length>=3 && input.substring(input_length-3).equals(GeneralCharacter.ANS))
                            break;
                        inputField.append(character);
                        break;
                }
                scroll();
                expression=inputField.getText().toString();
                result = outputField.getText().toString();
            }
        });
        return root;
    }

    private boolean checkPointCondition(String input) {
        String operand;
        int lastOperatorPosition = -1;
        for(int i=0;i<OPERATOR.length();i++)
        {
            int index = input.lastIndexOf(OPERATOR.charAt(i));
            if (index>lastOperatorPosition)
            {
                lastOperatorPosition = index;
            }
        }
        if (lastOperatorPosition>0)
            operand=input.substring(lastOperatorPosition+1);
        else
            operand=input;

        return !operand.equals(GeneralCharacter.ANS) && !operand.contains(".");

    }

    private void initialize() {
        if (display == null)
            display = getActivity().getWindowManager().getDefaultDisplay();
        if (size == null)
            size = new Point();
        display.getSize(size);

        inputField = root.findViewById(R.id.standard_input);
        outputField = root.findViewById(R.id.standard_result);
        inputScroll = root.findViewById(R.id.scroll_input);
        resultScroll = root.findViewById(R.id.scroll_result);
        keyboard = root.findViewById(R.id.standard_keyboard);
        inputField.setText(expression);
        outputField.setText(result);
        scroll();
    }

    private void createKeyboard(float textSize, int paddingVertical) {
        KeyboardAdapter adapter = new KeyboardAdapter(
                getActivity(),
                GeneralArray.getListOfStandardVertical(),
                R.layout.key_layout,
                textSize,
                paddingVertical
        );
        keyboard.setAdapter(adapter);
    }

    private boolean isOperatorAtLastExpression(String input) {
        if (input.length()==0) return true;
        String temp = input;
        temp = temp.substring(temp.length() - 1);
        return OPERATOR.contains(temp);
    }

    private void scroll()
    {
        inputScroll.post(new Runnable() {
            @Override
            public void run() {
                inputScroll.fullScroll(View.FOCUS_RIGHT);
            }
        });
        resultScroll.post(new Runnable() {
            @Override
            public void run() {
                resultScroll.fullScroll(View.FOCUS_RIGHT);
            }
        });
    }

}