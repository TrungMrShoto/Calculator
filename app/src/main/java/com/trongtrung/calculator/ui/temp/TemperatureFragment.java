package com.trongtrung.calculator.ui.temp;

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

public class TemperatureFragment extends Fragment {

    private TemperatureViewModel temperatureViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        temperatureViewModel =
                ViewModelProviders.of(this).get(TemperatureViewModel.class);
        View root = inflater.inflate(R.layout.fragment_temp, container, false);
        final TextView textView = root.findViewById(R.id.text_temp);
        temperatureViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}