package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class nav_about_us extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent intent2 = new Intent(nav_about_us.this,home_screen.class);
        startActivity(intent2);
        finish();
    }
    public void back_bt()
    {
        Button back_bt=(Button) findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nav_about_us.this,home_screen.class);
                startActivity(intent);
                finish();
            }
        });
    }
    TextView name;
    CardView c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_about_us);
        back_bt();
        c1 = findViewById(R.id.cardv1);

        name = findViewById(R.id.name);
        String data = "Teaching is one of the most challenging and complex jobs on the planet. \n" +
                "\n" +
                "Our digital resources, tools, and learning materials are useful in any type of teaching moment and many can be used to support national education standards.\n" +
                "\n" +
                "So , we are 4 Students of Branch Diploma Comptuper 6th Sem from MARWADI UNIVERSITY .\n" +
                "\n" +
                "Our Mission is to give free & Quality Education to every student & Our Motto is '' Education Is Our Right '' \n" ;

        name.setText(data);
    }
}