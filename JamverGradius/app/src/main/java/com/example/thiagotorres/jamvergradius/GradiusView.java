package com.example.thiagotorres.jamvergradius;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by Thiago.Torres on 05/04/2016.
 */
public class GradiusView extends View {

    public GradiusView (Context context){
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        Rect rect = new Rect();
        rect.set(0, 0, canvas.getWidth(), canvas.getHeight() / 2);

        Paint color = new Paint();
        color.setColor(Color.BLUE);
        color.setStyle(Paint.Style.FILL);

        canvas.drawRect(rect, color);
        super.onDraw(canvas);
    }

    MainActivity a = new MainActivity();
    float b = a.getAccX();
    float c = a.getAccY();


}
