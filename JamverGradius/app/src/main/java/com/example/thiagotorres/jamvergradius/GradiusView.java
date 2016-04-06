package com.example.thiagotorres.jamvergradius;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;

import java.io.Console;

/**
 * Created by Thiago.Torres on 05/04/2016.
 */
public class GradiusView extends View implements SensorEventListener, Runnable{

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    private long lastTime = 0;
    private float posX, posY, posZ;

    private Handler handler;

    private boolean isUpdating = true;

    Paint color = new Paint();

    public GradiusView (Context context){
        super(context);
        handler = new Handler();
        handler.post(this);

        //MainActivity a = new MainActivity();
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        color.setColor(Color.BLUE);
        color.setStyle(Paint.Style.FILL);
        color.setTextSize(100);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawText("Pos_x:" + posX, 20, 200, color);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (color.getColor() == (Color.RED)){
            color.setColor(Color.BLUE);
        } else{
            color.setColor(Color.RED);
        }

        System.out.println("Touch!");
        return true;
    }

    private void Update() {
        //Update Game
    }

    @Override
    public void run() {
        if(isUpdating)
        {
            handler.postDelayed(this, 30);

            Update();
            invalidate();
        }
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
