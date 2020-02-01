package org.techtown.samplepaint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintBoard extends View {
    Canvas canvas;
    Bitmap bitmap;
    Paint paint;

    int lastX;
    int lastY;

    public PaintBoard(Context context) {
        super(context);
        init(context);
    }

    public PaintBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private  void init(Context context){
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);

        this.lastX = -1;
        this.lastY = -1;
    }

    protected  void onSizeChanged(int w, int h, int oldw, int oldh){
        Bitmap img = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        Canvas can = new Canvas();
        can.setBitmap(img);
        can.drawColor(Color.WHITE);

        bitmap = img;
        canvas = can;
    }

    protected void onDraw(Canvas canvas){
        if(bitmap != null) canvas.drawBitmap(bitmap, 0,0,null);
    }

    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_UP:
                lastY = -1;
                lastX = -1;
                break;
            case MotionEvent.ACTION_DOWN:
                if (lastX != -1) {
                    if (x != lastX || y != lastY) {
                        canvas.drawLine(lastX, lastY, x, y, paint);
                    }
                }
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (lastX != -1) canvas.drawLine(lastX, lastY, x, y, paint);
                lastY = y;
                lastX = x;
                break;
        }
        invalidate();
        return true;
    }
}
