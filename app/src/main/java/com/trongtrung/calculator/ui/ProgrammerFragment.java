package com.trongtrung.calculator.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.trongtrung.calculator.R;

public class ProgrammerFragment extends Fragment {

    private enum Base {
        BIN(0), OCT(1), DEC(2), HEX(3);
        private int baseCode;
        Base(int baseCode){this.baseCode = baseCode;}
        public int getCode(){ return this.baseCode;}
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_programmer, container, false);


        return root;
    }
}