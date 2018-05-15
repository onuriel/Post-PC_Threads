package com.example.orenn.threadactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mAsync, mThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAsync = (Button)findViewById(R.id.async);
        final MainActivity activity = this;
        mAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AsyncActivity.class);
                startActivity(intent);
            }
        });
        mThread = (Button)findViewById(R.id.thread);
        mThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ThreadActivity.class);
                startActivity(intent);
            }
        });

    }
}
