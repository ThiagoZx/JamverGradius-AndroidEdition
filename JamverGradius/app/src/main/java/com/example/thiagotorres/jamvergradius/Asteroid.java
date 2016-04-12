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

    public Asteroid (Bitmap bitmap) {
        image = bitmap;
    }

    void setPositions(){
        posX = (int)Math.random() * ;
        posY = 0 - image.getHeight();
    }

    void drawAsteroid(Canvas canvas){
        canvas.drawBitmap(image, posX - image.getWidth() / 2, posY, paint);
    }

    void updateAsteroid(){
        posY = posY + 20
    }
}
