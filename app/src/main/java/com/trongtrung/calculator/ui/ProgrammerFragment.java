package com.trongtrung.calculator.ui;

import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.trongtrung.calculator.GeneralArray;
import com.trongtrung.calculator.KeyboardAdapter;
import com.trongtrung.calculator.R;

public class ProgrammerFragment extends Fragment {

    private enum Base {
        BIN(3), OCT(2), DEC(1), HEX(0);
        private int baseCode;
        Base(int baseCode){this.baseCode = baseCode;}
        public int getCode(){ return this.baseCode;}
    }

    private View root;
    private GridView keyboard;
    private TextView inputField, hexResult, decResult, octResult, binResult;
    private RadioButton hexRadioButton, decRadioButton, octRadioButton, binRadioButton;
    private Button bitwiseButton;
    private static Display display;
    private static Point size;

    private static boolean[] listRadioButtonState = {false, false, false, false};

    private static int radioButtonChecked = Base.DEC.getCode();

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
                changelistRadioButtonState(Base.HEX.getCode());
            }
        });

        decRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changelistRadioButtonState(Base.DEC.getCode());
            }
        });

        octRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changelistRadioButtonState(Base.OCT.getCode());
            }
        });

        binRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changelistRadioButtonState(Base.BIN.getCode());
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

        inputField = root.findViewById(R.id.programmer_input);

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
            if (i == postion) listRadioButtonState[i] = true;
            else
                listRadioButtonState[i] = false;
        }
        changeRadioButtonState();
    }

    private void changeRadioButtonState(){
        hexRadioButton.setChecked(listRadioButtonState[Base.HEX.getCode()]);
        decRadioButton.setChecked(listRadioButtonState[Base.DEC.getCode()]);
        octRadioButton.setChecked(listRadioButtonState[Base.OCT.getCode()]);
        binRadioButton.setChecked(listRadioButtonState[Base.BIN.getCode()]);
    }

}