package com.me.youtu_android.paging.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.me.youtu_android.R;
import com.me.youtu_android.paging.bean.StudentBean;

public class MyRecycleAdapter extends PagedListAdapter<StudentBean, MyRecycleAdapter.ViewHolder> {
    public MyRecycleAdapter() {
        super(new DiffUtil.ItemCallback<StudentBean>() {

            @Override
            public boolean areItemsTheSame(@NonNull StudentBean oldItem, @NonNull StudentBean newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull StudentBean oldItem, @NonNull StudentBean newItem) {
                return oldItem.getName().endsWith(oldItem.getName());
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.paging_item, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentBean studentBean = getItem(position);
        if(studentBean == null){

            holder.textView.setText("loading");
        }else{

            holder.textView.setText(studentBean.getName());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_content);
        }
    }
}
