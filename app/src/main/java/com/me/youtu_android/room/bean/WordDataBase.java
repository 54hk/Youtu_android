package com.me.youtu_android.room.bean;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.me.youtu_android.paging.bean.StudentBean;
import com.me.youtu_android.paging.bean.StudentDao;

@Database(entities = {Word.class, StudentBean.class}, version = 2, exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {
    private static WordDataBase INSTANCE;

    public static synchronized WordDataBase getInstance(Context mContext) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(mContext.getApplicationContext(), WordDataBase.class, "my_table")
//                    .allowMainThreadQueries()
                    .build();
        return INSTANCE;
    }

    public abstract WordDao getWordDao();

    public abstract StudentDao getStudentDao();
}
