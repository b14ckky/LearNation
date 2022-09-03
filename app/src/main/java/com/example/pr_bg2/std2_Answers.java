package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class std2_Answers extends AppCompatActivity {
    TextView name;
    CardView c1;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(std2_Answers.this,std2_test_result.class);
        startActivity(intent);
        finish();
    }
    public void back_bt()
    {
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(std2_Answers.this,std2_test_result.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std2_answers);


        c1 = findViewById(R.id.cardv1);

        name = findViewById(R.id.name);
        String data = "\n" +
                "What did the children bring for tiny birds? ans = Bread\n" +
                "\n" +
                "The balloons are tied together with? ans = Strings\n" +
                "\n" +
                "भारत में आ कर मीरा बहन किसके साथ काम करने लगी? ans = गांधीजी के साथ\n" +
                "\n" +
                "लेखक क्या बनकर तारों को अकड़ दिखाना चाहता है? ans = चांद\n" +
                "\n" +
                "What should we avoid to maintain good health? ans  = Junk food\n" +
                "\n" +
                "Which of the following thing can hurt you? ans = Knife\n" +
                "\n" +
                "We get eggs from? ans = Hen\n" +
                "\n" +
                "Which number comes just before 9990? ans = 9989\n" +
                "\n" +
                "The place value of 2 in 2548 is? ans = 2000\n" +
                "\n" +
                "In roman numerals M means? ans = 1000\n" ;

        name.setText(data);
    }
}