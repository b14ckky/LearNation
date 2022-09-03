package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class std2_test_home_screen extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent back_bt = new Intent(std2_test_home_screen.this, std2_sub1.class);
        startActivity(back_bt);
        finish();
    }
    public void back_bt()
    {
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_bt = new Intent(std2_test_home_screen.this, std2_sub1.class);
                startActivity(back_bt);
                finish();
            }
        });
    }
    public void std1_test_start()
    {
        Button std1_test_start = (Button)findViewById(R.id.std2_test_start);
        std1_test_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent std1_test_start = new Intent(std2_test_home_screen.this, std2_test_mcq_screen.class);
                startActivity(std1_test_start);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std2_test_home_screen);

        back_bt();
        std1_test_start();
    }
}