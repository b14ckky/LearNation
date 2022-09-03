package com.example.pr_bg2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr_bg2.Models.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class nav_view_profile extends AppCompatActivity {

    TextView name,age,state,standard,favsub;
    String name1,age1,state1,standard1,favsub1;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    UserProfile member;
    DatabaseReference UsersProfiles;
    UserProfile model;

    @Override
    public void onBackPressed() {
        Intent intent2 = new Intent(nav_view_profile.this,home_screen.class);
        startActivity(intent2);
        finish();
    }
    public void back_bt()
    {
        Button back_bt=(Button) findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nav_view_profile.this,home_screen.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_view_profile);
        back_bt();
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        state = findViewById(R.id.state);
        standard = findViewById(R.id.standard);
        favsub = findViewById(R.id.favsub);
        FetchData();
    }

    private void FetchData() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid= user.getUid();
        UsersProfiles = FirebaseDatabase.getInstance().getReference("UsersProfiles").child(currentUserid);
        UsersProfiles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                model = snapshot.getValue(UserProfile.class);
                if (snapshot.exists())
                {
                    name.setText(model.getName());
                    age.setText(model.getAge());
                    state.setText(model.getState());
                    standard.setText(model.getStandard());
                    favsub.setText(model.getFavsub());
                    pd.dismiss();
                }
                else
                {
                    Toast.makeText(nav_view_profile.this, "There is no data !!", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(nav_view_profile.this, "Error in fetching data..", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }
}