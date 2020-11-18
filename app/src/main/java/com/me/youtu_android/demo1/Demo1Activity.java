package com.me.youtu_android.demo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.me.youtu_android.R;
import com.me.youtu_android.databinding.ActivityDemo1Binding;

public class Demo1Activity extends AppCompatActivity {

    Demo1ViewModel demo1ViewModel;
    ActivityDemo1Binding activityDemo1Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDemo1Binding = DataBindingUtil.setContentView(this, R.layout.activity_demo1);
        demo1ViewModel = ViewModelProviders.of(this).get(Demo1ViewModel.class);
        activityDemo1Binding.setData(demo1ViewModel);
        activityDemo1Binding.setLifecycleOwner(this);

        activityDemo1Binding.socre1.setText(demo1ViewModel.getItemA() + "");
        activityDemo1Binding.socre2.setText(demo1ViewModel.getItemB() + "");
    }
}
