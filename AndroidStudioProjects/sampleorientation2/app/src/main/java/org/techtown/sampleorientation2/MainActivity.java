package org.techtown.sampleorientation2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) showToast("가로방향");
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) showToast("세로방향");
    }
    public  void showToast(String data){
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}
