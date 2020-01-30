package com.trongtrung.calculator.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.trongtrung.calculator.GeneralArray;
import com.trongtrung.calculator.KeyboardAdapter;
import com.trongtrung.calculator.R;

public class StandardFragment extends Fragment {

    private View root;
    private GridView keyboard;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        root = inflater.inflate(R.layout.fragment_standard, container, false);

        initialize();
        if (root.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            createKeyboardHorizontal(15f);
        else
            createKeyboardVertical(20f);
        return root;
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

    private void initialize() {
        keyboard = root.findViewById(R.id.standard_keyboard);
    }
}