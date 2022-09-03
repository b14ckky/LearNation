package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class standards_test_result extends AppCompatActivity {

    public void back_bt()
    {
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentIntent = new Intent(standards_test_result.this, home_screen.class);
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
                Intent navigation_bt = new Intent(standards_test_result.this, std1_test_result.class);
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
                Intent navigation_bt = new Intent(standards_test_result.this, std2_test_result.class);
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
                Intent navigation_bt = new Intent(standards_test_result.this, std3_test_result.class);
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
                Intent navigation_bt = new Intent(standards_test_result.this, std4_test_result.class);
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
                Intent navigation_bt = new Intent(standards_test_result.this, std5_test_result.class);
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
                Intent navigation_bt = new Intent(standards_test_result.this, std6_test_result.class);
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
                Intent navigation_bt = new Intent(standards_test_result.this, std7_test_result.class);
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
                Intent navigation_bt = new Intent(standards_test_result.this, std8_test_result.class);
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
                Intent navigation_bt = new Intent(standards_test_result.this, std9_test_result.class);
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
                Intent navigation_bt = new Intent(standards_test_result.this, std10_test_result.class);
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
                Intent navigation_bt = new Intent(standards_test_result.this, std11_test_result.class);
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
                Intent navigation_bt = new Intent(standards_test_result.this, std12_test_result.class);
                startActivity(navigation_bt);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standards_test_result);
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

        Intent currentIntent = new Intent(standards_test_result.this, home_screen.class);
        startActivity(currentIntent);
        finish();
    }

}
