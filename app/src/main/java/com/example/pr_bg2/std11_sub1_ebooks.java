package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class std11_sub1_ebooks extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(std11_sub1_ebooks.this,std11_sub1.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std11_sub1_ebooks);
        Button back_bt=(Button) findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(std11_sub1_ebooks.this,std10_sub1.class);
                startActivity(intent);
                finish();
            }
        });

    }
}