package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class std1_sub1_ebooks extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(std1_sub1_ebooks.this,std1_sub1.class);
        startActivity(intent);
        finish();
    }

    public void back_bt()
    {
        Button back_bt=(Button) findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(std1_sub1_ebooks.this,std1_sub1.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void hindi()
    {
        Button sub1_hindi=(Button) findViewById(R.id.sub1_hindi);
        sub1_hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(std1_sub1_ebooks.this,std1_sub1_ebooks_hindi_pdfs.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void english()
    {
        Button sub1_english=(Button) findViewById(R.id.sub1_english);
        sub1_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(std1_sub1_ebooks.this,std1_sub1_ebooks_english_pdfs.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void maths()
    {
        Button sub1_maths=(Button) findViewById(R.id.sub1_maths);
        sub1_maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(std1_sub1_ebooks.this,std1_sub1_ebooks_maths_pdfs.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std1_sub1_ebooks);

        back_bt();
        hindi();
        english();
        maths();

    }
}