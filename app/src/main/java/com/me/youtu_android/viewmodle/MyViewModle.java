package com.me.youtu_android.viewmodle;

import androidx.lifecycle.ViewModel;

public class MyViewModle extends ViewModel {
    public int number;



    public void add() {
        number++;
    }

    public void cut() {
        number--;
    }
}
