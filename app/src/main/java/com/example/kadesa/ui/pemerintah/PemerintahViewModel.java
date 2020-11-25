package com.example.kadesa.ui.pemerintah;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PemerintahViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PemerintahViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}