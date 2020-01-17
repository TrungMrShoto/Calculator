package com.trongtrung.calculator.ui.programmer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProgrammerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProgrammerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is programmer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}