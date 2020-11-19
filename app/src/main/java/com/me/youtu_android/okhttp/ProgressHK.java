package com.me.youtu_android.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;

public class ProgressHK {
    public static KProgressHUD show(AppCompatActivity activity){
        return KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE) // SPIN_INDETERMINATE, PIE_DETERMINATE, ANNULAR_DETERMINATE, BAR_DETERMINATE
                .setCancellable(false)
                .setDimAmount(0.5f)
                .show();
    }
}
