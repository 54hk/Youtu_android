package com.me.youtu_android.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class LiveDataViewModel extends ViewModel {

    public MutableLiveData<Integer> number;
    private SavedStateHandle savedStateHandle;


    public LiveDataViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
    }

    public MutableLiveData<String> getName() {
        if (!savedStateHandle.contains(LiveDataActivity.NAME)) {
            savedStateHandle.set(LiveDataActivity.NAME, "");
        }
        return savedStateHandle.getLiveData(LiveDataActivity.NAME);
    }

    public void setName(String mgs) {
        getName().setValue(mgs);
    }


    public MutableLiveData<Integer> getNumber() {
        if (number == null) {
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public void add() {
        getNumber().setValue(getNumber().getValue() + 1);
    }

    public void cut() {
        getNumber().setValue(getNumber().getValue() - 1);
    }
}
