package com.example.pr_bg2.Models;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pr_bg2.Models.AnswerMember;
import com.example.pr_bg2.Models.QuestionMember;
import com.example.pr_bg2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AnswerActivity extends AppCompatActivity {

    String uid,question;
    String postkey;
    EditText editText;
    Button btn_ans_submit;

    AnswerMember member;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Allquestions;
    String name,url,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);


        member = new AnswerMember();
        editText = findViewById(R.id.answer_et);
        btn_ans_submit = findViewById(R.id.btn_ans_submit);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            uid = bundle.getString("u");
            postkey = bundle.getString("p");

        }
        else
        {
            Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, "Postkey = "+postkey, Toast.LENGTH_SHORT).show();
        Allquestions = database.getReference("All Questions").child(postkey).child("Answer");
        btn_ans_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedAnswer();
            }
        });
    }
    public void savedAnswer()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();
        String answer = editText.getText().toString();
        if (answer != null)
        {
            Calendar cdate = Calendar.getInstance();
            SimpleDateFormat currentdate = new SimpleDateFormat("dd-MMMM-yyyy");
            final String savedate = currentdate.format(cdate.getTime());
            Calendar ctime = Calendar.getInstance();
            SimpleDateFormat currentime = new SimpleDateFormat("HH:mm:ss");
            final String savetime = currentime.format(ctime.getTime());
            time = savedate +":"+savetime;

            member.setAnswer(answer);
            member.setTime(time);
            member.setName(name);
            member.setUid(userid);
            member.setUrl(url);

            String id = Allquestions.push().getKey();
            Allquestions.child(id).setValue(member);
            Toast.makeText(this,"Submitted...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Please write Answer !!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid= user.getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users").child(currentUserid);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                QuestionMember member = new QuestionMember();
                for (DataSnapshot childSnapshot : snapshot.getChildren())
                {
                    String name = childSnapshot.getValue().toString();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}