package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class std7_sub1 extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent currentIntent = new Intent(std7_sub1.this, all_standards_screen.class);
        startActivity(currentIntent);
        finish();
    }

    public void back_bt()
    {
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentIntent = new Intent(std7_sub1.this, all_standards_screen.class);
                startActivity(currentIntent);
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std7_sub1);

        back_bt();
    }
}