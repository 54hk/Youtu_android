package com.me.youtu_android.paging.bean;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    void insertStudnet(StudentBean... beans);

    @Query("delete from table_student")
    void deleteAllStudent();

    @Query("select *from table_student order by id")
    DataSource.Factory<Integer ,StudentBean> getAllStudent();
}
