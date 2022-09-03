package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr_bg2.Models.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.protobuf.StringValue;

public class nav_edit_profile extends AppCompatActivity {

    EditText name,age,state,standard,favsub;
    Button submit;
    String name1,age1,state1,standard1,favsub1;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    UserProfile member;
    DatabaseReference UsersProfiles;

    @Override
    public void onBackPressed() {
        Intent intent2 = new Intent(nav_edit_profile.this,home_screen.class);
        startActivity(intent2);
        finish();
    }
    public void back_bt()
    {
        Button back_bt=(Button) findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nav_edit_profile.this,home_screen.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_edit_profile);
        back_bt();
        Hooks();
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name1 = name.getText().toString();
                age1 = age.getText().toString();
                state1 = state.getText().toString();
                standard1 = standard.getText().toString();
                favsub1 = favsub.getText().toString();

                if ((TextUtils.isEmpty(name1) &&
                        TextUtils.isEmpty(age1) &&
                        TextUtils.isEmpty(state1) &&
                        TextUtils.isEmpty(standard1) &&
                        TextUtils.isEmpty(favsub1)))
                {
                    Toast.makeText(nav_edit_profile.this, "Please Fill Whole Form !!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(nav_edit_profile.this, "name = "+name1+"age1 = "+age1+"state1 = "+state1+"standard1 = "+standard1+"favsub1"+favsub1, Toast.LENGTH_SHORT).show();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String currentUserid= user.getUid();
                    UsersProfiles = firebaseDatabase.getInstance().getReference("UsersProfiles").child(currentUserid);
                    member = new UserProfile();
                    member.setName(name1);
                    member.setState(state1);
                    member.setFavsub(favsub1);
                    member.setAge(age1);
                    member.setStandard(standard1);
                    UsersProfiles.setValue(member);
                    Toast.makeText(nav_edit_profile.this, "Submitted...", Toast.LENGTH_SHORT).show();


                }
            }
        });



    }

    private void Hooks() {
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        state = findViewById(R.id.state);
        standard = findViewById(R.id.standard);
        favsub = findViewById(R.id.favsub);
        submit =findViewById(R.id.submitBt);


    }
}