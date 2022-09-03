package com.example.pr_bg2.Models;

import android.app.Application;
import android.os.IInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr_bg2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AnswerViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView nameTv,timeTv,ansTv,votesNoTv;
    public static TextView upvoteTv;
    int votesCount;
    DatabaseReference reference;
    FirebaseDatabase database;


    public AnswerViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public void setAnswer(Application application,
            String name,
            String answer,
            String uid,
            String time,
            String url){

        imageView = itemView.findViewById(R.id.imageView_ans);
        nameTv = itemView.findViewById(R.id.tv_name_ans);
        timeTv = itemView.findViewById(R.id.tv_time_ans);
        ansTv = itemView.findViewById(R.id.tv_ans);

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
                    nameTv.setText("User");

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        timeTv.setText(time);
        ansTv.setText(answer);

    }
    public void upvoteChecker(String postkey)
    {
        database= FirebaseDatabase.getInstance();
        reference= database.getReference("votes");
        upvoteTv= itemView.findViewById(R.id.upvote);
        votesNoTv=itemView.findViewById(R.id.votes);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currenuid = user.getUid();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(postkey).hasChild(currenuid))
                {
                    upvoteTv.setText("VOTED");
                    votesCount =(int)snapshot.child(postkey).getChildrenCount();
                    votesNoTv.setText(Integer.toString(votesCount)+"-VOTES");
                }
                else
                {
                    upvoteTv.setText("UPVOTE");
                    votesCount =(int)snapshot.child(postkey).getChildrenCount();
                    votesNoTv.setText(Integer.toString(votesCount)+"-VOTES");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
