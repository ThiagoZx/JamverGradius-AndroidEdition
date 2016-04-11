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

    //Game Settings
    private int x, y = 0;
    private long lastTime;
    Background background;
    Player ship;
    Shoot[] machineGun = new Shoot[0]; // <----- Make this a list

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
        for (int i = 0; i < machineGun.length; i++){
            machineGun[i].drawShoot(canvas);
        }
        ship.drawPlayer(canvas);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN){

            long currTime = System.currentTimeMillis();

            if((currTime - lastTime) > 200){
                long diffTime = (currTime - lastTime);
                lastTime = diffTime;

                machineGun[machineGun.length] = new Shoot(BitmapFactory.decodeResource(getResources(), R.drawable.laser),
                                                            ship.cannonPositionX(), ship.cannonPositionY());

            }
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
        for (int i = 0; i < machineGun.length; i++){
            machineGun[i].updateShoot();
        }
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
