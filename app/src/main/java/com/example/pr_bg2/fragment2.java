package com.example.pr_bg2;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr_bg2.Models.BottomSheetF2;
import com.example.pr_bg2.Models.QuestionMember;
import com.example.pr_bg2.Models.ViewHolder_Question;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;


public class fragment2 extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fp;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatabaseReference reference;
    ImageView imageView;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String currentUserid= user.getUid();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    RecyclerView recyclerView;




    @Override
    public void onBackPressed() {
        Intent back_bt = new Intent(fragment2.this, std1_sub1.class);
        startActivity(back_bt);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment2);



        recyclerView = findViewById(R.id.rv_f2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         databaseReference = database.getReference("All Questions");





        imageView = findViewById(R.id.iv_f2);
        fp = findViewById(R.id.floatingActionButton);
        reference = database.getReference("Users").child(currentUserid).child("userName");

        fp.setOnClickListener(this);
        imageView.setOnClickListener(this);

        FirebaseRecyclerOptions<QuestionMember> options = new FirebaseRecyclerOptions.Builder<QuestionMember>()
                .setQuery(databaseReference,QuestionMember.class)
                .build();

        FirebaseRecyclerAdapter<QuestionMember,ViewHolder_Question> firebseRecyclerAdepter = new FirebaseRecyclerAdapter<QuestionMember, ViewHolder_Question>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_Question holder, int position, @NonNull QuestionMember model) {
              holder.setItem(fragment2.this,
                      model.getName(),
                      model.getUrl(),
                      model.getUserid(),
                      model.getKey(),
                      model.getQuestion(),
                      model.getPrivacy(),
                      model.getTime());

              final String postkey = getRef(position).getKey();
              final String que = getItem(position).getQuestion();
              final String name = getItem(position).getName();
              final String url = getItem(position).getUrl();
              final String time = getItem(position).getTime();
              final String privacy = getItem(position).getPrivacy();
              final String userid = getItem(position).getUserid();


                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("All Questions");
                mDatabase.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        QuestionMember member = new QuestionMember();
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String postkey2 = childSnapshot.getKey();
                            holder.replybtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(fragment2.this, ReplyActivity.class);
                                    intent.putExtra("uid", userid);
                                    intent.putExtra("q", que);
                                    intent.putExtra("postkey", postkey2);
                                    intent.putExtra("key", privacy);
                                    startActivity(intent);
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @NonNull
            @Override
            public ViewHolder_Question onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.
                        getContext()).inflate(R.layout.question_item,parent,false);
                return  new ViewHolder_Question(view);
            }
        };
        recyclerView.setAdapter(firebseRecyclerAdepter);
        firebseRecyclerAdepter.startListening();

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_f2:
                FragmentManager fm = getFragmentManager();
                BottomSheetF2 bottomSheetf2 = new BottomSheetF2();
                bottomSheetf2.show(getSupportFragmentManager(),"bottom");
                break;


            case R.id.floatingActionButton:
                Intent intent = new Intent(this,doubt_ask.class);
                startActivity(intent);
                break;
        }

    }


}