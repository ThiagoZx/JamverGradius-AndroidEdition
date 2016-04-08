package com.example.thiagotorres.jamvergradius;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Thiago.Torres on 08/04/2016.
 */
public class Player {

    private int posX, posY = 0;
    //private int size = 128;
    private Paint paint = new Paint();
    private Bitmap image;

    public Player(Bitmap bitmap) {
        image = bitmap;
    }

    void drawPlayer(Canvas canvas){
        canvas.drawBitmap(image, posX, posY, paint);
    }

    void updatePlayer(int x, int y){
        posX += x;
        posY += y;
    }

}
