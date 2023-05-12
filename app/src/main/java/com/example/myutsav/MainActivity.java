package com.example.myutsav;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bnview;
Toolbar toolbar;
String auth;
ViewFlipper flipper;
LinearLayout menu1,check,addtask,guest,budget1;
TextView username,event;
String name,date,useridshow,currentdate;
Calendar calendar;


DatabaseReference databaseReference;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance().getCurrentUser().getUid();
        bnview=findViewById(R.id.bnView);
        username=findViewById(R.id.username);
        event=findViewById(R.id.eventdate);
        toolbar=findViewById(R.id.home_toolbar);
        menu1=findViewById(R.id.menu);
        guest=findViewById(R.id.guest);
        check=findViewById(R.id.checklist);
        flipper=findViewById(R.id.flip);
        budget1= findViewById(R.id.budget);
        addtask=findViewById(R.id.tasks);
        int imgarray[]={R.drawable.wedding,R.drawable.corporate,R.drawable.birthday,R.drawable.marriage,R.drawable.travel};
        for(int i=0;i<imgarray.length;i++) {
        showimage(imgarray[i]);
        }
   if(auth==null)
   {
       Intent i=new Intent(getApplicationContext(),Loginpage.class);
       startActivity(i);
       finish();
   }
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Menu_activity.class);
                startActivity(i);
            }
        });

        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),newtaskform.class);
                startActivity(i);
            }
        });
        budget1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),showbudget.class);
                startActivity(i);
            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Guest_activity.class);
                startActivity(i);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),checklist_activity.class);
                startActivity(i);
            }
        });

        setSupportActionBar(toolbar);

        name=getIntent().getStringExtra("fullname");
        date=getIntent().getStringExtra("date");
        useridshow=getIntent().getStringExtra("useridtransfer");
        /*useridtext.setText("User Id : "+useridshow);*/
        username.setText(name);
        event.setText(date);
        calendar= Calendar.getInstance();
        bnview.setSelectedItemId(R.id.nav_home);
       bnview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();

                switch (id)
                {
                    case R.id.nav_home:
                        return true;
                    case R.id.check_list:
                        Intent intent=new Intent(getApplicationContext(),checklist_activity.class);
                        intent.putExtra("useridtransfer",useridshow);
                        intent.putExtra("username",name);
                        intent.putExtra("date",date);
                        startActivityForResult(intent,1);
                        overridePendingTransition(2,2);
                        return true;

                    case R.id.guests:
                        Intent intent1=new Intent(getApplicationContext(),Guest_activity.class);
                        intent1.putExtra("useridtransfer",useridshow);
                        intent1.putExtra("username",name);
                        intent1.putExtra("date",date);
                        startActivityForResult(intent1,1);
                        overridePendingTransition(2,2);
                        return true;

                    case R.id.budget:
                        Intent intent2=new Intent(getApplicationContext(),Budget_activity.class);
                        intent2.putExtra("useridtransfer",useridshow);
                        intent2.putExtra("username",name);
                        intent2.putExtra("date",date);
                        startActivityForResult(intent2,1);
                        overridePendingTransition(2,2);
                      //  finish();
                        return true;

                    case R.id.menu:
                        Intent intent3=new Intent(getApplicationContext(),Menu_activity.class);
                        intent3.putExtra("useridtransfer",useridshow);
                        intent3.putExtra("username",name);
                        intent3.putExtra("date",date);
                        startActivityForResult(intent3,1);
                        overridePendingTransition(2,2);

                        return true;
                }
                return false;
            }
        });

    }

    private void showimage(int i) {
        ImageView imageview=new ImageView(this);
        imageview.setBackgroundResource(i);
        flipper.addView(imageview);
        flipper.setFlipInterval(4000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setInAnimation(this, android.R.anim.slide_out_right);
    }

    protected void onStart() {

        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            Intent intent=new Intent(this,Loginpage.class);
            startActivity(intent);
            finish();
        }
    }




    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK) {
            if (requestCode == 1)
            {
                if(resultCode==RESULT_OK)
                {
                    username.setText(data.getStringExtra("username"));
                    event.setText(data.getStringExtra("date"));
                    /*useridtext.setText("User Id : "+data.getStringExtra("useridtransfer"));*/
                }
            }
        }
    }
}