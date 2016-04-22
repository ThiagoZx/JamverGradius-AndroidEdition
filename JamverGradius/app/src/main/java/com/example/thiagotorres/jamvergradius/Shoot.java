package com.example.thiagotorres.jamvergradius;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Thiago on 9/04/2016.
 */
public class Shoot {

    private Paint paint = new Paint();
    private int posX, posY = 0;
    private Bitmap image;
    public Rect body = new Rect();

    public Shoot(Bitmap bitmap, int positionX, int positionY) {
        image = bitmap;
        posX = positionX;
        posY = positionY;
    }

    boolean shootOffScreen(){
        if(posY + image.getHeight() < 0){
            return true;
        }
        return false;
    }

    void drawShoot(Canvas canvas){
        canvas.drawBitmap(image, posX - image.getWidth() / 2, posY, paint);
    }

    void updateShoot(){
        posY = posY - 45;
        body = new Rect();
        body.set(posX, posY, posX + image.getWidth(), posY + image.getHeight());
    }
}
