package com.me.youtu_android.viewmodle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.me.youtu_android.R;

public class ViewModleActivity extends AppCompatActivity implements View.OnClickListener {
    MyViewModle myViewModle;
    TextView tvNumber;
    Button butAdd, butCut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_modle2);

        myViewModle = ViewModelProviders.of(this).get(MyViewModle.class);
        tvNumber = findViewById(R.id.tv_number);
        butAdd = findViewById(R.id.but_add);
        butAdd.setOnClickListener(this);
        butCut = findViewById(R.id.but_cut);
        butCut.setOnClickListener(this);
        tvNumber.setText(myViewModle.number+"");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_add:
                myViewModle.add();
                break;
            case R.id.but_cut:
                myViewModle.cut();
                break;
        }
        tvNumber.setText(myViewModle.number+"");
    }
}
