package org.techtown.samplethread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int init = 0;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread = new BackgroundThread();
            }
        });
    }
    class BackgroundThread extends  Thread{
        public void run(){
            for(int i = 0; i< 100; i++){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){}
                init += 1;
                Log.d("Thread", "value" + init);
                textView.setText("value 값" + init);
            }
        }
    }
}
