package com.me.youtu_android;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModle extends ViewModel {
    private MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber() {
        if (number == null) {
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public void setNumber(int x) {
        getNumber().setValue(x);
    }


    public void add(int x) {
        getNumber().setValue(getNumber().getValue() + x);
    }

    public void jian(int x) {
        if (getNumber().getValue() == 0) {
            return;
        }
        getNumber().setValue(getNumber().getValue() - x);

    }
}
