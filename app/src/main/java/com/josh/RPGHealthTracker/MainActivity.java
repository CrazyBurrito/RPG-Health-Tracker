package com.josh.RPGHealthTracker;

import android.content.SharedPreferences;
import java.text.SimpleDateFormat;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.josh.RPGHealthTracker.Message";
    private SharedPreferences settings;
    private String base_health;
    private int currentHealth;
    private int tempHP;
    public static String log = "";
    public static String logTime = "";
    private final SimpleDateFormat date = new SimpleDateFormat("hh:mm, MM/dd/yy", Locale.US);
    private String[] characters;
    private DrawerLayout DrawerLayout;
    private ListView DrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        characters = new String[1];
        characters[0] = "john";
        DrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        DrawerList = (ListView) findViewById(R.id.drawer);

        DrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, characters));
        DrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            selectItem(position);
        }
    }

    private void selectItem(int position){
        DrawerList.setItemChecked(position, true);
    }

    protected void onStart(){
        super.onStart();
        //clear saved prefs file
        //getApplicationContext().getSharedPreferences("PREF_FILE",0).edit().clear().commit();

        settings = getApplicationContext().getSharedPreferences("PREF_FILE",0);
        base_health = settings.getString("baseHealth","0");
        String current_health = settings.getString("currentHealth","0");
        String char_name = settings.getString("charName","Name not set");
        String temp_hp = settings.getString("tempHP","0");

        if (current_health.equals("null")){
            currentHealth = Integer.parseInt(base_health);
        }
        else {
            currentHealth = Integer.parseInt(current_health);
        }
        if (temp_hp.equals("null")){
            tempHP = 0;
        }
        else {
            tempHP = Integer.parseInt(temp_hp);
        }

        TextView baseHealthText = (TextView) findViewById(R.id.health_text_view);
        baseHealthText.setText(base_health);
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        String text = ""+current_health;
        currentHealthText.setText(text);
        TextView characterNameText = (TextView) findViewById(R.id.character_name);
        characterNameText.setText(char_name);
        TextView tempHPText = (TextView) findViewById(R.id.temp_hp);
        text = ""+tempHP;
        tempHPText.setText(text);

    }

    protected void onPause(){
        super.onPause();

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("currentHealth", ""+currentHealth);
        editor.putString("tempHP",""+tempHP);
        editor.apply();

    }

    protected void onStop(){
        super.onStop();

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("currentHealth", ""+currentHealth);
        editor.putString("tempHP",""+tempHP);
        editor.apply();

    }

    public void viewLog(View view) {
        Intent intent = new Intent(this, LogActivity.class);

        intent.putExtra(EXTRA_MESSAGE, "");
        startActivity(intent);
    }

    public void changeName(View view){
        Intent intentName = new Intent(MainActivity.this, ChangeNameActivity.class);
        intentName.putExtra(EXTRA_MESSAGE, "");
        startActivity(intentName);
    }

    public void editHealth(View view){
        Intent intentHealth = new Intent(MainActivity.this, EditHealthActivity.class);
        intentHealth.putExtra(EXTRA_MESSAGE, "");
        startActivity(intentHealth);
    }

    public void showPopup(View v) {

        PopupMenu popup = new PopupMenu(this, v);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.rename:
                        //go to rename screen
                        Intent intentName = new Intent(MainActivity.this, ChangeNameActivity.class);
                        intentName.putExtra(EXTRA_MESSAGE, "");
                        startActivity(intentName);
                        return true;
                    case R.id.edit_base_health:
                        //go to edit base health screen
                        Intent intentHealth = new Intent(MainActivity.this, EditHealthActivity.class);
                        intentHealth.putExtra(EXTRA_MESSAGE, "");
                        startActivity(intentHealth);
                        return true;
                    default:
                        return false;
                }
            }
        });

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actions, popup.getMenu());
        popup.show();
    }



    public void plusOne(View view){
        currentHealth+=1;
        if(currentHealth>100000){
            currentHealth=100000;
        }
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        String text = ""+currentHealth;
        currentHealthText.setText(text);
        String time = date.format(new Date());
        log+="HP+1\n";
        logTime+=time + "\n";
    }

    public void minusOne(View view){
        currentHealth-=1;
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        String text = ""+currentHealth;
        currentHealthText.setText(text);
        String time = date.format(new Date());
        log+="HP-1\n";
        logTime+=time + "\n";
    }

    public void plusFive(View view){
        currentHealth+=5;
        if(currentHealth>100000){
            currentHealth=100000;
        }
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        String text = ""+currentHealth;
        currentHealthText.setText(text);
        String time = date.format(new Date());
        log+="HP+5\n";
        logTime+=time + "\n";
    }

    public void minusFive(View view){
        currentHealth-=5;
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        String text = ""+currentHealth;
        currentHealthText.setText(text);
        String time = date.format(new Date());
        log+="HP-5\n";
        logTime+=time + "\n";
    }

    public void plusTen(View view){
        currentHealth+=10;
        if(currentHealth>100000){
            currentHealth=100000;
        }
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        String text = ""+currentHealth;
        currentHealthText.setText(text);
        String time = date.format(new Date());
        log+="HP+10\n";
        logTime+=time + "\n";
    }

    public void minusTen(View view){
        currentHealth-=10;
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        String text = ""+currentHealth;
        currentHealthText.setText(text);
        String time = date.format(new Date());
        log+="HP-10\n";
        logTime+=time + "\n";
    }

    public void resetHealth(View view){
        currentHealth = Integer.parseInt(base_health);
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        String text = ""+currentHealth;
        currentHealthText.setText(text);
        String time = date.format(new Date());
        log+="Reset HP to " + currentHealth + "\n";
        logTime+=time + "\n";
    }

    public void plusTempHP(View view){
        tempHP+=1;
        if(tempHP>100000){
            tempHP=100000;
        }
        TextView tempHPText = (TextView) findViewById(R.id.temp_hp);
        String text = ""+tempHP;
        tempHPText.setText(text);
        String time = date.format(new Date());
        log+="TempHP+1\n";
        logTime+=time + "\n";
    }

    public void minusTempHP(View view){
        tempHP-=1;
        if(tempHP < 0){
            tempHP=0;
        }
        TextView tempHPText = (TextView) findViewById(R.id.temp_hp);
        String text = ""+tempHP;
        tempHPText.setText(text);
        String time = date.format(new Date());
        log+="TempHP-1\n";
        logTime+=time + "\n";
    }
}



