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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class parental_control_line extends AppCompatActivity {
    LineChart mpLineChart;
    int corr, wrong,corr2,wrong2;
    DatabaseReference databaseReference, databaseReference2;

    @Override
    public void onBackPressed() {
        Intent back_bt = new Intent(parental_control_line.this, parental_control_home.class);
        startActivity(back_bt);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parental_control_line);

        mpLineChart = (LineChart) findViewById(R.id.line_chart);

        std_fetch_data();

    }

    private ArrayList<Entry> dataValues1() {
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        //Toast.makeText(this, "c1 = "+corr+" c2 = "+corr2, Toast.LENGTH_SHORT).show();
        dataVals.add(new Entry(1, corr));
        dataVals.add(new Entry(2, corr2));

        return dataVals;
    }

    public void std_fetch_data() {
        ProgressDialog pd = new ProgressDialog(this);
        //pd.setMessage("loading");
        //pd.show();
        // For Standard 1
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid= user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("UsersTest").child(currentUserid).child("std1_test_counter");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    for (DataSnapshot sn : snapshot.getChildren()) {
                        SendData sendData = snapshot.getValue(SendData.class);
                        corr = sendData.getCorrect();
                        wrong = sendData.getWrong();

                        databaseReference2 = FirebaseDatabase.getInstance().getReference("UsersTest").child(currentUserid).child("std2_test_counter");
                        databaseReference2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                if (snapshot2.exists())
                                {
                                    for (DataSnapshot sn2 : snapshot2.getChildren())
                                    {
                                        SendData sendData = snapshot2.getValue(SendData.class);
                                        corr2 = sendData.getCorrect();
                                        wrong2 = sendData.getWrong();

                                        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(), "Marks");
                                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                        dataSets.add(lineDataSet1);
                                        LineData data = new LineData(dataSets);
                                        mpLineChart.setData(data);
                                        mpLineChart.invalidate();
                                        pd.dismiss();
                                    }

                                }
                                else
                                {
                                    Toast.makeText(parental_control_line.this, "Please Give Exam First...!!", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(parental_control_line.this, "Please Give Exam First...!!", Toast.LENGTH_SHORT).show();
                                pd.dismiss();;
                            }
                        });


                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}