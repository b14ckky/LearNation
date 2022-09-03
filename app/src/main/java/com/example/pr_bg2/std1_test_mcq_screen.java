package com.example.pr_bg2;


import static com.example.pr_bg2.MainActivity.list;
import static com.example.pr_bg2.MainActivity.list2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr_bg2.Models.Modelclass;
import com.example.pr_bg2.Models.SendData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class std1_test_mcq_screen extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue = 20;
    int i = 0;
    RoundedHorizontalProgressBar progressBar;
    List<Modelclass> allQuestionsList;
    Modelclass modelclass;
    int index = 0;
    TextView card_question, optiona, optionb, optionc, optiond;
    CardView cardOA, cardOB, cardOC, cardOD;
    public static int correctCount = 0;
    public static int wrongCount = 0;
    LinearLayout nextBtn;
    SendData SendData;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    public void onBackPressed() {
        Intent back_bt = new Intent(std1_test_mcq_screen.this, std1_test_home_screen.class);
        startActivity(back_bt);
        finish();
    }

    public void back_bt() {
        Button back_bt = (Button) findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_bt = new Intent(std1_test_mcq_screen.this, std1_test_home_screen.class);
                startActivity(back_bt);
                finish();
            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std1_test_mcq_screen);


        Hooks();

        allQuestionsList = list;
        modelclass = list.get(index);
        nextBtn.setClickable(false);

        setAllData();


        progressBar = findViewById(R.id.quiz_timer);
        TextView mTextField = findViewById(R.id.mTextField);

        //1200000
        new CountDownTimer(50000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
        try {

    Dialog dialog = new Dialog(std1_test_mcq_screen.this);
    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    dialog.setContentView(R.layout.timeout_dialog);
    dialog.show();

        dialog.findViewById(R.id.dialog_retry).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(std1_test_mcq_screen.this, std1_test_home_screen.class);
            startActivity(intent);
             }
         });
        }
            catch (WindowManager.BadTokenException e)
            {
                Log.d("BadTokenException","Error message occurs...");
            }


            }


        }.start();

        /*countDownTimer = new CountDownTimer(1200000, 1000) {
            @Override
            public void onTick(long l) {
                timerValue = timerValue - 1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                if (wrongCount == 0 && correctCount == 0) {

                    Dialog dialog = new Dialog(std1_test_mcq_screen.this);
                    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                    dialog.setContentView(R.layout.timeout_dialog);
                    dialog.show();

                    dialog.findViewById(R.id.dialog_retry).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(std1_test_mcq_screen.this, std1_test_home_screen.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(std1_test_mcq_screen.this, "Your test is completed", Toast.LENGTH_SHORT).show();
                    Toast.makeText(std1_test_mcq_screen.this, "To See the Result goto Result section", Toast.LENGTH_SHORT).show();
                }
            }
        }.start();*/
    }

    private void SendData() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid= user.getUid();
        hashMap.put("Correct", correctCount);
        hashMap.put("Wrong", wrongCount);
        FirebaseDatabase.getInstance().getReference("UsersTest").child(currentUserid).child("std1_test_counter").updateChildren(Collections.unmodifiableMap(hashMap));
    }

    private void Result_show() {
        SendData();
        correctCount = 0;
        wrongCount = 0;
        Toast.makeText(std1_test_mcq_screen.this, "Your test is completed", Toast.LENGTH_SHORT).show();
        Toast.makeText(std1_test_mcq_screen.this, "To See the Result goto Result section", Toast.LENGTH_SHORT).show();
        Intent result = new Intent(std1_test_mcq_screen.this, std1_test_result.class);
        startActivity(result);
        finish();
    }

    public void enableButton() {
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);
    }

    public void disableButton() {
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }

    public void resetColor() {
        cardOA.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
        cardOB.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
        cardOC.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
        cardOD.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
    }

    private void setAllData() {
        card_question.setText(modelclass.getQuestion());
        optiona.setText(modelclass.getoA());
        optionb.setText(modelclass.getoB());
        optionc.setText(modelclass.getoC());
        optiond.setText(modelclass.getoD());
    }

    private void Hooks() {


        card_question = findViewById(R.id.card_question);
        optiona = findViewById(R.id.card_optiona);
        optionb = findViewById(R.id.card_optionb);
        optionc = findViewById(R.id.card_optionc);
        optiond = findViewById(R.id.card_optiond);

        cardOA = findViewById(R.id.cardview_optiona);
        cardOB = findViewById(R.id.cardview_optionb);
        cardOC = findViewById(R.id.cardview_optionc);
        cardOD = findViewById(R.id.cardview_optiond);

        nextBtn = findViewById(R.id.nextBtn);
    }

    public void Correct(CardView cardView) {
        cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctCount++;
                index++;
                modelclass = list.get(index);
                setAllData();
                resetColor();
                enableButton();
            }
        });

    }

    public void Wrong(CardView cardView) {

        cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongCount++;
                if (index < list.size() - 1) {
                    index++;
                    modelclass = list.get(index);
                    setAllData();
                    resetColor();
                    enableButton();
                } else {
                    Result_show();
                }
            }
        });
    }

    public void optionA_Click(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelclass.getoA().equals(modelclass.getAns())) {
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            if (index < list.size() - 1) {
                Correct(cardOA);
            } else {
                Result_show();
            }
        } else {
            Wrong(cardOA);
        }
    }

    public void optionB_Click(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelclass.getoB().equals(modelclass.getAns())) {
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            if (index < list.size() - 1) {
                Correct(cardOB);
            } else {
                Result_show();
            }
        } else {
            Wrong(cardOB);
        }
    }

    public void optionC_Click(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelclass.getoC().equals(modelclass.getAns())) {
            cardOC.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            if (index < list.size() - 1) {
                Correct(cardOC);
            } else {
                Result_show();
            }
        } else {
            Wrong(cardOC);
        }
    }

    public void optionD_Click(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if (modelclass.getoD().equals(modelclass.getAns())) {
            cardOD.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            if (index < list.size() - 1) {
                Correct(cardOD);
            } else {
                Result_show();
            }
        } else {
            Wrong(cardOD);
        }
    }

}
