package com.trongtrung.calculator.ui.length;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LengthViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LengthViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is length fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}