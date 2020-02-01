package org.techtown.samplelayoutinflator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Menuactivity extends AppCompatActivity {
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuactivity);

        container = findViewById(R.id.container);
        Button button  = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater Inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                Inflater.inflate(R.layout.sub1, container, true);
            }
        });
    }

}
