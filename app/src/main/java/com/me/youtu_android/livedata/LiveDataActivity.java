package com.me.youtu_android.livedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.youtu_android.R;
import com.me.youtu_android.databinding.ActivityLiveDataBinding;

public class LiveDataActivity extends AppCompatActivity {

    private LiveDataViewModel liveDataViewModel;
    private ActivityLiveDataBinding activityLiveDataBinding;
    public static final String NAME = "NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLiveDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_live_data);
        liveDataViewModel = ViewModelProviders.of(this, new SavedStateViewModelFactory(this.getApplication(), this)).get(LiveDataViewModel.class);
        liveDataViewModel.getNumber().observe(this, new Observer<Integer>() {

            @Override
            public void onChanged(Integer integer) {
            }
        });
        activityLiveDataBinding.setData(liveDataViewModel);
        activityLiveDataBinding.setLifecycleOwner(this);
        activityLiveDataBinding.butGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveDataViewModel.setName("这是我设置的");
            }
        });
        liveDataViewModel.getName().observe(this, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                activityLiveDataBinding.tv2.setText(liveDataViewModel.getName().getValue());
            }
        });
    }

}
