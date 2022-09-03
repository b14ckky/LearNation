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

import org.w3c.dom.Text;

public class std1_Answers extends AppCompatActivity {
    TextView name;
    CardView c1;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(std1_Answers.this,std1_test_result.class);
        startActivity(intent);
        finish();
    }
    public void back_bt()
    {
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(std1_Answers.this,std1_test_result.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std1_answers);


        c1 = findViewById(R.id.cardv1);

        name = findViewById(R.id.name);
        String data = "Which of the following is a natural thing? ans = Rock\n" +
                "\n" +
                "Which of the following is living thing? ans = Ant\n" +
                "\n" +
                "शेर के बच्चे ने पेड़ की डाल क्यों पकड़ी? ans = गिरने से बचने के लिए\n" +
                "\n" +
                "हवा ने सूरज को किस कारण नमस्कार किया? ans = सूरज की ताकत देख कर\n" +
                "\n" +
                "What did the sun do to get the man’s coat off?\" ans = The sun started shining hard\n" +
                "\n" +
                "What sound does a dog make? ans = Barks\n" +
                "\n" +
                "Who does all the mischiefs in the house? ans = Mr. Nobody\n" +
                "\n" +
                "Which of the following add upto 20? ans = 10+10\n" +
                "\n" +
                "Rita collected 8 stamps. Neha has collected 7. How many stamps do both of them have? ans = 15\n" +
                "\n" +
                "10x5=? ans = 50" ;

        name.setText(data);
    }
}