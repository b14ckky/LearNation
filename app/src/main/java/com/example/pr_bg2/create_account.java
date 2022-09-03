package com.example.pr_bg2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.example.pr_bg2.Models.Users;
import com.example.pr_bg2.databinding.ActivityCreateAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class create_account extends AppCompatActivity {

    ActivityCreateAccountBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;




        public void back_bt() {
            ActivityOptionsCompat compat=ActivityOptionsCompat.
                    makeSceneTransitionAnimation(create_account.this,
                            findViewById(R.id.back_bt),"back_bt");
        Button back_bt = (Button) findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentIntent = new Intent(create_account.this, welcome_screen.class);
                startActivity(currentIntent,compat.toBundle());
                finish();
            }
        });
    }

    public void txt_login() {
        ActivityOptionsCompat compat=ActivityOptionsCompat.
                makeSceneTransitionAnimation(create_account.this,
                        findViewById(R.id.txt_login),"txt_login");
        TextView txt_login = (TextView) findViewById(R.id.txt_login);
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txt_login = new Intent(create_account.this, login_screen.class);
                startActivity(txt_login,compat.toBundle());
                finish();
            }
        });

    }
    public void onBackPressed() {
        Intent currentIntent = new Intent(create_account.this, welcome_screen.class);
        startActivity(currentIntent);
        finish();
    }
    public void CreateNewAccount()
    {
        EditText UserName=(EditText) findViewById(R.id.nameBt);
        EditText UserEmail=(EditText) findViewById(R.id.emailBt);
        EditText UserPassword=(EditText) findViewById(R.id.passwordBt);
        EditText UserConfirmPassword=(EditText) findViewById(R.id.confirmPassword);

        String username = UserName.getText().toString();
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        String confirm_password = UserConfirmPassword.getText().toString();
        String regex = "^(?=.*[a-z])(?=."
                + "*[A-Z])(?=.*\\d)"
                + "(?=.*[-+_!@#$%^&*., ?]).+$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Please write your username..", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your email..", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password..", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(confirm_password))
        {
            Toast.makeText(this, "Please write your confirm password..", Toast.LENGTH_SHORT).show();
        }
        else if (!password.equals(confirm_password))
        {
            Toast.makeText(this, "Your password do not match with your confirm password..", Toast.LENGTH_SHORT).show();
        }
        else if (!m.matches())
        {
            Toast.makeText(this, "Your password must have this conditions\n" +
                    "1. Your Password Must Contains Lowercase\n" +
                    "2. Your Password Must Contains Uppercase\n" +
                    "3. Your Password Must Contains Special Symbols\n" +
                    "4. Your Password Must Contains Digits", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressDialog.show();
            auth.createUserWithEmailAndPassword(binding.emailBt.getText().toString(),
                    binding.passwordBt.getText().toString()).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                Users user = new Users(binding.nameBt.getText().toString(),
                                        binding.emailBt.getText().toString(),
                                        binding.passwordBt.getText().toString());
                                String id = task.getResult().getUser().getUid();
                                database.getReference().child("Users").child(id).setValue(user);
                                Toast.makeText(create_account.this, "User Created Successfully", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(create_account.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);



        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        progressDialog = new ProgressDialog(create_account.this);
        progressDialog.setTitle("Creating An Account");
        progressDialog.setMessage("We Are Creating Your Account");
        binding.signupBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            CreateNewAccount();

            }

        });
        if (auth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(create_account.this,home_screen.class);
            startActivity(intent);

        }

            txt_login();
            back_bt();
        }





        }








