package com.example.orenn.threadactivities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AsyncActivity extends AppCompatActivity {

    Button mStart, mCreate, mCancel;
    TextView mText;
    CountAsyncTask task;
    boolean taskInProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        taskInProgress = false;
        task = null;
        mText = (TextView) findViewById(R.id.text);
        mCreate = (Button) findViewById(R.id.create);
        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (task!= null && taskInProgress)
                {
                    task.cancel(true);
                    taskInProgress = false;
                }
                task = new CountAsyncTask(mText);

            }
        });
        mStart = findViewById(R.id.start);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taskInProgress)
                {
                    return;
                }
                task.execute();
                taskInProgress = true;

            }
        });

        mCancel = findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.cancel(true);
                task = null;
                taskInProgress = false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        task.cancel(true);
        task = null;
    }
}
