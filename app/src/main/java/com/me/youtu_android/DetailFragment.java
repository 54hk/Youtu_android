package com.me.youtu_android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.me.youtu_android.databinding.FragmentDetailBinding;


public class DetailFragment extends Fragment implements View.OnClickListener {

    MyViewModle myViewModle;
    FragmentDetailBinding fragmentDetailBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myViewModle = ViewModelProviders.of(getActivity()).get(MyViewModle.class);
        fragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        fragmentDetailBinding.setData(myViewModle);
        fragmentDetailBinding.setLifecycleOwner(getActivity());
        return fragmentDetailBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        ((TextView) getView().findViewById(R.id.tv_detail)).setText(getArguments().getString("name"));

        fragmentDetailBinding.butReturnPage.setOnClickListener(this);
        fragmentDetailBinding.butAdd.setOnClickListener(this);
        fragmentDetailBinding.butJian.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_return_page:
//                Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_homeFragment);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_detailFragment_to_homeFragment);
//                navController.popBackStack();
                break;

            case R.id.but_add:
                myViewModle.add(1);
                break;
            case R.id.but_jian:
                myViewModle.jian(1);
                break;
        }
    }
}
