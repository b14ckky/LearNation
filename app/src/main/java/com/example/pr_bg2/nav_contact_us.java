package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class nav_contact_us extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent intent2 = new Intent(nav_contact_us.this,home_screen.class);
        startActivity(intent2);
        finish();
    }
    public void back_bt()
    {
        Button back_bt=(Button) findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nav_contact_us.this,home_screen.class);
                startActivity(intent);
                finish();
            }
        });
    }
    TextView name2,email1,email2,website;
    CardView c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_contact_us);
        c1 = findViewById(R.id.cardv1);
        back_bt();

        name2 = findViewById(R.id.name2);
        email1 = findViewById(R.id.email1);
        email2 = findViewById(R.id.email2);
        website = findViewById(R.id.website);

        String data = "For Your Valuable feedback & To Answer your every question Please Contact us On :-";
        String data2 = "tejasya.vasani107920@marwadiuniversity.ac.in";
        String data3 = "jeel.nariya107888@marwadiuniversity.ac.in";
        String data4 = "https://learnation.000webhostapp.com/LearNation/index.html\n";

        name2.setText(data);
        email1.setText(data2);
        email2.setText(data3);
        website.setText(data4);
    }
}