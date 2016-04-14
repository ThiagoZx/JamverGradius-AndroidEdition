package com.example.thiagotorres.jamvergradius;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Thiago.Torres on 12/04/2016.
 */
public class Asteroid {
    private Paint paint = new Paint();
    private int posX;
    private int posY;
    private Bitmap image;
    double screen;
    boolean isSet = true;

    public Asteroid (Bitmap bitmap) {
        image = bitmap;
        posY = 0 - image.getHeight();
    }

    void setPositions(Canvas canvas){
        screen = canvas.getWidth();
        posX = (int)(Math.random() * screen) - image.getWidth();
    }

    void drawAsteroid(Canvas canvas){
        if (isSet){
            setPositions(canvas);
            isSet = false;
        }
        canvas.drawBitmap(image, posX, posY, paint);
    }

    void updateAsteroid(){
        posY = posY + 20;
    }
}
