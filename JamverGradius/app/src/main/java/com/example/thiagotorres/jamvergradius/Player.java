package com.example.thiagotorres.jamvergradius;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Thiago.Torres on 08/04/2016.
 */
public class Player {

    //The ship is 74x108 px long, from where it starts.
    private Paint paint = new Paint();
    private int posX, posY = 0;
    private int currentFrame;
    private Bitmap image;

    public Player(Bitmap bitmap) {
        image = bitmap;
    }

    void drawPlayer(Canvas canvas){
        canvas.drawBitmap(image, posX, posY, paint);
        //resizedbitmap1=Bitmap.createBitmap(bmp, 0,0,yourwidth, yourheight);
    }

    void updatePlayer(int x, int y){
        posX -= x * 2;
        posY += y * 3;
    }

}
