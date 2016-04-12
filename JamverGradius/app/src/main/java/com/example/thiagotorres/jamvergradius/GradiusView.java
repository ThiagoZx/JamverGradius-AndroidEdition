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

import java.util.ArrayList;
import java.util.List;

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
    List<Shoot> machineGun;

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
        machineGun = new ArrayList<Shoot>();
        ship = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.spritesheet));
    }

    private void shoot(){
        long currTime = System.currentTimeMillis();

        if((currTime - lastTime) > 350){
            lastTime = currTime;

            machineGun.add(machineGun.size(), new Shoot(BitmapFactory.decodeResource(getResources(), R.drawable.laser),
                    ship.cannonPositionX(), ship.cannonPositionY()));

        }
    }

    private void Update() {
        background.updateBackground();
        ship.updatePlayer(x, y);
        for (int i = 0; i < machineGun.size(); i++){
            machineGun.get(i).updateShoot();
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        background.drawBackground(canvas);
        for (int i = 0; i < machineGun.size(); i++){
            machineGun.get(i).drawShoot(canvas);
        }
        ship.drawPlayer(canvas);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_UP ){
            shoot();
        }

        return true;
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
