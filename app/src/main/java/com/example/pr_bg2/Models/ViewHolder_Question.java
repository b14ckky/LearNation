package com.example.pr_bg2.Models;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr_bg2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ViewHolder_Question extends RecyclerView.ViewHolder {
    ImageView imageView,delete_imageView;
    TextView delete_name_result,delete_time_result,delete_question_result;
    TextView name_result,time_result,question_result;
    public TextView delete_btn,replybtn;
    String user_id="";


    public ViewHolder_Question(@NonNull View itemView) {
        super(itemView);
    }

    public void setItem(FragmentActivity activity,
                   String name,
                   String url,
                   String userid,
                   String key,
                   String question,
                   String privacy,
                   String time) {
        imageView =  itemView.findViewById(R.id.iv_que_item);
        time_result = itemView.findViewById(R.id.time_que_time);
        name_result = itemView.findViewById(R.id.name_que_item_tv);
        question_result = itemView.findViewById(R.id.que_time_tv);
        replybtn = itemView.findViewById(R.id.reply_item_que);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid= user.getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("User Questions");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                QuestionMember member = new QuestionMember();


                for (DataSnapshot childSnapshot : snapshot.getChildren())
                {
                    user_id = childSnapshot.getKey();
                    //Toast.makeText(activity, "User_id = "+user_id, Toast.LENGTH_SHORT).show();

                    //String name = childSnapshot.getValue().toString();
                    //name_result.setText(name);
                    DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference("Users").child(user_id);
                    mDatabase2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Users model = snapshot.getValue(Users.class);
                            String name_result2 = model.getUserName().toString();
                            name_result.setText("User");
                            //Toast.makeText(activity, "Username"+name_result2, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(activity, "Error..!!", Toast.LENGTH_SHORT).show();
                        }
                    });


                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
            }
        });


        name_result.setText(name);
        time_result.setText(time);
        question_result.setText(question);



    }

    public void setItemdelete(FragmentActivity activity,
                        String name,
                        String url,
                        String userid,
                        String key,
                        String question,
                        String privacy,
                        String time) {
        delete_imageView =  itemView.findViewById(R.id.delete_iv_que_item);
        delete_time_result = itemView.findViewById(R.id.delete_time_que_time);
        delete_name_result = itemView.findViewById(R.id.delete_name_que_item_tv);
        delete_question_result = itemView.findViewById(R.id.delete_que_time_tv);
        delete_btn = itemView.findViewById(R.id.delete_btn);

        delete_time_result.setText(time);
        delete_name_result.setText("User");
        delete_question_result.setText(question);




    }

}
