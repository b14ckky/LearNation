package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class gamification_std1_sub1_screen extends AppCompatActivity {
    public void maths()
    {
        Button maths = (Button)findViewById(R.id.sub2_maths);
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent maths = new Intent(gamification_std1_sub1_screen.this, gamification_std1_sub1_maths.class);
                startActivity(maths);
                finish();
            }

        });
    }
    public void back_bt()
    {
        Button maths = (Button)findViewById(R.id.back_bt);
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent maths = new Intent(gamification_std1_sub1_screen.this, gamification_screen.class);
                startActivity(maths);
                finish();
            }

        });
    }

    @Override
    public void onBackPressed() {
        Intent maths = new Intent(gamification_std1_sub1_screen.this, gamification_screen.class);
        startActivity(maths);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamification_std1_sub1_screen);
        back_bt();
        maths();
    }
}