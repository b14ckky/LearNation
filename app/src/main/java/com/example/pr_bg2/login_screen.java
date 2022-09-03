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

import com.example.pr_bg2.databinding.ActivityLoginScreenBinding;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class login_screen extends AppCompatActivity {


    ActivityLoginScreenBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    public void back_bt()
    {
        ActivityOptionsCompat compat=ActivityOptionsCompat.
                makeSceneTransitionAnimation(login_screen.this,
                        findViewById(R.id.back_bt),"back_bt");
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentIntent = new Intent(login_screen.this, welcome_screen.class);
                startActivity(currentIntent,compat.toBundle());
                finish();
            }
        });
    }
    public void onBackPressed() {
        Intent currentIntent = new Intent(login_screen.this, welcome_screen.class);
        startActivity(currentIntent);
        finish();
    }

       public void signup_bt()
    {
        ActivityOptionsCompat compat=ActivityOptionsCompat.
                makeSceneTransitionAnimation(login_screen.this,
                        findViewById(R.id.signup_bt),"signup_bt");
        TextView signup_bt = (TextView)findViewById(R.id.signup_bt);
        signup_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup_bt = new Intent(login_screen.this, create_account.class);
                startActivity(signup_bt,compat.toBundle());
                finish();
            }
        });
    }
    public void forgotBt()
    {
        ActivityOptionsCompat compat=ActivityOptionsCompat.
                makeSceneTransitionAnimation(login_screen.this,
                        findViewById(R.id.forgotBt),"forgotBt");
        TextView forgotBt=(TextView) findViewById(R.id.forgotBt);
        forgotBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotBt=new Intent(login_screen.this,forgot_password_screen.class);
                startActivity(forgotBt,compat.toBundle());
                finish();
            }
        });
    }
    public void checker()
    {
        EditText UserEmail=(EditText) findViewById(R.id.usrEmailBt);
        EditText UserPassword=(EditText) findViewById(R.id.passwordBt);

        if (TextUtils.isEmpty(UserEmail.getText().toString()))
        {
            Toast.makeText(this, "Please Enter The Email.. ", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(UserPassword.getText().toString()))
        {
            Toast.makeText(this, "Please Enter The Password.. ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressDialog.show();
            auth.signInWithEmailAndPassword(binding.usrEmailBt.getText().toString(),
                    binding.passwordBt.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    ActivityOptionsCompat compat=ActivityOptionsCompat.
                            makeSceneTransitionAnimation(login_screen.this,
                                    findViewById(R.id.loginBt),"loginBt");
                    if (task.isSuccessful())
                    {
                        Intent loginBt = new Intent(login_screen.this,home_screen.class);
                        startActivity(loginBt,compat.toBundle());
                        finish();
                    }
                    else
                    {
                        Toast.makeText(login_screen.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(login_screen.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login To Your Your Account");
        binding.loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checker();

            }
        });


        back_bt();
        signup_bt();
        forgotBt();
    }
}
