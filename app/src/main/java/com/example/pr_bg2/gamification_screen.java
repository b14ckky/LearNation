package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class gamification_screen extends AppCompatActivity {
    public void onBackPressed() {
        Intent back_bt = new Intent(gamification_screen.this, home_screen.class);
        startActivity(back_bt);
        finish();
    }
    public void back_bt()
    {
        Button back_bt=(Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_bt = new Intent(gamification_screen.this, home_screen.class);
                startActivity(back_bt);
                finish();
            }
        });
    }
    public void std1()
    {
        Button std_1_bt=(Button)findViewById(R.id.std_1_bt);
        std_1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent std_1_bt = new Intent(gamification_screen.this, gamification_std1_sub1_screen.class);
                startActivity(std_1_bt);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamification_screen);
        back_bt();
        std1();




    }
    }
