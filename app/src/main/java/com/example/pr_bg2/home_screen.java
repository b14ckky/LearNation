package com.example.pr_bg2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.pr_bg2.databinding.ActivityHomeScreenBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class home_screen extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    ActivityHomeScreenBinding binding;
    FirebaseAuth auth;
    public void onBackPressed() {

    }
    public void std_1()
    {
        Button std_bt=(Button)findViewById(R.id.std_bt);
        std_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent std_bt = new Intent(home_screen.this, all_standards_screen.class);
                startActivity(std_bt);
                finish();
            }
        });
    }
    public void gamification()
    {
        Button gamification=(Button)findViewById(R.id.Gamification);
        gamification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gamification = new Intent(home_screen.this, gamification_screen.class);
                startActivity(gamification);
                finish();
            }
        });
    }
    public void result()
    {
        Button result_bt=(Button)findViewById(R.id.result_bt);
        result_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result_bt = new Intent(home_screen.this, standards_test_result.class);
                startActivity(result_bt);
                finish();
            }
        });
    }
    public void pc()
    {
        Button pc=(Button)findViewById(R.id.pc);
        pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pc = new Intent(home_screen.this, parental_control_home.class);
                startActivity(pc);
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        auth = FirebaseAuth.getInstance();
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        std_1();
        gamification();
        result();
        pc();
        drawerLayout=findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {
                    case R.id.nav_edit_profile:
                        Intent intent = new Intent(home_screen.this,nav_edit_profile.class);
                        startActivity(intent);
                        finish();
                        //Toast.makeText(home_screen.this, "Edit Profile clicked.", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_view_profile:
                        Intent intent2 = new Intent(home_screen.this,nav_view_profile.class);
                        startActivity(intent2);
                        finish();
                        //Toast.makeText(home_screen.this, "View Profile clicked.", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_thought:
                        Dialog dialog = new Dialog(home_screen.this);
                        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                        dialog.setContentView(R.layout.thought_of_the_day);
                        dialog.show();
                        dialog.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        //Toast.makeText(home_screen.this, "thought clicked.", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_aboutus:
                        Intent intent4 = new Intent(home_screen.this,nav_about_us.class);
                        startActivity(intent4);
                        finish();
                        //Toast.makeText(home_screen.this, "About us clicked.", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_contact:
                        Intent intent5 = new Intent(home_screen.this,nav_contact_us.class);
                        startActivity(intent5);
                        finish();
                        //Toast.makeText(home_screen.this, "Contact us clicked.", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_logout:
                        auth.signOut();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent6 = new Intent(home_screen.this,login_screen.class);
                        startActivity(intent6);
                        finish();
                        //Toast.makeText(home_screen.this, "logout clicked.", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_share:
                        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                        whatsappIntent.setType("text/plain");
                        whatsappIntent.setPackage("com.whatsapp");
                        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "https://learnation.000webhostapp.com/LearNation/LearNation.apk");
                        try {
                            startActivity(whatsappIntent);
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(home_screen.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(home_screen.this, "Share clicked.", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });


    }


    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.settings:
                Toast.makeText(home_screen.this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                auth.signOut();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(home_screen.this,login_screen.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;

    }*/



}
