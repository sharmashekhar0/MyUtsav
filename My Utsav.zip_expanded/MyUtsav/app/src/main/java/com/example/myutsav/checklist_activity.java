package com.example.myutsav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class checklist_activity extends AppCompatActivity {
 BottomNavigationView bnview;
 Button newtask;
 String auth;
 DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<taskmodel> dataholder;

 String useridshow,username,eventdate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        bnview=findViewById(R.id.bnView);
        auth=FirebaseAuth.getInstance().getCurrentUser().getUid();
        bnview.setSelectedItemId(R.id.check_list);
        useridshow=getIntent().getStringExtra("useridtransfer");
        username=getIntent().getStringExtra("username");
        eventdate=getIntent().getStringExtra("date");
        reference= FirebaseDatabase.getInstance().getReference("users");
         newtask=findViewById(R.id.addnewtask);

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database2 g=new database2(this);
        Cursor t=g.getInfo();
        dataholder=new ArrayList<taskmodel>();
        while (t.moveToNext())
        {
            taskmodel obj=new taskmodel(t.getString(1),t.getString(2),t.getString(3),t.getString(4));
            dataholder.add(obj);
        }
        mytaskadapter myadapterobj=new mytaskadapter(dataholder,getApplicationContext());
        recyclerView.setAdapter(myadapterobj);


         newtask.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i=new Intent(getApplicationContext(),newtaskform.class);
                 startActivity(i);
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
                        return true;
                    case R.id.guests:
                        Intent intent1=new Intent(getApplicationContext(),Guest_activity.class);
                        startActivity(intent1);
                        overridePendingTransition(2,2);
                        finish();
                        return true;

                    case R.id.budget:
                        Intent intent=new Intent(getApplicationContext(),Budget_activity.class);
                      /*  intent.putExtra("username",username);
                        intent.putExtra("date",eventdate);
                        intent.putExtra("useridtransfer",useridshow);
                        setResult(RESULT_OK,intent);*/
                        startActivity(intent);
                        overridePendingTransition(2,2);
                        finish();
                        return true;

                    case R.id.menu:
                        Intent intent3=new Intent(getApplicationContext(),Menu_activity.class);
                        /*intent3.putExtra("username",username);
                        intent3.putExtra("date",eventdate);
                        intent3.putExtra("useridtransfer",useridshow);
                        setResult(RESULT_OK,intent3);*/
                        startActivity(intent3);
                        overridePendingTransition(2,2);
                        finish();
                        return true;
                }
                return false;
            }
        });
         }


}
