package org.techtown.samplecustomviewimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewImage extends View {
    private Bitmap cacheBitmap;
    private Canvas cacheCanvas;
    private Paint paint;

    public CustomViewImage(Context context) {
        super(context);
        init(context);
    }

    public CustomViewImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        paint = new Paint();
    }
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        createCacheBitmap(w,h);
        testDrawing();
    }
    private void createCacheBitmap(int w, int h){
        cacheBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        cacheCanvas.setBitmap(cacheBitmap);
    }
    private  void testDrawing(){
        cacheCanvas.drawColor(Color.WHITE);
        paint.setColor(Color.RED);
        cacheCanvas.drawRect(100,100,200,200,paint);

        Bitmap bi = BitmapFactory.decodeResource(getResources(), R.drawable.waterdrop);
        cacheCanvas.drawBitmap(bi,30,30,paint);

        Matrix matrix = new Matrix();
        matrix.setScale(-1,1);
        Bitmap bi2 = Bitmap.createBitmap(bi, 0,0, bi.getWidth(), bi.getHeight(), matrix, false);
        cacheCanvas.drawBitmap(bi2, 30,130, paint);

        Matrix matrix2 = new Matrix();
        matrix2.setScale(-1,1);
        Bitmap bi3 = Bitmap.createBitmap(bi, 0,0, bi.getWidth(), bi.getHeight(), matrix2, false);
        cacheCanvas.drawBitmap(bi3, 30,230, paint);

        paint.setMaskFilter(new BlurMaskFilter(10,BlurMaskFilter.Blur.NORMAL));
        Bitmap scale = Bitmap.createScaledBitmap(bi, bi.getWidth()*3, bi.getHeight()*3, false);
        cacheCanvas.drawBitmap(scale, 30,300,paint);

    }
    protected  void onDraw(Canvas canvas){
        if(cacheBitmap != null) canvas.drawBitmap(cacheBitmap,0,0,null);
    }
}
