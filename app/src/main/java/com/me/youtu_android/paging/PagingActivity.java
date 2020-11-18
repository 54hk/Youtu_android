package com.me.youtu_android.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.me.youtu_android.R;
import com.me.youtu_android.paging.adapter.MyRecycleAdapter;
import com.me.youtu_android.paging.bean.StudentBean;
import com.me.youtu_android.paging.bean.StudentDao;
import com.me.youtu_android.room.bean.WordDataBase;

import java.util.ArrayList;
import java.util.List;

public class PagingActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    Button butSet, butClear;

    private WordDataBase wordDataBase;
    private StudentDao mStudentDao;
    private LiveData<PagedList<StudentBean>> allStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);
        recyclerView = findViewById(R.id.recycle_view);
        butSet = findViewById(R.id.but_data_set);
        butClear = findViewById(R.id.data_clear);
        butSet.setOnClickListener(this);
        butClear.setOnClickListener(this);

        wordDataBase = WordDataBase.getInstance(this);
        mStudentDao = wordDataBase.getStudentDao();
        final MyRecycleAdapter recycleAdapter = new MyRecycleAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(recycleAdapter);

        allStudent = new LivePagedListBuilder<>(mStudentDao.getAllStudent(), 5).build();
        allStudent.observe(this, new Observer<PagedList<StudentBean>>() {
            @Override
            public void onChanged(PagedList<StudentBean> studentBeans) {
                recycleAdapter.submitList(studentBeans);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.data_clear:

                new ClearTask(mStudentDao).execute();
                break;
            case R.id.but_data_set:
                StudentBean[] studentBeanArr = new StudentBean[1000];
                for (int i = 0; i < 1000; i++) {
                    StudentBean studentBean = new StudentBean();
                    studentBean.setName("机器人 ");
                    studentBeanArr[i] = studentBean;
                }
                new InsertTask(mStudentDao).execute(studentBeanArr);
                break;
        }
    }

    static class InsertTask extends AsyncTask<StudentBean, Void, Void> {
        private StudentDao mStudentDao;

        public InsertTask(StudentDao studentDao) {
            this.mStudentDao = studentDao;
        }

        @Override
        protected Void doInBackground(StudentBean... beans) {
            mStudentDao.insertStudnet(beans);
            return null;
        }
    }

    static class ClearTask extends AsyncTask<Void, Void, Void> {
        private StudentDao mStudentDao;

        public ClearTask(StudentDao studentDao) {
            this.mStudentDao = studentDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            mStudentDao.deleteAllStudent();
            return null;
        }
    }
}
