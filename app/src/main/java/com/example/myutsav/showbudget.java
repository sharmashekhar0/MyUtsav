package com.example.myutsav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class showbudget extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swaprefresh;
    ArrayList<budgetdatamodel> dataholder;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showbudget);
        recyclerView=findViewById(R.id.recycler);
        budgetdb g=new budgetdb(this);
        swaprefresh=findViewById(R.id.swapid);
        Cursor t=g.getInfo();
        dataholder=new ArrayList<budgetdatamodel>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        while (t.moveToNext())
        {
            budgetdatamodel obj=new budgetdatamodel(t.getString(1),t.getString(2),t.getString(3),t.getString(4),t.getString(5),t.getString(6),t.getString(7),t.getString(8));
            dataholder.add(obj);
        }
        mybudgetadapter myadapterobj=new mybudgetadapter(dataholder,getApplicationContext());
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
    }
}