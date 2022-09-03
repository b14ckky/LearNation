package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class all_standards_screen extends AppCompatActivity {

    public void back_bt()
    {
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentIntent = new Intent(all_standards_screen.this, home_screen.class);
                startActivity(currentIntent);
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
                Intent navigation_bt = new Intent(all_standards_screen.this, std1_sub1.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }
    public void std2()
    {
        Button std_1_bt=(Button)findViewById(R.id.std_2_bt);
        std_1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation_bt = new Intent(all_standards_screen.this, std2_sub1.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }
    public void std3()
    {
        Button std_1_bt=(Button)findViewById(R.id.std_3_bt);
        std_1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation_bt = new Intent(all_standards_screen.this, std3_sub1.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }
    public void std4()
    {
        Button std_1_bt=(Button)findViewById(R.id.std_4_bt);
        std_1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation_bt = new Intent(all_standards_screen.this, std4_sub1.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }
    public void std5()
    {
        Button std_1_bt=(Button)findViewById(R.id.std_5_bt);
        std_1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation_bt = new Intent(all_standards_screen.this, std5_sub1.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }
    public void std6()
    {
        Button std_1_bt=(Button)findViewById(R.id.std_6_bt);
        std_1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation_bt = new Intent(all_standards_screen.this, std6_sub1.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }
    public void std7()
    {
        Button std_1_bt=(Button)findViewById(R.id.std_7_bt);
        std_1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation_bt = new Intent(all_standards_screen.this, std7_sub1.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }
    public void std8()
    {
        Button std_1_bt=(Button)findViewById(R.id.std_8_bt);
        std_1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation_bt = new Intent(all_standards_screen.this, std8_sub1.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }
    public void std9()
    {
        Button std_1_bt=(Button)findViewById(R.id.std_9_bt);
        std_1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation_bt = new Intent(all_standards_screen.this, std9_sub1.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }
    public void std10()
    {
        Button std_1_bt=(Button)findViewById(R.id.std_10_bt);
        std_1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation_bt = new Intent(all_standards_screen.this, std10_sub1.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }
    public void std11()
    {
        Button std_1_bt=(Button)findViewById(R.id.std_11_bt);
        std_1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation_bt = new Intent(all_standards_screen.this, std11_sub1.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }
    public void std12()
    {
        Button std_1_bt=(Button)findViewById(R.id.std_12_bt);
        std_1_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation_bt = new Intent(all_standards_screen.this, std12_sub1.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.activity_all_standards_screen);
        back_bt();
        std1();
        std2();
        std3();
        std4();
        std5();
        std6();
        std7();
        std8();
        std9();
        std10();
        std11();
        std12();


    }
    @Override
    public void onBackPressed() {

        Intent currentIntent = new Intent(all_standards_screen.this, home_screen.class);
        startActivity(currentIntent);
        finish();
    }

}
