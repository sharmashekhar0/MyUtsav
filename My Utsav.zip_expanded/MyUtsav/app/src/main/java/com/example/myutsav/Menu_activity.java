package com.example.myutsav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Calendar;

public class Menu_activity extends AppCompatActivity {
BottomNavigationView bnview;

Toolbar toolbar;
LinearLayout home,guest,budget,todolist,event,profile,invite,about;
    String useridshow,username,eventdate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bnview=findViewById(R.id.bnView);
        toolbar=findViewById(R.id.menu_toolbar);
        bnview.setSelectedItemId(R.id.menu);
        setSupportActionBar(toolbar);
        home=findViewById(R.id.home);
        guest=findViewById(R.id.guest);
        budget=findViewById(R.id.budget);
        todolist=findViewById(R.id.todolist);
        event=findViewById(R.id.event);
        profile=findViewById(R.id.profile);
        invite=findViewById(R.id.invite);
        about=findViewById(R.id.about);
        useridshow=getIntent().getStringExtra("useridtransfer");
        username=getIntent().getStringExtra("username");
        eventdate=getIntent().getStringExtra("date");
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Guest_activity.class);
                startActivity(i);
                finish();
            }
        });
        budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),Budget_activity.class);
                startActivity(i);
                finish();
            }
        });
        todolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),checklist_activity.class);
                startActivity(i);
                finish();
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),show_event.class);
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),profile.class);
                startActivity(i);
            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),sendsms.class);
                startActivity(i);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),aboutproject.class);
                startActivity(i);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("username",username);
                i.putExtra("date",eventdate);
                i.putExtra("useridtransfer",useridshow);
                setResult(RESULT_OK,i);
                overridePendingTransition(2,2);
                finish();
            }
        });

        bnview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id)
                {
                    case R.id.nav_home:
                        Intent intent4=new Intent(getApplicationContext(),MainActivity.class);
                        intent4.putExtra("username",username);
                        intent4.putExtra("date",eventdate);
                        intent4.putExtra("useridtransfer",useridshow);
                        setResult(RESULT_OK,intent4);
                        overridePendingTransition(2,2);
                        finish();
                        return true;

                    case R.id.check_list:
                        Intent intent=new Intent(getApplicationContext(),checklist_activity.class);
                        startActivity(intent);
                        setResult(RESULT_OK,intent);
                        overridePendingTransition(2,2);
                        finish();
                        return true;
                    case  R.id.guests:
                        Intent intent3=new Intent(getApplicationContext(),Guest_activity.class);
                        startActivity(intent3);
                        overridePendingTransition(2,2);
                        finish();
                        return true;
                    case R.id.budget:
                        Intent intent2=new Intent(getApplicationContext(),Budget_activity.class);
                        startActivity(intent2);
                        overridePendingTransition(2,2);
                        finish();
                        return true;
                    case R.id.menu:
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater=getMenuInflater();
    inflater.inflate(R.menu.frag_menu,menu);
    return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "SignOut Successful", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(this,Loginpage.class);
                overridePendingTransition(0,0);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}