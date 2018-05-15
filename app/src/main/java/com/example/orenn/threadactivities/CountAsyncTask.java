package com.example.orenn.threadactivities;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

public class CountAsyncTask extends AsyncTask<Void, Integer, Void> {


    private TextView textview;

    public CountAsyncTask(TextView text){
        this.textview = text;
    }


    @Override
    protected Void doInBackground(Void... v) {
        for (int i = 0; i< 10; i++){
            publishProgress(i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            if (isCancelled()){
                break;
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        this.textview.setText(String.valueOf(values[0]));
    }

    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);
        this.textview.setText("Done");
    }
}
