package com.me.youtu_android.paging.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_student")
public class StudentBean {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "student_name")
    private String name;
    @Ignore
    public StudentBean() {
    }

    public StudentBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
