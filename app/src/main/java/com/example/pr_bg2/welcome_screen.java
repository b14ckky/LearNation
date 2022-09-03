package com.example.pr_bg2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr_bg2.Models.Users;
import com.example.pr_bg2.databinding.ActivityCreateAccountBinding;
import com.example.pr_bg2.databinding.ActivityWelcomeScreenBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class welcome_screen extends AppCompatActivity {


    ActivityWelcomeScreenBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    GoogleSignInClient mGoogleSignInClient;

    public void login()
    {
        ActivityOptionsCompat compat=ActivityOptionsCompat.
                makeSceneTransitionAnimation(welcome_screen.this,
                        findViewById(R.id.txtLogin),"t1");

        final TextView txtLogin=(TextView)findViewById(R.id.txtLogin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent txtLogin= new Intent(welcome_screen.this,login_screen.class);
                startActivity(txtLogin,compat.toBundle());
            }
        });
    }
    public void createPn()
    {
        ActivityOptionsCompat compat=ActivityOptionsCompat.
                makeSceneTransitionAnimation(welcome_screen.this,
                        findViewById(R.id.createPn),"t1");

        final Button createPn=(Button) findViewById(R.id.createPn);
        createPn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent createPn= new Intent(welcome_screen.this,send_o_t_p.class);
                startActivity(createPn,compat.toBundle());
            }
        });
    }
    public  void create_bt()
    {
        ActivityOptionsCompat compat=ActivityOptionsCompat.
                makeSceneTransitionAnimation(welcome_screen.this,
                findViewById(R.id.createBt2),"t1");
        Button create_bt=(Button) findViewById(R.id.createBt2);

        create_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create_bt= new Intent(welcome_screen.this,create_account.class);
                startActivity(create_bt,compat.toBundle());
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        binding = ActivityWelcomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        login();
        create_bt();
        createPn();
        binding.btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultLauncher.launch(new Intent(mGoogleSignInClient.getSignInIntent()));
            }
        });
        if (auth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(welcome_screen.this,home_screen.class);
            startActivity(intent);

        }


    }

ActivityResultLauncher<Intent>resultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
    @Override
    public void onActivityResult(ActivityResult result) {
        if (result.getResultCode() == Activity.RESULT_OK)
        {
            Intent intent= result.getData();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) {

                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }
});


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user2 = auth.getCurrentUser();
                            Users users = new Users();
                            users.setUserName(user2.getDisplayName());
                            users.setUserId(user2.getUid());
                            users.setProfilepic(user2.getPhotoUrl().toString());
                            database.getReference().child("Users").child(user2.getUid()).setValue(users);
                            Intent intent = new Intent(welcome_screen.this,home_screen.class);
                            startActivity(intent);
                            finish();
                        }
                        else {

                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

}



