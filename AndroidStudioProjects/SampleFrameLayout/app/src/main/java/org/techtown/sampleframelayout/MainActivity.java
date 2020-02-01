package org.techtown.sampleframelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
    }
    public void button_clicked(View v){
        ChangeImage();
    }
    public void ChangeImage(){
        if(count == 0){
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.GONE);
            count = 1;

        }
        else if(count == 1){
            imageView.setVisibility(View.GONE);
            imageView2.setVisibility(View.VISIBLE);
            count = 0;
        }

    }
}
