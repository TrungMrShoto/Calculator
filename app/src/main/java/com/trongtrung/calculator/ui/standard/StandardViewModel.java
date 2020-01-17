package com.trongtrung.calculator.ui.standard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StandardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StandardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is standard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}