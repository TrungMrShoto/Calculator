package com.trongtrung.calculator.ui;

import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.trongtrung.calculator.GeneralArray;
import com.trongtrung.calculator.KeyboardAdapter;
import com.trongtrung.calculator.R;

public class StandardFragment extends Fragment {

    private View root;
    private GridView keyboard;
    private static Display display;
    private static Point size;
    private TextView txtShift, txtHyp, txtRad, txtDeg, inputField, outputField;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        root = inflater.inflate(R.layout.fragment_standard, container, false);

        initialize();

        if (root.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            createKeyboardHorizontal(size.y*15f/1080);
        else
            createKeyboardVertical(size.y*20f/1776);
        return root;
    }

    private void initialize() {
        if (display == null)
            display = getActivity().getWindowManager().getDefaultDisplay();
        if (size == null)
            size = new Point();
        display.getSize(size);

        txtShift = root.findViewById(R.id.SHIFT);
        txtHyp = root.findViewById(R.id.HYP);
        txtRad = root.findViewById(R.id.RAD);
        txtDeg = root.findViewById(R.id.DEG);
        inputField = root.findViewById(R.id.standard_input);
        outputField = root.findViewById(R.id.standard_result);

        keyboard = root.findViewById(R.id.standard_keyboard);
    }

    private void createKeyboardVertical(float textSize) {
        KeyboardAdapter adapter = new KeyboardAdapter(
                getActivity(),
                GeneralArray.getListOfStandardVertical(),
                R.layout.key_layout,
                textSize
        );
        keyboard.setAdapter(adapter);
    }

    private void createKeyboardHorizontal(float textSize)
    {
        KeyboardAdapter adapter = new KeyboardAdapter(
                getActivity(),
                GeneralArray.getListOfStandardHorizontal(),
                R.layout.key_layout,
                textSize
        );
        keyboard.setAdapter(adapter);
    }




}