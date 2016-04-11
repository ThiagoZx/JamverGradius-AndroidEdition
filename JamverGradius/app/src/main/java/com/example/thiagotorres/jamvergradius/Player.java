package com.example.thiagotorres.jamvergradius;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Thiago.Torres on 08/04/2016.
 */
public class Player {

    //74X100 = collision dimention;
    //74x123 = drawable dimention, for canvas limit;
    private Paint paint = new Paint();
    private int posX, posY = 0;
    private int currentFrame = 0;
    private Bitmap image;

    public Player(Bitmap bitmap) {
        image = bitmap;
    }

    void drawPlayer(Canvas canvas){
        Bitmap sprite = Bitmap.createBitmap(image, (image.getWidth() / 10) * currentFrame, 0, (image.getWidth() / 10), image.getHeight());
        canvas.drawBitmap(sprite, posX, posY, paint);
        currentFrame = currentFrame + 1;
        if (currentFrame > 9) currentFrame = 0;
    }

    void updatePlayer(int x, int y){
        posX -= x * 2;
        posY += y * 3;
    }

}
