package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class std1_sub1 extends AppCompatActivity {
    public void onBackPressed() {
        Intent back_bt = new Intent(std1_sub1.this, all_standards_screen.class);
        startActivity(back_bt);
        finish();
    }
    public void back_bt()
    {
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_bt = new Intent(std1_sub1.this, all_standards_screen.class);
                startActivity(back_bt);
                finish();
            }

        });
    }
    public void std1_video()
    {
        Button std1_video = (Button)findViewById(R.id.std3_video);
        std1_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent std1_video = new Intent(std1_sub1.this, std1_sub1_videos.class);
                startActivity(std1_video);
                finish();
            }
        });

    }
    public void std1_ebooks()
    {
        Button std1_video = (Button)findViewById(R.id.std3_ebooks);
        std1_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent std1_ebooks = new Intent(std1_sub1.this, std1_sub1_ebooks.class);
                startActivity(std1_ebooks);
                finish();
            }
        });

    }
    public void std1_test()
    {
        Button std1_test = (Button)findViewById(R.id.std1_test);
        std1_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent std3_test = new Intent(std1_sub1.this, std1_test_home_screen.class);
                startActivity(std3_test);
                finish();
            }
        });

    }

    public void std1_doubt()
    {
        Button std1_doubt = (Button)findViewById(R.id.std1_doubt);
        std1_doubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent std1_doubt = new Intent(std1_sub1.this, fragment2.class);
                startActivity(std1_doubt);
                finish();
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1_std1);

        back_bt();
        std1_video();
        std1_ebooks();
        std1_test();
        std1_doubt();




    }
}