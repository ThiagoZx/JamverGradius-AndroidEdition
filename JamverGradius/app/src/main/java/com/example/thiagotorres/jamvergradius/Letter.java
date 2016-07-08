package com.example.thiagotorres.jamvergradius;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Thiago.Torres on 05/07/2016.
 */
public class Letter {

    private Paint paint = new Paint();
    private int posX;
    private int posY;
    private Bitmap image;
    public Rect body;

    public Letter(Bitmap bitmap, int x, int y) {
        image = bitmap;
        posY = y;
        posX = x;
    }

    boolean LetterOffScreen (Canvas canvas){
        if(posY > canvas.getHeight()){
            return true;
        }
        return false;
    }

    void drawLetter(Canvas canvas){
        canvas.drawBitmap(image, posX, posY, paint);
    }

    void updateLetter(){
        posY = posY + 20;
        body = new Rect();
        body.set(posX, posY, posX + image.getWidth(), posY + image.getHeight());
    }

}
