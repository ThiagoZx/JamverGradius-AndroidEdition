package com.example.thiagotorres.jamvergradius;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
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
    private long gravitationalField = System.currentTimeMillis();
    Background background;
    Player ship;
    List<Shoot> machineGun;
    List<Asteroid> meteorShower;
    List<Letter> alphabet;
    int screenW;
    int screenH;

    public GradiusView (Context context){
        super(context);

        handler = new Handler();
        handler.post(this);

        senSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        screenW = context.getResources().getDisplayMetrics().widthPixels;
        screenH = context.getResources().getDisplayMetrics().heightPixels;

        Start();
    }

    void Start(){

        background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background),
                                    BitmapFactory.decodeResource(getResources(), R.drawable.stars));
        machineGun = new ArrayList<>();
        meteorShower = new ArrayList<>();
        alphabet = new ArrayList<>();
        ship = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.spritesheet), screenW / 2, screenH / 2);
    }

    private void shoot(){
        long currTime = System.currentTimeMillis();

        if((currTime - lastTime) > 350){
            lastTime = currTime;

            machineGun.add(machineGun.size(), new Shoot(BitmapFactory.decodeResource(getResources(), R.drawable.laser),
                    ship.cannonPositionX(), ship.cannonPositionY()));

        }
    }

    private void meteor(){
        long currTime = System.currentTimeMillis();

        if((currTime - gravitationalField) > 1500){
            gravitationalField = currTime;
            meteorShower.add(meteorShower.size(), new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid)));
        }
    }

    private void createLetter(int x, int y) {
        if (ship.getCounter() > 5){
            alphabet.add(alphabet.size(), new Letter(BitmapFactory.decodeResource(getResources(), R.drawable.letter), x, y));
        }
    }

    private void Update() {
        background.updateBackground();
        boolean gameOver = false;

        for (int i = 0; i < meteorShower.size(); i++){
            if (Rect.intersects(meteorShower.get(i).body, ship.body))
                gameOver = true;
        }


        for (int i = 0; i < meteorShower.size(); i++){
            for (int j = 0; j < machineGun.size(); j++){
                if (Rect.intersects(meteorShower.get(i).body, machineGun.get(j).body)) {
                    //Work from here -- All done! Adjust performance and image size. Oh, the Intent, by the way, that is the work itself. Do it.
                    ship.increaseCounter();
                    createLetter(meteorShower.get(i).getPositions("x"), meteorShower.get(i).getPositions("y"));
                    meteorShower.remove(i);
                    machineGun.remove(j);
                    break;
                }
            }
        }

        if (!gameOver) {
            meteor();
            for (int i = 0; i < machineGun.size(); i++) {
                if (machineGun.get(i).shootOffScreen()){
                    machineGun.remove(i);
                } else {
                    machineGun.get(i).updateShoot();
                }
            }

            for (int i = 0; i < meteorShower.size(); i++) {
                meteorShower.get(i).updateAsteroid();
            }

            for (int i = 0; i < alphabet.size(); i++) {
                alphabet.get(i).updateLetter();
            }

            ship.updatePlayer(x, y);
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        background.drawBackground(canvas);

        for (int i = 0; i < machineGun.size(); i++){
            machineGun.get(i).drawShoot(canvas);
        }

        for (int i = 0; i < alphabet.size(); i++){
            if(alphabet.get(i).LetterOffScreen(canvas)){
                alphabet.remove(i);
            } else {
                alphabet.get(i).drawLetter(canvas);
            }
        }

        for (int i = 0; i < meteorShower.size(); i++){
            if (meteorShower.get(i).asteroidOffScreen(canvas)){
                meteorShower.remove(i);
            }else {
                meteorShower.get(i).drawAsteroid(canvas);
            }
        }
        ship.drawPlayer(canvas);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN ||
            event.getAction() == MotionEvent.ACTION_MOVE ||
            event.getAction() == MotionEvent.ACTION_UP ){
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
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
