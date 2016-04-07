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
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;

import java.io.Console;

/**
 * Created by Thiago.Torres on 05/04/2016.
 */
public class GradiusView extends View implements SensorEventListener, Runnable{

    //sensor stuff
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastTime = 0;
    private float posX, posY;//, posZ;

    //Managing stuff
    private Handler handler;
    private boolean isUpdating = true;

    //My healthy tests :D
    private Paint color = new Paint();
    private Rect player = new Rect();
    private int x, y = 0;

    public GradiusView (Context context){
        super(context);
        handler = new Handler();
        handler.post(this);

        senSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        color.setColor(Color.BLUE);
        color.setStyle(Paint.Style.FILL);
        color.setTextSize(100);

    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawText("Pos_x:" + posX, 20, 200, color);
        canvas.drawText("Pos_y:" + posY, 20, 400, color);
        canvas.drawRect(player, color);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            System.out.println("You're Holding still at X:" + event.getRawX() + " Y:" + event.getRawY());
        }

        if (event.getAction() == MotionEvent.ACTION_UP){
            System.out.println("Y u let me go ; ^ ;");
        }

        if (color.getColor() == (Color.RED)){
            color.setColor(Color.BLUE);
        } else{
            color.setColor(Color.RED);
        }
        System.out.println("You're Touching me at X:" + event.getRawX() + " Y:" + event.getRawY());
        return true;
    }

    private void Update() {
        player.set((x) * -1, y, ((x) * -1) + 300, y + 300);
        x += Math.round(posX);
        y += Math.round(posY);
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
        //posZ = event.values[2];

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
