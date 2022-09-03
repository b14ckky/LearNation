package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class std2_sub1_ebooks_maths_pdfs extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent back_bt = new Intent(std2_sub1_ebooks_maths_pdfs.this, std2_sub1_ebooks.class);
        startActivity(back_bt);
        finish();
    }
    public void back_bt()
    {
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_bt = new Intent(std2_sub1_ebooks_maths_pdfs.this, std2_sub1_ebooks.class);
                startActivity(back_bt);
                finish();
            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std2_sub1_ebooks_maths_pdfs);

        back_bt();
    }
}