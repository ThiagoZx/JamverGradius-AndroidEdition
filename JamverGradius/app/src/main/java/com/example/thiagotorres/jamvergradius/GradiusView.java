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

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    public GradiusView (Context context){
        super(context);
    }

    Start(){

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

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
