package com.example.myutsav;

import static com.example.myutsav.R.id.bnView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Guest_activity extends AppCompatActivity {

    BottomNavigationView bnview;
    SwipeRefreshLayout swaprefresh;
    RecyclerView recyclerView;
    ArrayList<guestdatamodel> dataholder;
    @SuppressLint("MissingInflatedId")
    Toolbar toolbar;
    Button btn;
    String useridshow,username,eventdate;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar=findViewById(R.id.guest_toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_guest);
        bnview=findViewById(R.id.bnView);
        bnview.setSelectedItemId(R.id.guests);
     btn=findViewById(R.id.floatbtn);
        swaprefresh=(SwipeRefreshLayout)findViewById(R.id.swapid);
        useridshow=getIntent().getStringExtra("useridtransfer");
        username=getIntent().getStringExtra("username");
        eventdate=getIntent().getStringExtra("date");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),sendsms.class);
                startActivity(i);
            }
        });
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database3 g=new database3(this);
        Cursor t=g.getInfo();
        dataholder=new ArrayList<guestdatamodel>();
        while (t.moveToNext())
        {
            guestdatamodel obj=new guestdatamodel(t.getString(1),t.getString(2),t.getString(3),t.getString(4));
            dataholder.add(obj);
        }
        myadapterguest myadapterobj=new myadapterguest(dataholder,getApplicationContext());
        recyclerView.setAdapter(myadapterobj);
        swaprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                swaprefresh.setRefreshing(false);

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
                        overridePendingTransition(2,2);
                        finish();
                        return true;

                    case R.id.guests:
                        return true;

                    case R.id.budget:
                        Intent intent2=new Intent(getApplicationContext(),Budget_activity.class);
                        startActivity(intent2);
                        overridePendingTransition(2,2);
                        finish();
                        return true;
                    case R.id.menu:
                        Intent intent3=new Intent(getApplicationContext(),Menu_activity.class);
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
