package com.example.thiagotorres.jamvergradius;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Thiago.Torres on 05/04/2016.
 */
public class GradiusView extends View implements SensorEventListener, Runnable{

    //sensor stuff
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    //Managing stuff
    private Handler handler;

    //My healthy tests :D
    private int x, y = 0;
    Background background;
    Player ship;

    public GradiusView (Context context){
        super(context);
        handler = new Handler();
        handler.post(this);

        senSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        Start();
    }

    void Start(){
        background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background),
                                    BitmapFactory.decodeResource(getResources(), R.drawable.stars));
        ship = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.spritesheet));
    }

    @Override
    protected void onDraw(Canvas canvas){
        background.drawBackground(canvas);
        ship.drawPlayer(canvas);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //System.out.println("You're Holding still at X:" + event.getRawX() + " Y:" + event.getRawY());
        }

        if (event.getAction() == MotionEvent.ACTION_UP){
            //System.out.println("Y u let me go ; ^ ;");
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE){
            //System.out.println("STOP DRAGIN ME AROUND! >:C");
        }

        return true;
    }

    private void Update() {
        background.updateBackground();
        ship.updatePlayer(x, y);
    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
        Update();
        invalidate();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = Math.round(event.values[0]);
        y = Math.round(event.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
