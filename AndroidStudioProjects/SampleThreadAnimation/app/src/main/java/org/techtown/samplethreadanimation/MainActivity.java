package org.techtown.samplethreadanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ArrayList<Drawable> drawablelist = new ArrayList<Drawable>();
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        drawablelist.add(res.getDrawable(R.drawable.face1));
        drawablelist.add(res.getDrawable(R.drawable.face2));
        drawablelist.add(res.getDrawable(R.drawable.face3));
        drawablelist.add(res.getDrawable(R.drawable.face4));
        drawablelist.add(res.getDrawable(R.drawable.face5));

        imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimThread thread =  new AnimThread();
                thread.start();
            }
        });
    }
    class AnimThread extends  Thread{
        public void run(){
            int index = 0;
            for(int i = 0; i< 100; i++) {
                final Drawable drawable = drawablelist.get(index);
                index+=1;
                if(index > 4) index = 0;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();;
                }
            }
        }
    }
}
