package org.techtown.simpledata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 100;
    public static String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                SimpleData data = new SimpleData(100, "Hello");
                intent.pu
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }
}
