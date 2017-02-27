package com.josh.RPGHealthTracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LogActivity extends AppCompatActivity {

    public static String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
    }

    protected void onStart(){
        super.onStart();

        setContentView(R.layout.activity_log);
        TextView logText = (TextView) findViewById(R.id.log_text);
        TextView logTimeText = (TextView) findViewById(R.id.log_time_text);
        logText.setText(MainActivity.log);
        logTimeText.setText(MainActivity.logTime);
    }

}
