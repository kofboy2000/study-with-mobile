package com.ahnsong.studymobile.ui.main.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SearchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Start Broadcasting");
    }

    public LiveData<String> getText() {
        return mText;
    }
}