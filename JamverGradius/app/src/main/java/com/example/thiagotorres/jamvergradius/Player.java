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
    private int posX = 300;
    private int posY = 0;
    private int currentFrame = 0;
    private Bitmap image;
    private int shipSize;
    private int canvasWidth, canvasHeight;

    public Player(Bitmap bitmap) {
        image = bitmap;
        shipSize = image.getWidth() / 10;
    }

    int cannonPositionX(){
        return posX + image.getWidth() / 20;
    }

    int cannonPositionY(){
        return posY;
    }

    void drawPlayer(Canvas canvas){
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        Bitmap sprite = Bitmap.createBitmap(image, shipSize * currentFrame, 0, shipSize, image.getHeight());
        canvas.drawBitmap(sprite, posX, posY, paint);
    }

    private void canvasBoundary(){
        if (posX <= 0) {
            posX = 0;
        } else if(posX >= canvasWidth - shipSize){
            posX = canvasWidth - shipSize;
        }

        if (posY + 123 >= canvasHeight){
            posY = canvasHeight - 123;
        } else if (posY <= 0){
            posY = 0;
        }

    }

    void updatePlayer(int x, int y){

        posY += y * 3;
        posX -= x * 2;

        canvasBoundary();


        currentFrame = currentFrame + 1;
        if (currentFrame > 9) currentFrame = 0;
    }

}
