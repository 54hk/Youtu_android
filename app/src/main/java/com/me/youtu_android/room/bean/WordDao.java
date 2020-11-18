package com.me.youtu_android.room.bean;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insertWrod(Word... words);

    @Delete
    void deleteWrod(Word... words);

    @Update
    void updataWord(Word... words);

    @Query("Delete from table_word")
    void deleteAllWord();

    @Query("Select * from table_word order by id Desc")
    LiveData<List<Word>> getAllWords();

}
