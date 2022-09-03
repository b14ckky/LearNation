package com.example.pr_bg2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pr_bg2.Models.QuestionMember;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class doubt_ask extends AppCompatActivity {

    EditText editText;
    Button button;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference AllQuestions,UserQuestions;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatabaseReference databaseReference;
    public static String POSTKEY;
    QuestionMember member;
    String name,url,privacy,uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doubt_ask);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid= user.getUid();

        //Toast.makeText(this, "currentUserid  "+currentUserid, Toast.LENGTH_SHORT).show();

        editText = findViewById(R.id.ask_et_question);
        button = findViewById(R.id.btn_submit);

        databaseReference = firebaseDatabase.getReference("Users").child(currentUserid);

        AllQuestions = firebaseDatabase.getReference("All Questions");
        UserQuestions = firebaseDatabase.getReference("User Questions").child(currentUserid);

        member = new QuestionMember();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = editText.getText().toString();
                Calendar cdate = Calendar.getInstance();
                SimpleDateFormat currentdate = new SimpleDateFormat("dd-MMMM-yyyy");
                final String savedate = currentdate.format(cdate.getTime());
                Calendar ctime = Calendar.getInstance();
                SimpleDateFormat currentime = new SimpleDateFormat("HH:mm:ss");
                final String savetime = currentime.format(ctime.getTime());


                String time = savedate +":"+savetime;
                if (question != null)
                {
                    member.setQuestion(question);
                    member.setName(name);
                    member.setPrivacy(privacy);
                    member.setUrl(url);
                    member.setUserid(uid);
                    member.setTime(time);

                    String id = UserQuestions.push().getKey();
                    UserQuestions.child(id).setValue(member);

                    POSTKEY = AllQuestions.push().getKey();
                    //Toast.makeText(doubt_ask.this, "postkey = "+POSTKEY, Toast.LENGTH_SHORT).show();
                    member.setKey(id);
                    AllQuestions.child(POSTKEY).setValue(member);
                    Toast.makeText(doubt_ask.this, "Submitted...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(doubt_ask.this, "Please ask a Question..", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    //name = task.getResult().getString("name");
    //url = task.getResult().getString("url");
    //privacy = task.getResult().getString("privacy");
    //uid = task.getResult().getString("uid");


    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot sn:snapshot.getChildren())
                {
                    member = snapshot.getValue(QuestionMember.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(doubt_ask.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}