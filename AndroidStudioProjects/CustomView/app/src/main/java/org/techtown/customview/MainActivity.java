package org.techtown.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CustomView view = new CustomView(this);
        setContentView(view);
    }
}
