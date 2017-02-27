package com.josh.RPGHealthTracker;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    public static String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

    }


    protected void onStart(){
        super.onStart();
        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREF_FILE",0);
        String base_health = settings.getString("baseHealth","null");

        setContentView(R.layout.activity_display_message);
        TextView health = (TextView) findViewById(R.id.saved_health_text);
        //health.setText("test");
        //textView = new TextView(this);
        //ViewGroup layout = (ViewGroup) findViewById(R.id.base_health);
        //layout.addView(textView);

        health.setTextSize(100);
        health.setText(base_health);

    }
}
