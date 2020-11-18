package com.me.youtu_android.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.me.youtu_android.R;
import com.me.youtu_android.databinding.ActivityRoomBinding;
import com.me.youtu_android.room.bean.Word;
import com.me.youtu_android.room.bean.WordDao;
import com.me.youtu_android.room.bean.WordDataBase;

import java.util.List;
import java.util.Observable;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener {


    private RoomViewModel roomViewModel;
    private ActivityRoomBinding activityRoomBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRoomBinding = DataBindingUtil.setContentView(this, R.layout.activity_room);
        roomViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);
        roomViewModel.getAllView().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> allWords) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < allWords.size(); i++) {
                    stringBuffer.append(allWords.get(i).getId() + "-" + allWords.get(i).getWord() + ":" + allWords.get(i).getMean() + "\n");
                }
                activityRoomBinding.tvData.setText(stringBuffer.toString());
            }
        });

        activityRoomBinding.butDelete.setOnClickListener(this);
        activityRoomBinding.butDeleteAll.setOnClickListener(this);
        activityRoomBinding.butUpdate.setOnClickListener(this);
        activityRoomBinding.butInsert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_delete:
                deleteWord();
                break;
            case R.id.but_update:
                updateWord();
                break;
            case R.id.but_insert:
                insertWord();
                break;
            case R.id.but_delete_all:
                deletAllWord();
                break;
        }
    }


    public void insertWord() {
        Word word = new Word("English", "英语");
        Word word1 = new Word("Chinese", "中文");
        roomViewModel.insterWords(word, word1);
    }

    public void deleteWord() {
        Word word = new Word();
        word.setId(90);
        roomViewModel.deleteWords(word);
    }

    public void deletAllWord() {
        roomViewModel.deleteAllWords();
    }

    public void updateWord() {
        Word word = new Word("HI", "你好呀");
        word.setId(92);
        roomViewModel.upDateWords(word);
    }


}
