package com.josh.RPGHealthTracker;

import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import java.util.Date;


public class MainActivity extends AppCompatActivity {
    public static String EXTRA_MESSAGE = "com.josh.RPGHealthTracker.Message";
    public SharedPreferences settings;
    public String base_health;
    public int currentHealth;
    public static String log = "";
    public static String logTime = "";
    SimpleDateFormat date = new SimpleDateFormat("hh:mm, MM/dd/yy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    protected void onStart(){
        super.onStart();
        //clear saved prefs file
        //getApplicationContext().getSharedPreferences("PREF_FILE",0).edit().clear().commit();

        settings = getApplicationContext().getSharedPreferences("PREF_FILE",0);
        base_health = settings.getString("baseHealth","0");
        String current_health = settings.getString("currentHealth","0");
        String char_name = settings.getString("charName","Name not set");

        if (current_health.equals("null")){
            currentHealth = Integer.parseInt(base_health);
        }
        else {
            currentHealth = Integer.parseInt(current_health);
        }

        TextView baseHealthText = (TextView) findViewById(R.id.health_text_view);
        baseHealthText.setText(base_health);
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        currentHealthText.setText(""+currentHealth);
        TextView characterName = (TextView) findViewById(R.id.character_name);
        characterName.setText(char_name);

    }

    protected void onPause(){
        super.onPause();

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("currentHealth", ""+currentHealth);
        editor.apply();

    }

    protected void onStop(){
        super.onStop();

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("currentHealth", ""+currentHealth);
        editor.apply();

    }

    protected void onResume(){
        super.onResume();

//        String char_name = settings.getString("charName","null");
//        TextView characterName = (TextView) findViewById(R.id.character_name);
//        characterName.setText(char_name);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        /*EditText editText = (EditText) findViewById(R.id.edit_message);
        int base_health = Integer.parseInt(editText.getText().toString());
        textView.setText(base_health);
*/
        EditText editText = (EditText) findViewById(R.id.edit_health_message);
        String message = editText.getText().toString();

        SharedPreferences settings = getApplicationContext().getSharedPreferences("PREF_FILE",0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("baseHealth", message);
        editor.apply();


        intent.putExtra(EXTRA_MESSAGE, "");
        startActivity(intent);
    }

    public void viewLog(View view) {
        Intent intent = new Intent(this, LogActivity.class);

        intent.putExtra(EXTRA_MESSAGE, "");
        startActivity(intent);
    }

    public void showPopup(View view) {

        PopupMenu popup = new PopupMenu(this, view);

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
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        currentHealthText.setText(""+currentHealth);
        String time = date.format(new Date());
        log+="+1\n";
        logTime+=time + "\n";
    }

    public void minusOne(View view){
        currentHealth-=1;
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        currentHealthText.setText(""+currentHealth);
        String time = date.format(new Date());
        log+="-1\n";
        logTime+=time + "\n";
    }

    public void plusFive(View view){
        currentHealth+=5;
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        currentHealthText.setText(""+currentHealth);
        String time = date.format(new Date());
        log+="+5\n";
        logTime+=time + "\n";
    }

    public void minusFive(View view){
        currentHealth-=5;
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        currentHealthText.setText(""+currentHealth);
        String time = date.format(new Date());
        log+="-5\n";
        logTime+=time + "\n";
    }

    public void plusTen(View view){
        currentHealth+=10;
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        currentHealthText.setText(""+currentHealth);
        String time = date.format(new Date());
        log+="+10\n";
        logTime+=time + "\n";
    }

    public void minusTen(View view){
        currentHealth-=10;
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        currentHealthText.setText(""+currentHealth);
        String time = date.format(new Date());
        log+="-10\n";
        logTime+=time + "\n";
    }

    public void resetHealth(View view){
        currentHealth = Integer.parseInt(base_health);
        TextView currentHealthText = (TextView) findViewById(R.id.current_health_text_view);
        currentHealthText.setText(""+currentHealth);
        String time = date.format(new Date());
        log+="Reset to " + currentHealth + "\n";
        logTime+=time + "\n";
    }
}