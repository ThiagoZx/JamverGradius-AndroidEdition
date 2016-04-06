package com.example.thiagotorres.jamvergradius;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(new GradiusView(this));
    }

}