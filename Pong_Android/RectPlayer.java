package com.example.up201503708.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Leonardo on 05-05-2017.
 */

public class RectPlayer {

    private Rect rectangle;
    private int color;

    public RectPlayer(Rect rectangle, int color){
        this.rectangle=rectangle;
        this.color=color;
    }

    public void draw(Canvas canvas){
        Paint paint=new Paint();
        paint.setColor(color);

        canvas.drawRect(rectangle, paint);
    }

    public void update(Point point){
        ///l t r b
        rectangle.set(point.x-rectangle.width()/2,point.y-rectangle.height()/2,
                point.x+rectangle.width()/2,point.y+rectangle.height()/2);

    }
}
