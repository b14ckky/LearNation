package com.example.pr_bg2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.pr_bg2.Models.QuestionMember;
import com.example.pr_bg2.Models.ViewHolder_Question;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class YourQuestions extends AppCompatActivity {
RecyclerView recyclerView;
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference AllQuestions,UserQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_questions);


        recyclerView = findViewById(R.id.rv_your_quetions);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid = user.getUid();

        AllQuestions = database.getReference("All Questions");
        UserQuestions = database.getReference("User Questions").child(currentUserid);

        FirebaseRecyclerOptions<QuestionMember> options = new FirebaseRecyclerOptions.Builder<QuestionMember>()
                .setQuery(UserQuestions,QuestionMember.class)
                .build();

        FirebaseRecyclerAdapter<QuestionMember, ViewHolder_Question> firebseRecyclerAdepter = new FirebaseRecyclerAdapter<QuestionMember, ViewHolder_Question>(options) {
            @Override


            protected void onBindViewHolder(@NonNull ViewHolder_Question holder, int position, @NonNull QuestionMember model) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final String currentUserid = user.getUid();
                final String postkey = getRef(position).getKey();
                holder.setItemdelete(YourQuestions.this,
                        model.getName(),
                        model.getUrl(),
                        model.getUserid(),
                        model.getKey(),
                        model.getQuestion(),
                        model.getPrivacy(),
                        model.getTime());

                final String time= getItem(position).getTime();
                holder.delete_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        delete(time);
                    }
                });
            }

            @NonNull
            @Override
            public ViewHolder_Question onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.
                        getContext()).inflate(R.layout.yourque_item,parent,false);
                return  new ViewHolder_Question(view);
            }
        };
        recyclerView.setAdapter(firebseRecyclerAdepter);
        firebseRecyclerAdepter.startListening();

    }


    void delete(String time)
    {
        Query query = UserQuestions.orderByChild("time").equalTo(time);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    dataSnapshot.getRef().removeValue();
                    Toast.makeText(YourQuestions.this, "Deleted ..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Query query1 = AllQuestions.orderByChild("time").equalTo(time);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    dataSnapshot.getRef().removeValue();
                    Toast.makeText(YourQuestions.this, "Deleted ..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

