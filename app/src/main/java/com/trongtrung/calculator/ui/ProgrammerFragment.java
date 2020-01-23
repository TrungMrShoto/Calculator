package com.trongtrung.calculator.ui;

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

public class ProgrammerFragment extends Fragment {

    private enum Base {
        BIN(0), OCT(1), DEC(2), HEX(3);
        private int baseCode;
        Base(int baseCode){this.baseCode = baseCode;}
        public int getCode(){ return this.baseCode;}
    }

    private View root;
    private GridView keyboard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_programmer, container, false);

        initialize();
        createKeyboardVertical();

        return root;
    }

    private void createKeyboardVertical() {
        KeyboardAdapter adapter = new KeyboardAdapter(
                getActivity(),
                GeneralArray.getListOfProgrammerVertical(),
                R.layout.key_layout,
                16.0f
        );
        keyboard.setAdapter(adapter);
    }

    private void initialize() {
        keyboard = root.findViewById(R.id.program_keyboard);
    }
}