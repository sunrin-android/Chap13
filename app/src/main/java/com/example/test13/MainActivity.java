package com.example.test13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 개발자 스레드 실행
                BackgroundThread myThread = new BackgroundThread();
                myThread.start();
            }
        });
    }

    class BackgroundThread extends Thread {
        @Override
        public void run() {
            int count = 0;
            for(int i = 0; i < 10; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                Log.d("help", "Thread count value = " + count);
//                textView.setText(String.valueOf(count));
            }
        }
    }
}
