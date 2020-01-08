package com.example.mybooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splashscreen extends AppCompatActivity {

    private Handler waitHandler = new Handler();//declare handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        waitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //The following code will execute after the 5 seconds.
                try {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 3000);//give 3 sec delay
    }

}
