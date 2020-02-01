package org.techtown.samplelifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "oncreate 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onstart 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onstop 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "ondestroy 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onpause 호출됨", Toast.LENGTH_LONG).show();
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onresume 호출됨", Toast.LENGTH_LONG).show();
        restoreState();
    }
    public void println(String data){
        Toast.makeText(this,data, Toast.LENGTH_LONG);
        Log.d("Main", data);
    }
     protected void restoreState(){
         SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
         if((pref != null) && (pref.contains("name"))){
             String name = pref.getString("name", "");
             nameInput.setText(name);
         }
     }
    protected void saveState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", nameInput.getText().toString());
        editor.commit();
    }
    protected void clearState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
