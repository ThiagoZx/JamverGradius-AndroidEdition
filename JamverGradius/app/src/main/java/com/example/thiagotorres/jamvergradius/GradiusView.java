package com.example.thiagotorres.jamvergradius;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

/**
 * Created by Thiago.Torres on 05/04/2016.
 */
public class GradiusView extends View implements SensorEventListener{

    public SensorManager senSensorManager;
    public Sensor senAccelerometer;
    private long lastTime = 0;
    private float posX, posY, posZ;

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
        color.setTextSize(100);
        //canvas.drawRect(rect, color);
        canvas.drawText("Pos_x:" + posX, 20, 200, color);
        super.onDraw(canvas);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        posX = event.values[0];
        posY = event.values[1];
        posZ = event.values[2];

        long currTime = System.currentTimeMillis();

        if((currTime - lastTime) > 100){
            long diffTime = (currTime - lastTime);
            lastTime = diffTime;

            //Make sure you gather the shake gesture to perform the special!

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
