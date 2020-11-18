package com.me.youtu_android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.NavigatorProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.me.youtu_android.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements View.OnClickListener {

    FragmentHomeBinding fragmentHomeBinding;
    MyViewModle myViewModle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myViewModle = ViewModelProviders.of(getActivity()).get(MyViewModle.class);

        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        fragmentHomeBinding.setData(myViewModle);
        fragmentHomeBinding.setLifecycleOwner(getActivity());
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentHomeBinding.butGoPage.setOnClickListener(this);
        fragmentHomeBinding.editText.setText(myViewModle.getNumber().getValue() + "");
        fragmentHomeBinding.seekBar.setProgress(myViewModle.getNumber().getValue());
        fragmentHomeBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myViewModle.setNumber(progress);
                Log.e("TTTT", "pr --- " + progress);

                Log.e("TTTT", "getNumber --- " + myViewModle.getNumber().getValue());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_go_page:
//            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detailFragment);
                Bundle bundle = new Bundle();
                bundle.putString("name", "homeFragmet");
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_homeFragment_to_detailFragment, bundle);
//                navController.popBackStack();
                break;
        }
    }
}
