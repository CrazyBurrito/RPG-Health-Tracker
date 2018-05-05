package com.josh.RPGHealthTracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Josh on 10/4/2016.
 */

public class EditHealthActivity extends AppCompatActivity {

    private EditText health;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_health);
        health = (EditText) findViewById(R.id.edit_health);
    }

    protected void onStart(){
        super.onStart();
        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREF_FILE",0);
        String charHealth = settings.getString("baseHealth","");

        setContentView(R.layout.activity_edit_health);
        TextView healthText = (TextView) findViewById(R.id.edit_health);

        healthText.setText(charHealth);
    }

    public void saveHealth(View view){
        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREF_FILE",0);

        health = (EditText) findViewById(R.id.edit_health);
        int int_health = Integer.parseInt(health.getText().toString());
        if(int_health > 100000){
            int_health=100000;
        }
        String char_health = Integer.toString(int_health);

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("baseHealth", char_health);
        editor.apply();

        finish();

    }

    public void saveAndReset(View view){
        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREF_FILE",0);

        health = (EditText) findViewById(R.id.edit_health);
        int int_health = Integer.parseInt(health.getText().toString());
        if(int_health > 100000){
            int_health=100000;
        }
        String char_health = Integer.toString(int_health);

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("baseHealth", char_health);
        editor.putString("currentHealth",char_health);
        editor.apply();

        finish();

    }
}
