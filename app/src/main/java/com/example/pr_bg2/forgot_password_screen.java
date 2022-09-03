package com.example.pr_bg2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.pr_bg2.databinding.ActivityLoginScreenBinding;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class forgot_password_screen extends AppCompatActivity {

    ActivityLoginScreenBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    Toolbar mToolbar;
    Button ResetPasswordSendEmailButton;
    EditText ResetEmailInput;

    public  void back_bt()
    {
        ActivityOptionsCompat compat=ActivityOptionsCompat.
                makeSceneTransitionAnimation(forgot_password_screen.this,
                        findViewById(R.id.back_bt),"back_bt");
        Button back_bt=(Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_bt = new Intent(forgot_password_screen.this, login_screen.class);
                startActivity(back_bt,compat.toBundle());
                finish();
            }
        });
    }
    public void onBackPressed() {
        Intent back_bt = new Intent(forgot_password_screen.this, login_screen.class);
        startActivity(back_bt);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_screen);


        auth=FirebaseAuth.getInstance();

        ResetPasswordSendEmailButton=(Button)findViewById(R.id.ResetBt2);
        ResetEmailInput=(EditText) findViewById(R.id.usrEmailBt);

        ResetPasswordSendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String userEmail = ResetEmailInput.getText().toString();
                if (TextUtils.isEmpty(userEmail))
                {
                    Toast.makeText(forgot_password_screen.this, "Please Write Your Valid Email Address First!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                   auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           ActivityOptionsCompat compat=ActivityOptionsCompat.
                                   makeSceneTransitionAnimation(forgot_password_screen.this,
                                           findViewById(R.id.ResetBt2),"ResetBt2");
                           if (task.isSuccessful())
                           {
                               Toast.makeText(forgot_password_screen.this,
                                       "Please Check Your Email Account , If You Want To Reset Your Password"
                                       , Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(forgot_password_screen.this,login_screen.class),compat.toBundle());

                           }
                           else
                           {
                               String massage=task.getException().getMessage();
                               Toast.makeText(forgot_password_screen.this, "Error Occurred!!"+massage, Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
                }
            }
        });

        back_bt();
    }
}