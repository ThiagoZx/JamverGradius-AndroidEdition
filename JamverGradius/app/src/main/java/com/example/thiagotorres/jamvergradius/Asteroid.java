package com.example.thiagotorres.jamvergradius;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;

/**
 * Created by Thiago.Torres on 12/04/2016.
 */
public class Asteroid {
    private Paint paint = new Paint();
    private int posX;
    private int posY;
    private Bitmap image;
    int screen;

    public Asteroid (Bitmap bitmap, Context context) {
        image = bitmap;
        setPositions();
        screen = context.getResources().getDisplayMetrics().widthPixels;
    }

    void setPositions(){
        posX = ((int)(Math.random() * screen)) * (- 1);
        posY = 0 - image.getHeight();
    }

    void drawAsteroid(Canvas canvas){
        canvas.drawBitmap(image, posX, posY, paint);
    }

    void updateAsteroid(){
        posY = posY + 20;
    }
}
