package org.techtown.sampleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

import java.util.jar.Attributes;

public class MyButton extends AppCompatButton {

    public MyButton(Context context) {
        super(context);
        init(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        setBackgroundColor(Color.CYAN);
        setTextColor(Color.BLACK);

        float textsize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textsize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("MyButton", "onDraw 호출됨");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MyButton", "onToouchEvent 호출됨");

        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(Color.BLUE);
                setTextColor(Color.RED);

                break;

                case MotionEvent.ACTION_OUTSIDE:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    setBackgroundColor(Color.CYAN);
                    setTextColor(Color.BLACK);

                    break;
        }
        invalidate();

        return true;
    }
}
