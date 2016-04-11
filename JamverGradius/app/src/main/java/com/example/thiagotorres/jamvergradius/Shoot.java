package com.example.thiagotorres.jamvergradius;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Thiago on 9/04/2016.
 */
public class Shoot {

    private Paint paint = new Paint();
    private int posX, posY = 0;
    private Bitmap image;

    public Shoot(Bitmap bitmap, int positionX, int positionY) {
        image = bitmap;
        posX = positionX;
        posY = positionY;
    }

    void drawShoot(Canvas canvas){
        canvas.drawBitmap(image, posX, posY, paint);
    }

    void updateShoot(){
        //posY = posY + 7;
    }
}
