package com.example.pr_bg2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr_bg2.databinding.ActivityWelcomeScreenBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class send_o_t_p extends AppCompatActivity {
    ActivityWelcomeScreenBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;


    public void back_bt()
    {
        ActivityOptionsCompat compat=ActivityOptionsCompat.
                makeSceneTransitionAnimation(send_o_t_p.this,
                        findViewById(R.id.back_bt),"back_bt");
        Button back_bt = (Button)findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentIntent = new Intent(send_o_t_p.this, welcome_screen.class);
                startActivity(currentIntent,compat.toBundle());
                finish();
            }
        });
    }
    public void onBackPressed() {
        Intent currentIntent = new Intent(send_o_t_p.this, welcome_screen.class);
        startActivity(currentIntent);
        finish();
    }
    public void signup_bt()
    {
        ActivityOptionsCompat compat=ActivityOptionsCompat.
                makeSceneTransitionAnimation(send_o_t_p.this,
                        findViewById(R.id.signup_bt),"t1");
        TextView signup_bt = (TextView)findViewById(R.id.signup_bt);
        signup_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup_bt = new Intent(send_o_t_p.this, welcome_screen.class);
                startActivity(signup_bt,compat.toBundle());
                finish();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivityWelcomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_ot_p);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        final EditText inputMobile = findViewById(R.id.inputMobile);
        final Button getOTP = findViewById(R.id.btn_getOTP);
        signup_bt();
        back_bt();
        getOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputMobile.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(send_o_t_p.this,"Enter Mobile",Toast.LENGTH_LONG).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                getOTP.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + inputMobile.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        send_o_t_p.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                getOTP.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                getOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(send_o_t_p.this,e.getMessage(),Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                //super.onCodeSent(s, forceResendingToken);
                                ActivityOptionsCompat compat=ActivityOptionsCompat.
                                        makeSceneTransitionAnimation(send_o_t_p.this,
                                                findViewById(R.id.btn_getOTP),"btn_getOTP");
                                progressBar.setVisibility(View.GONE);
                                getOTP.setVisibility(View.VISIBLE);
                                Intent i = new Intent(getApplicationContext(),verify_o_t_p.class);
                                i.putExtra("mobile",inputMobile.getText().toString());
                                i.putExtra("verificationID", verificationID);
                                startActivity(i,compat.toBundle());
                            }
                        }
                );

            }
        });
        if (auth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(send_o_t_p.this,home_screen.class);
            startActivity(intent);

        }

    }
}