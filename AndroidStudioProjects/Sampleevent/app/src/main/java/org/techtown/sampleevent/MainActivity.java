package org.techtown.sampleevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    GestureDetector     gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView2);

        View view =findViewById(R.id.view);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                float curX = event.getX();
                float curY = event.getY();

                if(action == MotionEvent.ACTION_DOWN){
                    println("손가락 눌림 : " + curX+ "," + curY);
                }
                else if(action == MotionEvent.ACTION_MOVE){
                    println("손가락  움직임 : " + curX+ "," + curY);
                }
                else if(action ==  MotionEvent.ACTION_UP){
                    println("손가락 뗌 : " + curX+ "," + curY);
                }
                return true;
            }
        });
        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDown");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("onShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapup");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("onScroll");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling");
                return false;
            }
        });
        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this,  "시스템 back 버튼이 눌렸음", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    public void println(String data){
        textView.append(data + "\n");

    }



}
