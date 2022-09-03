package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class parental_control_home extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent back_bt = new Intent(parental_control_home.this, home_screen.class);
        startActivity(back_bt);
        finish();
    }
    public void back_bt() {
        Button back_bt = (Button) findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_bt = new Intent(parental_control_home.this, home_screen.class);
                startActivity(back_bt);
                finish();
            }
        });
    }
    public void result() {
        Button result = (Button) findViewById(R.id.pc_result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = new Intent(parental_control_home.this, standards_test_result.class);
                startActivity(result);
                finish();
            }

        });
    }
    public void graph() {
        Button graph = (Button) findViewById(R.id.pc_graph);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent graph = new Intent(parental_control_home.this, parental_control_graph.class);
                startActivity(graph);
                finish();
            }

        });
    }

    public void line() {
        Button graph = (Button) findViewById(R.id.line_graph);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent graph = new Intent(parental_control_home.this, parental_control_line.class);
                startActivity(graph);
                finish();
            }

        });
    }
    public void grade() {
        Button graph = (Button) findViewById(R.id.pc_grade);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent graph = new Intent(parental_control_home.this, parental_control_grade.class);
                startActivity(graph);
                finish();
            }

        });
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parental_control_home);
        back_bt();
        result();
        line();
        graph();
        grade();
    }
}