package com.shreyansh.drawtext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class Canvas extends View {
    Paint paint;
    Path path;
    public Canvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        path=new Path();

        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xPos=event.getX();
        float yPos=event.getY();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xPos,yPos);
                return true;

            case MotionEvent.ACTION_MOVE:
                 path.lineTo(xPos,yPos);
                 break;

            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }
}
