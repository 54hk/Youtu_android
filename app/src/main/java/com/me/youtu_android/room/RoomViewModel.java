package com.me.youtu_android.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.me.youtu_android.room.bean.Word;
import com.me.youtu_android.room.bean.WordDao;
import com.me.youtu_android.room.bean.WordDataBase;
import com.me.youtu_android.room.bean.WordsRepository;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {

    private WordsRepository mWordsRepository;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        mWordsRepository = new WordsRepository(application);
    }

    public LiveData<List<Word>> getAllView() {
        return mWordsRepository.getAllView();
    }

    public void insterWords(Word... words) {

        mWordsRepository.insterWords(words);
    }

    public void upDateWords(Word... words) {
        mWordsRepository.upDateWords(words);
    }

    public void deleteAllWords() {
        mWordsRepository.deleteAllWords();
    }

    public void deleteWords(Word... words) {
        mWordsRepository.deleteWords(words);
    }

}
