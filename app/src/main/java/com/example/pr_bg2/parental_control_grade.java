package com.example.pr_bg2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr_bg2.Models.SendData;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class parental_control_grade extends AppCompatActivity {

    DatabaseReference databaseReference,databaseReference2;
    int wrong,wrong2;
    int corr,corr2;
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,textView12;
    TextView  std1_cardview_tv,
            std2_cardview_tv,
            std3_cardview_tv,
            std4_cardview_tv,
            std5_cardview_tv,
            std6_cardview_tv,
            std7_cardview_tv,
            std8_cardview_tv,
            std9_cardview_tv,
            std10_cardview_tv,
            std11_cardview_tv,
            std12_cardview_tv;

    CardView std1_cardview,
            std2_cardview,
            std3_cardview,
            std4_cardview,
            std5_cardview,
            std6_cardview,
            std7_cardview,
            std8_cardview,
            std9_cardview,
            std10_cardview,
            std11_cardview,
            std12_cardview;




    @Override
    public void onBackPressed() {
        Intent back_bt = new Intent(parental_control_grade.this, parental_control_home.class);
        startActivity(back_bt);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parental_control_grade);

        Hooks();
        std1_set_grade();
        std2_set_grade();
    }

    private void std1_set_grade() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid= user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("UsersTest").child(currentUserid).child("std1_test_counter");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot sn : snapshot.getChildren())
                {
                    SendData sendData = snapshot.getValue(SendData.class);
                    corr = sendData.getCorrect();
                    wrong = sendData.getWrong();
                    if (corr <= 10 && corr >=9)
                    {
                        textView1.setText("O");
                        std1_cardview.setCardBackgroundColor(getResources().getColor(R.color.green));
                        std1_cardview_tv.setText("Congratulation !!");
                    }
                    else if (corr <= 8 && corr >=7)
                    {
                        textView1.setText("A");
                        std1_cardview.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        std1_cardview_tv.setText("Keep Going !!");
                    }
                    else if (corr <= 6 && corr >=5)
                    {
                        textView1.setText("B");
                        std1_cardview.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                        std1_cardview_tv.setText("Keep Maintaining !!");
                    }
                    else if (corr <= 4 && corr >=3)
                    {
                        textView1.setText("C");
                        std1_cardview.setCardBackgroundColor(getResources().getColor(R.color.orange));
                        std1_cardview_tv.setText("Need Improvement");
                    }
                    else if (corr < 3 )
                    {
                        textView1.setText("D");
                        std1_cardview.setCardBackgroundColor(getResources().getColor(R.color.red));
                        std1_cardview_tv.setText("Fail !!");
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void std2_set_grade() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid= user.getUid();
        databaseReference2 = FirebaseDatabase.getInstance().getReference("UsersTest").child(currentUserid).child("std2_test_counter");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot sn : snapshot.getChildren())
                {
                    if (sn.exists())
                    {
                        SendData sendData = snapshot.getValue(SendData.class);
                        corr2 = sendData.getCorrect();
                        wrong2 = sendData.getWrong();

                        if (corr2 <= 10 && corr2 >=9)
                        {
                            textView2.setText("O");
                            std2_cardview.setCardBackgroundColor(getResources().getColor(R.color.green));
                            std2_cardview_tv.setText("Congratulation !!");
                        }
                        else if (corr2 <= 8 && corr2 >=7)
                        {
                            textView2.setText("A");
                            std2_cardview.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            std2_cardview_tv.setText("Keep Going !!");
                        }
                        else if (corr2 <= 6 && corr2 >=5)
                        {
                            textView2.setText("B");
                            std2_cardview.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                            std2_cardview_tv.setText("Keep Maintaining !!");
                        }
                        else if (corr2 <= 4 && corr2 >=3)
                        {
                            textView2.setText("C");
                            std2_cardview.setCardBackgroundColor(getResources().getColor(R.color.orange));
                            std2_cardview_tv.setText("Need Improvement");
                        }
                        else if (corr2 < 3 )
                        {
                            textView2.setText("D");
                            std2_cardview.setCardBackgroundColor(getResources().getColor(R.color.red));
                            std2_cardview_tv.setText("Fail !!");
                        }
                    }
                    else
                    {
                        Toast.makeText(parental_control_grade.this, "Please Give Exam First...!!", Toast.LENGTH_SHORT).show();
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void Hooks() {
        std1_cardview = findViewById(R.id.std1_cardview);
        std2_cardview = findViewById(R.id.std2_cardview);
        std3_cardview = findViewById(R.id.std3_cardview);
        std4_cardview = findViewById(R.id.std4_cardview);
        std5_cardview = findViewById(R.id.std5_cardview);
        std6_cardview = findViewById(R.id.std6_cardview);
        std7_cardview = findViewById(R.id.std7_cardview);
        std8_cardview = findViewById(R.id.std8_cardview);
        std8_cardview = findViewById(R.id.std8_cardview);
        std9_cardview = findViewById(R.id.std9_cardview);
        std10_cardview = findViewById(R.id.std10_cardview);
        std11_cardview = findViewById(R.id.std11_cardview);
        std12_cardview = findViewById(R.id.std12_cardview);

        std1_cardview_tv = findViewById(R.id.std1_cardview_tv);
        std2_cardview_tv = findViewById(R.id.std2_cardview_tv);
        std3_cardview_tv = findViewById(R.id.std3_cardview_tv);
        std4_cardview_tv = findViewById(R.id.std4_cardview_tv);
        std5_cardview_tv = findViewById(R.id.std5_cardview_tv);
        std6_cardview_tv = findViewById(R.id.std6_cardview_tv);
        std7_cardview_tv = findViewById(R.id.std7_cardview_tv);
        std8_cardview_tv = findViewById(R.id.std8_cardview_tv);
        std9_cardview_tv = findViewById(R.id.std9_cardview_tv);
        std10_cardview_tv = findViewById(R.id.std10_cardview_tv);
        std11_cardview_tv = findViewById(R.id.std11_cardview_tv);
        std12_cardview_tv = findViewById(R.id.std12_cardview_tv);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);
        textView12 = findViewById(R.id.textView12);

    }
}