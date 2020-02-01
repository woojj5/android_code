package org.techtown.samplecustomviewdrawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class CustomViewDrawable extends View {
    private ShapeDrawable upperDrawable;
    private ShapeDrawable lowerDrawable;

    public CustomViewDrawable (Context context){
        super(context);
        init(context);
    }
    public CustomViewDrawable(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    private  void init(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        Resources resources = getResources();
        int blackColor = resources.getColor(R.color.color01);
        int grayColor = resources.getColor(R.color.color02);
        int darkgrayColor = resources.getColor(R.color.color03);

        upperDrawable = new ShapeDrawable();

        RectShape rect = new RectShape();
        rect.resize(width,height*2 / 3);
        upperDrawable.setShape(rect);
        upperDrawable.setBounds(0,0,width, height*2 / 3);

        LinearGradient gradient =  new LinearGradient(0,0,0, height * 2 / 3, grayColor, blackColor, Shader.TileMode.CLAMP);

        Paint paint = upperDrawable.getPaint();
        paint.setShader(gradient);

        lowerDrawable = new ShapeDrawable();

        RectShape rect2 = new RectShape();
        rect2.resize(width, height/3);
        lowerDrawable.setShape(rect2);
        lowerDrawable.setBounds(0,height*2/3, width, height);

        LinearGradient gradient2 =  new LinearGradient(0,0,0, height * 1 / 3, blackColor, darkgrayColor, Shader.TileMode.CLAMP);
        Paint paint2 = lowerDrawable.getPaint();
        paint2.setShader(gradient2);

    }
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        upperDrawable.draw(canvas);
        lowerDrawable.draw(canvas);

        Paint pathpaint = new Paint();
        pathpaint.setAntiAlias(true);
        pathpaint.setColor(Color.YELLOW);
        pathpaint.setStyle(Paint.Style.STROKE);
        pathpaint.setStrokeWidth(16.0F);
        pathpaint.setStrokeCap(Paint.Cap.BUTT);
        pathpaint.setStrokeJoin(Paint.Join.MITER);

        Path path  = new Path();
        path.moveTo(20,20);
        path.lineTo(120,20);
        path.lineTo(160,90);
        path.lineTo(180,80);
        path.lineTo(200,120);

        canvas.drawPath(path, pathpaint);

        pathpaint.setColor(Color.WHITE);
        pathpaint.setStrokeCap(Paint.Cap.ROUND);
        pathpaint.setStrokeJoin(Paint.Join.ROUND);

        path.offset(30,120);
        canvas.drawPath(path,pathpaint);

        pathpaint.setColor(Color.CYAN);
        pathpaint.setStrokeCap(Paint.Cap.SQUARE);
        pathpaint.setStrokeJoin(Paint.Join.BEVEL);

        path.offset(30,120);
        canvas.drawPath(path, pathpaint);
    }
}
