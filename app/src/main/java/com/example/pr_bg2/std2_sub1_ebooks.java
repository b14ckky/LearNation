package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class std2_sub1_ebooks extends AppCompatActivity {
    public void onBackPressed() {
        Intent back_bt = new Intent(std2_sub1_ebooks.this, all_standards_screen.class);
        startActivity(back_bt);
        finish();
    }
    public void back_bt()
    {
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_bt = new Intent(std2_sub1_ebooks.this, all_standards_screen.class);
                startActivity(back_bt);
                finish();
            }

        });
    }
    public void sub2_hindi()
    {
        Button sub2_hindi = (Button) findViewById(R.id.sub2_hindi);
        sub2_hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_bt = new Intent(std2_sub1_ebooks.this, std2_sub1_ebooks_hindi_pdfs.class);
                startActivity(back_bt);
                finish();
            }
        });
    }
    public void sub2_english()
    {
        Button sub2_english = (Button) findViewById(R.id.sub2_english);
        sub2_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_bt = new Intent(std2_sub1_ebooks.this, std2_sub1_ebooks_english_pdfs.class);
                startActivity(back_bt);
                finish();
            }
        });
    }
    public void sub2_maths()
    {
        Button sub2_maths = (Button) findViewById(R.id.sub2_maths);
        sub2_maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_bt = new Intent(std2_sub1_ebooks.this, std2_sub1_ebooks_maths_pdfs.class);
                startActivity(back_bt);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std2_sub1_ebooks);

        back_bt();
        sub2_hindi();
        sub2_english();
        sub2_maths();

    }
}