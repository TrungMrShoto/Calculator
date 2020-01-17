package com.trongtrung.calculator.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by NguyenTrongTrung on 17 January 2020
 */
public class AboutViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is about fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
