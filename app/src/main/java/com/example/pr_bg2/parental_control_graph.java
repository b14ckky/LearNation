package com.example.pr_bg2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.pr_bg2.Models.SendData;
import com.github.mikephil.charting.charts.BarChart;
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

import java.util.ArrayList;

public class parental_control_graph extends AppCompatActivity {
    //initialize array list
    public ArrayList<BarEntry> barEntries = new ArrayList<>();

    //use of loop
    public  ArrayList<BarEntry> marks = new ArrayList<>();
    BarChart barChart;
    DatabaseReference databaseReference,databaseReference2;
    public int wrong,wrong2;
    public int corr,corr2;

    @Override
    public void onBackPressed() {
        Intent back_bt = new Intent(parental_control_graph.this, parental_control_home.class);
        startActivity(back_bt);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parental_control_graph);


        barChart = findViewById(R.id.bar_chart);
        //Toast.makeText(this, "Before Database "+corr, Toast.LENGTH_SHORT).show();
        std_data_fetch();

        //Toast.makeText(parental_control_graph.this, "After Database "+corr2, Toast.LENGTH_SHORT).show();


        }


    private void std_data_fetch() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
        // For Standard 1
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid= user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("UsersTest").child(currentUserid).child("std1_test_counter");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot sn : snapshot.getChildren())
                {
                    if (sn.exists())
                    {
                        SendData sendData = snapshot.getValue(SendData.class);
                        corr = sendData.getCorrect();
                        wrong = sendData.getWrong();

                        marks.add(new BarEntry(1,corr));
                        BarDataSet barDataSet = new BarDataSet(marks, "Standards");
                        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        barDataSet.setValueTextColor(Color.BLACK);
                        barDataSet.setValueTextSize(16f);
                        BarData barData= new BarData(barDataSet);
                        barChart.setFitBars(true); barChart.setData(barData);
                        barChart.getDescription().setText("Standards");
                        barChart.animateY(1000);
                        pd.dismiss();
                    }
                    else
                    {
                        Toast.makeText(parental_control_graph.this, "Please Give Exam First...!!", Toast.LENGTH_SHORT).show();
                    }



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    //////////
        pd.show();

        databaseReference2 = FirebaseDatabase.getInstance().getReference("UsersTest").child(currentUserid).child("std2_test_counter");
        databaseReference2.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            for (DataSnapshot sn : snapshot.getChildren())
            {
                if (snapshot.exists())
                {
                    SendData sendData = snapshot.getValue(SendData.class);
                    corr2 = sendData.getCorrect();
                    wrong2 = sendData.getWrong();


                    marks.add(new BarEntry(2,corr2));
                    BarDataSet barDataSet = new BarDataSet(marks, "Standards");
                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setValueTextSize(16f);
                    BarData barData= new BarData(barDataSet);
                    barChart.setFitBars(true); barChart.setData(barData);
                    barChart.getDescription().setText("Standards");
                    barChart.animateY(1000);
                    pd.dismiss();
                }
                else
                {
                    Toast.makeText(parental_control_graph.this, "Please Give Exam First...!!", Toast.LENGTH_SHORT).show();
                }


            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });


}

}