package com.example.decoration.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.decoration.MainActivity;
import com.example.decoration.R;

public class LaunchActivity extends Activity {

    private Handler handler;
    private double runTime = 6;
    private TextView timeView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        timeView = findViewById(R.id.tv_time);
        imageView = findViewById(R.id.launch_img);
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String time = String.format("倒计时：%d", (int) runTime);
                if (runTime / 2 == 2) {
                    imageView.setBackground(getDrawable(R.drawable.launch_pic_2));
                } else if (runTime / 2 == 1) {
                    imageView.setBackground(getDrawable(R.drawable.launch_pic_3));
                }
                runTime = runTime - 1;
                if (runTime == 0) {
                    startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                    finish();
                }
                timeView.setText(time);
                handler.postDelayed(this, 1000);
            }
        });
    }
}