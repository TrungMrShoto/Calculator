package com.trongtrung.calculator.ui.programmer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.trongtrung.calculator.R;

public class ProgrammerFragment extends Fragment {

    private ProgrammerViewModel programmerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        programmerViewModel =
                ViewModelProviders.of(this).get(ProgrammerViewModel.class);

        View root = inflater.inflate(R.layout.fragment_programmer, container, false);

        final TextView textView = root.findViewById(R.id.text_programmer);

        programmerViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}