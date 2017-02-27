package com.josh.RPGHealthTracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;

public class ChangeNameActivity  extends AppCompatActivity {

    public static String message;
    protected EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        name = (EditText) findViewById(R.id.change_name);

        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
    }

    protected void onStart(){
        super.onStart();
        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREF_FILE",0);
        String charName = settings.getString("charName","");

        setContentView(R.layout.activity_change_name);
        TextView nameText = (TextView) findViewById(R.id.change_name);

        nameText.setText(charName);
    }

    public void saveName(View view){
        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREF_FILE",0);

        name = (EditText) findViewById(R.id.change_name);
        String char_name = name.getText().toString();

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("charName", char_name);
        editor.apply();

        finish();

    }
}
