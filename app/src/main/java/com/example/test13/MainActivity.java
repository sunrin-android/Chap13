package com.example.test13;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
//    MyHandler myHandler;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3. 개발자 스레드 실행
//                BackgroundThread myThread = new BackgroundThread();
//                myThread.start();
                // 3-2. 스레드 실행 (Runnable)
                MyRunnable myRunnable = new MyRunnable();
                Thread thread = new Thread(myRunnable);
//                Thread thread = new Thread(new MyRunnable());
                thread.start();
            }
        });

        // 핸들러 생성
//        myHandler = new MyHandler();
        handler = new Handler();
    }

    // 1-2. 스레드 만들기 (Runnable 인터페이스 구현)
    class MyRunnable implements Runnable {
        int count;
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                Log.d("help!", "Thread count value = " + count);

                Message message = new Message();
                message.arg1 = count;
//                myHandler.sendMessage(message);
                handler.sendMessage(message);
//                textView.setText(String.valueOf(count));
            }
        }
        class MyRunnable2 implements Runnable {
            @Override
            public void run() {
                textView.setText(String.valueOf(count));
            }
        }
    }


    // 핸들러 만들기 (메인스레드)
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText(String.valueOf(msg.arg1));
        }
    }

    // 1. 스레드 만들기 (상속)
//    class BackgroundThread extends Thread {
//        @Override
//        public void run() {
//            int count = 0;
//            for(int i = 0; i < 10; i++){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                count++;
//                Log.d("help", "Thread count value = " + count);
////                textView.setText(String.valueOf(count));
//            }
//        }
//    }
}
