package com.example.orenn.threadactivities;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThreadActivity extends AppCompatActivity {

    Button mStart, mCreate, mCancel;
    TextView mText;
    Thread thread;
    Handler handler;
    boolean taskInProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        handler = new Handler();
        mText = (TextView) findViewById(R.id.text);
        mCreate = (Button) findViewById(R.id.create);
        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i=0;i<10;i++){
                            if (thread.isInterrupted()){
                                return;
                            }
                            else {
                                final int progress = i;
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mText.setText(String.valueOf(progress));
                                        ;
                                    }
                                });
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }
                });

            }
        });
        mStart = findViewById(R.id.start);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taskInProgress )
                {
                    return;
                }
                thread.start();
                taskInProgress = true;

            }
        });

        mCancel = findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelThread();
            }
        });
    }
    private void cancelThread(){
        thread.interrupt();
        taskInProgress = false;
    }
}


