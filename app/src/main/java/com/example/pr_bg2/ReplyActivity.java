package com.example.pr_bg2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr_bg2.Models.AnswerActivity;
import com.example.pr_bg2.Models.AnswerMember;
import com.example.pr_bg2.Models.AnswerViewHolder;
import com.example.pr_bg2.Models.QuestionMember;
import com.example.pr_bg2.Models.Users;
import com.example.pr_bg2.Models.ViewHolder_Question;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ReplyActivity extends AppCompatActivity {

String uid,questions,post_key,key;
DatabaseReference reference,reference2;
DocumentReference ref1,ref2;
TextView nametv,questiotv,tvreply,que_reply_tv;
FirebaseFirestore db;
RecyclerView recyclerView;
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference votesref,AllQuestions;
Boolean votechecker = false;
ImageView imageViewQue,ImageviewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);



        nametv = findViewById(R.id.name_reply_tv);
        questiotv = findViewById(R.id.que_reply_tv);
        tvreply = findViewById(R.id.answer_tv);

        recyclerView = findViewById(R.id.rv_ans);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReplyActivity.this));

        Bundle extra = getIntent().getExtras();
        if (extra != null)
        {
            uid = extra.getString("uid");
            post_key = extra.getString("postkey");
            questions = extra.getString("q");
            key = extra.getString("key");
        }
        else
        {
            Toast.makeText(this, "Opps..!", Toast.LENGTH_SHORT).show();
        }


       votesref = database.getReference("votes");
        AllQuestions = database.getReference("All Questions").child(post_key).child("Answer");


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();
        //ref1 = db.collection("user").document(uid);
        //ref2 = db.collection("user").document(currentuid);

        tvreply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReplyActivity.this, AnswerActivity.class);
                intent.putExtra("uid", uid);
                intent.putExtra("q", questions);
                intent.putExtra("p", post_key);
                startActivity(intent);

            }
        });
        questiotv.setText(questions);
        //Toast.makeText(this, "Question = "+questions, Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid= user.getUid();
        /*DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                QuestionMember member = new QuestionMember();
                for (DataSnapshot childSnapshot : snapshot.getChildren())
                {
                    String name = childSnapshot.getValue().toString();
                    nametv.setText(name);


                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReplyActivity.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });*/

        //////////////

        DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference("User Questions");
        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren())
                {
                    String user_id = childSnapshot.getKey();
                    DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference("Users").child(user_id);
                    mDatabase2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Users model = snapshot.getValue(Users.class);
                            String name_result2 = model.getUserName().toString();
                            nametv.setText("User");
                           // Toast.makeText(ReplyActivity.this, "Username"+name_result2, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(ReplyActivity.this, "Error..!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReplyActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseRecyclerOptions<AnswerMember> options = new FirebaseRecyclerOptions.Builder<AnswerMember>()
                .setQuery(AllQuestions,AnswerMember.class)
                .build();

        FirebaseRecyclerAdapter<AnswerMember, AnswerViewHolder> firebseRecyclerAdepter =
                new FirebaseRecyclerAdapter<AnswerMember,
                        AnswerViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AnswerViewHolder holder, int position, @NonNull AnswerMember model) {

                final String postkey = getRef(position).getKey();
                holder.setAnswer(getApplication(),
                        model.getName(),
                        model.getAnswer(),
                        model.getUid(),
                        model.getTime(),
                        model.getUrl());

                holder.upvoteChecker(postkey);
                holder.upvoteTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        votechecker = true;
                        votesref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (votechecker.equals(true))
                                {
                                    if (snapshot.child(postkey).hasChild(currentUserid))
                                    {
                                        votesref.child(postkey).child(currentUserid).removeValue();
                                        votechecker = false;
                                    }
                                    else
                                    {
                                        votesref.child(postkey).child(currentUserid).setValue(true);
                                        votechecker = false;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });

            }

            @NonNull
            @Override
            public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.
                        getContext()).inflate(R.layout.ans_layout,parent,false);
                return  new AnswerViewHolder(view);
            }
        };
        recyclerView.setAdapter(firebseRecyclerAdepter);
        firebseRecyclerAdepter.startListening();

    }

}
