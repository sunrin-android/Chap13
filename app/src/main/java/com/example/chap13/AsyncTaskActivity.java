package com.example.test13;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 스레드 실행
                MyAsyncTask my = new MyAsyncTask();
                my.execute();
            }
        });
    }

    // 선언
    class MyAsyncTask extends AsyncTask<Void, Integer, String> {
        int count;
        @Override
        protected void onPreExecute() { // 스레드 실행 전에 호출
            super.onPreExecute();
            textView.setText("카운트 시작");
        }

        @Override
        protected String doInBackground(Void... voids) { // 스레드 실행 내용
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                // 핸들러 역할
                publishProgress(count);
            }
            return "Finish";
        }

        @Override
        protected void onProgressUpdate(Integer... values) { // 스레드 실행 중 변경 내용 update
            super.onProgressUpdate(values);
            textView.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }
}
