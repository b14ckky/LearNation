package com.example.pr_bg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.pr_bg2.Models.Modelclass;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Modelclass> list2,list;
    private static int SPLASH_TIME_OUT = 2000;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_intro_screen);


        list = new ArrayList<>();


        list.add(new Modelclass("Which of the following is a natural thing?","Rock","Aeroplane","Television","Telephone","Rock"));
        list.add(new Modelclass("Which of the following is living thing?","Ant","Fire","Paper","Eraser","Ant"));
        list.add(new Modelclass("शेर के बच्चे ने पेड़ की डाल क्यों पकड़ी?","गिरने से बचने के लिए","झूलने के लिए","फल खाने के लिए","खेलने के लिए ","गिरने से बचने के लिए"));
        list.add(new Modelclass("हवा ने सूरज को किस कारण नमस्कार किया?","सूरज का आकार देख कर","सूरज की ताकत देख कर","सूरज का गुस्सा देख कर","सूरज का लाल रंग देख कर","सूरज की ताकत देख कर"));
        list.add(new Modelclass("What did the sun do to get the man’s coat off?","The sun went away","The sun started shining hard","The Sun started to set","None of the above","The sun started shining hard"));
        list.add(new Modelclass("What sound does a dog make?","Bleats","Moos","Barks","Neighs","Barks"));
        list.add(new Modelclass("Who does all the mischiefs in the house?","Everyone","Mouse","Man","Mr. Nobody","Mr. Nobody"));
        list.add(new Modelclass("Which of the following add upto 20?","10+10","10+5","15+10","15+15","10+10"));
        list.add(new Modelclass("Rita collected 8 stamps. Neha has collected 7. How many stamps do both of them have?","25","15","35","65","15"));
        list.add(new Modelclass("10x5=?","55","45","50","30","50"));

//////////////////////////////////////////////////////////////////////////////////////////////////////

        list2 = new ArrayList<>();



        list2.add(new Modelclass("What did the children bring for tiny birds?","Fruits","Chips","Bread","Chapati","Bread"));
        list2.add(new Modelclass("The balloons are tied together with?","Strings","Cottonwool","Nylon thread","Threads","Strings"));
        list2.add(new Modelclass("भारत में आ कर मीरा बहन किसके साथ काम करने लगी?","शंकर के साथ","सरोजनी नायडू के साथ","गांव वालों के साथ","गांधीजी के साथ","गांधीजी के साथ"));
        list2.add(new Modelclass("लेखक क्या बनकर तारों को अकड़ दिखाना चाहता है?","चांद","सूरज","बादल","बाबा","चांद"));
        list2.add(new Modelclass("What should we avoid to maintain good health?","Milk","Roti","Vegetables","Junk food","Junk food"));
        list2.add(new Modelclass("Which of the following thing can hurt you?","Paper","Pencil","Knife","Book","Knife"));
        list2.add(new Modelclass("We get eggs from?","Cow","Dog","Hen","Goat","Hen"));
        list2.add(new Modelclass("Which number comes just before 9990?","9991","9909","9988","9989","9989"));
        list2.add(new Modelclass("The place value of 2 in 2548 is?","200","2020","2000","2002","2000"));
        list2.add(new Modelclass("In roman numerals M means?","500","100","1000","50","1000"));


        boolean b = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this,welcome_screen.class);
                startActivity(homeIntent);


            }
        },SPLASH_TIME_OUT);


    }



}