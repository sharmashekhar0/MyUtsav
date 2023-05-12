package com.example.myutsav;

import static com.example.myutsav.R.id.bnView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Budget_activity extends AppCompatActivity {
    BottomNavigationView bnview;
    String useridshow,username,eventdate;
    Button addbudget,showbudgetbtn;
    budgetdb db;
    String strtotal,strgift,strdecoration,strcatering,strcarbus,strother,strcamping,streventname;
    EditText total,gift,decoration,catering,carbus,other,camping,eventname1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        bnview=findViewById(bnView);
        eventname1=findViewById(R.id.eventname);
        bnview.setSelectedItemId(R.id.budget);
        useridshow=getIntent().getStringExtra("useridtransfer");
        username=getIntent().getStringExtra("username");
        eventdate=getIntent().getStringExtra("date");
        showbudgetbtn=findViewById(R.id.showbudget);
        total=findViewById(R.id.totalBudget);
        gift=findViewById(R.id.giftBudget);
        decoration=findViewById(R.id.tentbudget);
        catering=findViewById(R.id.cateringBudget);
        carbus=findViewById(R.id.TravelBudget);
        other=findViewById(R.id.otherBudget);
        camping=findViewById(R.id.campBudget);
        db=new budgetdb(this);
        addbudget=findViewById(R.id.addbudget);
        showbudgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),showbudget.class);
                startActivity(i);
            }
        });
        addbudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                streventname=eventname1.getText().toString();
                strtotal = total.getText().toString();
                strgift = gift.getText().toString();
                strdecoration = decoration.getText().toString();
                strcatering = catering.getText().toString();
                strcarbus = carbus.getText().toString();
                strother = other.getText().toString();
                strcamping = camping.getText().toString();
                if (TextUtils.isEmpty(streventname))
                {
                    eventname1.setError("Event Name is required!!");
                    return;
                }
                if (TextUtils.isEmpty(strgift)) {
                    strgift = "0";
                    return;
                }
                if (TextUtils.isEmpty(strdecoration)) {
                    strdecoration = "0";
                    return;
                }
                if (TextUtils.isEmpty(strcatering)) {
                    strcatering = "0";
                    return;
                }
                if (TextUtils.isEmpty(strcarbus)) {
                    strcarbus = "0";
                    return;
                }
                if (TextUtils.isEmpty(strother)) {
                    strother = "0";
                    return;
                }
                if (TextUtils.isEmpty(strcamping)) {
                    strcamping = "0";
                    return;
                }
                int  totalint=Integer.parseInt(strgift)+Integer.parseInt(strdecoration)+Integer.parseInt(strcatering)+Integer.parseInt(strcarbus)+Integer.parseInt(strother)+Integer.parseInt(strcamping);

                if (TextUtils.isEmpty(strtotal)) {
                    total.setError("Total Budget Empty");
                    return;
                }

                else {
                    Toast.makeText(Budget_activity.this,String.valueOf(totalint), Toast.LENGTH_SHORT).show();
                    Boolean i = db.insert_data(streventname,strtotal, strgift, strdecoration, strcatering, strcarbus, strother, strcamping);
                    if (i == true) {
                        Toast.makeText(Budget_activity.this, "New Budget Added", Toast.LENGTH_SHORT).show();
                        eventname1.setText("");
                        total.setText("");
                        gift.setText("");
                        decoration.setText("");
                        catering.setText("");
                        carbus.setText("");
                        other.setText("");
                        camping.setText("");
                    } else {
                        Toast.makeText(Budget_activity.this, "No new Budget Added", Toast.LENGTH_SHORT).show();
                    }
                }

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
                        Intent intent1=new Intent(getApplicationContext(),Guest_activity.class);
                        startActivity(intent1);
                        overridePendingTransition(2,2);
                        finish();
                        return true;

                    case R.id.budget:
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