package com.example.moskit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private int layoutWidth;
    private int layoutHeight;
    int count = 0;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private Timer timersegons = new Timer();

    ImageView iv_mosquit;
    AnimationDrawable mosquit_animat;

    Button button_start;
    TextView contador, tv_puntuacio;

    float iv_mosquitX;
    float iv_mosquitY;

    int puntuacio = 0;
    boolean mort = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mosquit_animat = new AnimationDrawable();
        iv_mosquit = (ImageView) findViewById(R.id.iv_mosquit);
        button_start = (Button) findViewById(R.id.button_start);
        contador = (TextView) findViewById(R.id.contador);
        tv_puntuacio = (TextView) findViewById(R.id.tv_puntuacio);


        iv_mosquit.setX(0);
        iv_mosquit.setY(0);
        iv_mosquit.setBackgroundResource(R.drawable.mosquit_animat);
        mosquit_animat = (AnimationDrawable) iv_mosquit.getBackground();
        mosquit_animat.start();
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        layoutWidth = size.x;
        layoutHeight = size.y;



        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresiu();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(contador.getText().equals("0")){
                                    contador.setText("30");
                                    tv_puntuacio.setText("0");
                                    quiet();

                                }else {
                                    mouremosquit();
                                }
                            }
                        });
                    }
                }, 0, 200);
            }
        });

    }


    public void mort(View view) {

        iv_mosquit.setBackgroundResource(R.drawable.sang_animat);
        mosquit_animat = (AnimationDrawable) iv_mosquit.getBackground();
        mosquit_animat.start();

        mort = false;
        puntuacio = Integer.parseInt(tv_puntuacio.getText().toString());
        puntuacio++;
        tv_puntuacio.setText(String.valueOf(puntuacio));

        reviure();
    }

    public void reviure() {

        if (mort == false) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    iv_mosquit.setBackgroundResource(R.drawable.mosquit_animat);
                    mosquit_animat = (AnimationDrawable) iv_mosquit.getBackground();
                    mosquit_animat.start();
                }
            }, 2000);
        }

    }

    public void mouremosquit(){

        Random r = new Random();

        float X = 700;
        float Y = 2000;

        float random = X + r.nextFloat() * (X - Y);
        float random2 = X + r.nextFloat() * (X - Y);

        iv_mosquit.setX(random);
        iv_mosquit.setY(random2);

    }
    public void quiet(){


        iv_mosquit.setX(100);
        iv_mosquit.setY(100);

    }
    public void regresiu() {

        timersegons.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int numero = Integer.parseInt(contador.getText().toString());
                        numero--;
                        String numseg = String.valueOf(numero);
                        contador.setText(numseg);
                    }
                });
            }
        }, 1000, 1000);
    }
}