package com.me.youtu_android.demo1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class Demo1ViewModel extends ViewModel {
    public MutableLiveData<Integer> itemA;
    public MutableLiveData<Integer> itemB;
    private int aBack;
    private int bBeak;
    private SavedStateHandle mSavedStateHandle;

    public Demo1ViewModel(SavedStateHandle savedStateHandle) {
        this.mSavedStateHandle = savedStateHandle;
    }

    public MutableLiveData<Integer> getItemA() {
        if (itemA == null) {
            itemA = new MutableLiveData<>();
            itemA.setValue(0);
        }
        return itemA;
    }

    public MutableLiveData<Integer> getItemB() {
        if (itemB == null) {
            itemB = new MutableLiveData<>();
            itemB.setValue(0);
        }
        return itemB;
    }

    public void addA(int x) {
        bBeak = getItemB().getValue();
        aBack = getItemA().getValue();
        getItemA().setValue(getItemA().getValue() + x);
    }

    public void addB(int x) {
        bBeak = getItemB().getValue();
        aBack = getItemA().getValue();
        getItemB().setValue(getItemB().getValue() + x);
    }

    public void back() {
        getItemA().setValue(aBack);
        getItemB().setValue(bBeak);
    }

    public void loop() {
        getItemA().setValue(0);
        getItemB().setValue(0);
    }
}
