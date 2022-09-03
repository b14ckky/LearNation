package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class std2_sub1 extends AppCompatActivity {

    public void onBackPressed() {
        Intent currentIntent = new Intent(std2_sub1.this, all_standards_screen.class);
        startActivity(currentIntent);
        finish();
    }

    public void back_bt()
    {
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentIntent = new Intent(std2_sub1.this, all_standards_screen.class);
                startActivity(currentIntent);
                finish();
            }
        });
    }
    public void ebooks()
    {
        Button std2_ebooks = (Button)findViewById(R.id.std2_ebooks);
        std2_ebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent std2_ebooks = new Intent(std2_sub1.this, std2_sub1_ebooks.class);
                startActivity(std2_ebooks);
                finish();
            }

        });
    }
    public void test()
    {
        Button std2_test = (Button)findViewById(R.id.std2_test);
        std2_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent std2_test = new Intent(std2_sub1.this, std2_test_home_screen.class);
                startActivity(std2_test);
                finish();
            }

        });
    }
    public void std2_doubt()
    {
        Button std1_doubt = (Button)findViewById(R.id.std1_doubt);
        std1_doubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent std1_doubt = new Intent(std2_sub1.this, fragment2.class);
                startActivity(std1_doubt);
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std2_sub1);

        ebooks();
        test();
        back_bt();
        std2_doubt();

    }
}
