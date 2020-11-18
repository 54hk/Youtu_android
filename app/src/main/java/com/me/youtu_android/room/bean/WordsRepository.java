package com.me.youtu_android.room.bean;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordsRepository {

    WordDataBase mWordDataBase;
    WordDao mWordDao;

    public WordsRepository(Application application) {
        mWordDataBase = WordDataBase.getInstance(application.getApplicationContext());
        mWordDao = mWordDataBase.getWordDao();
    }

    public LiveData<List<Word>> getAllView() {
        return mWordDao.getAllWords();
    }

    public void insterWords(Word... words) {
        new InsterAsynTask(mWordDao).execute(words);
    }

    public void upDateWords(Word... words) {
        new UpDateAsynTask(mWordDao).execute(words);
    }

    public void deleteAllWords() {
        new DeleteAllAsynTask(mWordDao).execute();
    }

    public void deleteWords(Word... words) {
        new DeleteAsynTask(mWordDao).execute(words);
    }

    static class UpDateAsynTask extends AsyncTask<Word, Void, Void> {
        WordDao mWordDao;

        public UpDateAsynTask(WordDao wordDao) {
            this.mWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.updataWord(words);
            return null;
        }
    }

    static class DeleteAllAsynTask extends AsyncTask<Void, Void, Void> {
        WordDao mWordDao;

        public DeleteAllAsynTask(WordDao wordDao) {
            this.mWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordDao.deleteAllWord();
            return null;
        }
    }

    static class DeleteAsynTask extends AsyncTask<Word, Void, Void> {
        WordDao mWordDao;

        public DeleteAsynTask(WordDao wordDao) {
            this.mWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.deleteWrod(words);
            return null;
        }
    }

    static class InsterAsynTask extends AsyncTask<Word, Void, Void> {
        WordDao mWordDao;

        public InsterAsynTask(WordDao wordDao) {
            this.mWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.insertWrod(words);
            return null;
        }
    }
}
