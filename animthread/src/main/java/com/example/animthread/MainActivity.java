package com.example.animthread;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button, button1;
    ArrayList<Drawable> drawableList = new ArrayList<Drawable>();
    Handler handler = new Handler();
    boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);

        drawableList.add(getResources().getDrawable(R.drawable.face1));
        drawableList.add(getResources().getDrawable(R.drawable.face2));
        drawableList.add(getResources().getDrawable(R.drawable.face3));
        drawableList.add(getResources().getDrawable(R.drawable.face4));
        drawableList.add(getResources().getDrawable(R.drawable.face5));

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                running = true;
                MyThread myThread = new MyThread();
                myThread.start();
            }
        });

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                running = false;
            }
        });

        // 내부클래스로 스레드 만들기
        class MyThread extends Thread {
            int index;
            @Override
            public void run() {
                while(running){
                    index++;
                    ;if(index > 4)
                        index = 0;
//                    SystemClock.sleep(1000);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageDrawable(drawableList.get(index));
                        }
                    });
                }
            }
        }
    }
}
