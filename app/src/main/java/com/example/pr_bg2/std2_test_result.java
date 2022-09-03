package com.example.pr_bg2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr_bg2.Models.SendData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class std2_test_result extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView resultText;
    TextView all_ques;
    DatabaseReference databaseReference;
    int wrong2;
    int corr2;
    Button show_ans;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(std2_test_result.this,standards_test_result.class);
        startActivity(intent);
        finish();
    }
    public void back_bt()
    {
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(std2_test_result.this,standards_test_result.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std2_test_result);
        back_bt();


        show_ans = findViewById(R.id.std2_show_ans_btn);
        show_ans.setClickable(false);


        ProgressDialog pd = new ProgressDialog(std2_test_result.this);
        pd.setMessage("loading");
        pd.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid= user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("UsersTest").child(currentUserid).child("std2_test_counter");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot sn : snapshot.getChildren())
                {
                    SendData sendData = snapshot.getValue(SendData.class);
                    corr2 = sendData.getCorrect();
                    wrong2 = sendData.getWrong();
                }
                pd.dismiss();
                if (corr2 == 0)
                {
                    Toast.makeText(std2_test_result.this, "Please give quiz first !! ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    show_ans.setClickable(true);
                    show_ans.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(std2_test_result.this,std2_Answers.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    //num_right.setText(correct);
                    //num_wrong.setText(wrong);

                }
                circularProgressBar = findViewById(R.id.circularProgressBar);
                resultText  =findViewById(R.id.resultText);

                circularProgressBar.setProgress(corr2);
                resultText.setText(corr2+"/10");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}
