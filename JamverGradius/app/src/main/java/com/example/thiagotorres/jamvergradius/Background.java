package com.example.thiagotorres.jamvergradius;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Thiago on 10/04/2016.
 */
public class Background {

    Bitmap background;
    Bitmap stars;
    Paint paint = new Paint();
    int currentFrame = 0;

    public Background (Bitmap back, Bitmap front) {
        background = back;
        stars = front;
    }

    void updateBackground(){
        currentFrame -= 1;
        if (currentFrame < -960) currentFrame = 0;
    }

    void drawBackground(Canvas canvas){
        Bitmap screen = Bitmap.createBitmap(background, 0, 0, canvas.getWidth(), canvas.getHeight());
        Bitmap paralax = Bitmap.createBitmap(stars, 0, currentFrame, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(screen, 0, 0, paint);
        canvas.drawBitmap(paralax, 0, 0, paint);
    }
}
