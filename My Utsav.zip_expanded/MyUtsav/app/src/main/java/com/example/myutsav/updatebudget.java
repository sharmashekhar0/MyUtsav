package com.example.myutsav;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updatebudget extends AppCompatActivity {
    Button updatebtn,deletebtn;
    String strtotal,strgift,strdecoration,strcatering,strcarbus,strother,strcamping,streventname;
    String updatestrtotal,updatestrgift,updatestrdecoration,updatestrcatering,updatestrcarbus,updatestrother,updatestrcamping;
    EditText total,gift,decoration,catering,carbus,other,camping,eventname;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatebudget);
        updatebtn=findViewById(R.id.updatebtnbudget);
        total=findViewById(R.id.totalBudget);
        gift=findViewById(R.id.giftBudget);
        eventname=findViewById(R.id.texteventname);
        decoration=findViewById(R.id.tentbudget);
        catering=findViewById(R.id.cateringBudget);
        carbus=findViewById(R.id.TravelBudget);
        other=findViewById(R.id.otherBudget);
        camping=findViewById(R.id.campBudget);
        strgift = gift.getText().toString();
        deletebtn=findViewById(R.id.deletebudget);

        eventname.setFocusable(false);
        eventname.setEnabled(false);
        eventname.setCursorVisible(false);
        eventname.setKeyListener(null);
        eventname.setBackgroundColor(Color.GRAY);
        strdecoration = decoration.getText().toString();
        strcatering = catering.getText().toString();
        strcarbus = carbus.getText().toString();
        strother = other.getText().toString();
        strcamping = camping.getText().toString();
        streventname=getIntent().getStringExtra("eventnamestring");
        strtotal = getIntent().getStringExtra("total");
        strgift = getIntent().getStringExtra("gift");
        strdecoration = getIntent().getStringExtra("decoration");
        strcatering = getIntent().getStringExtra("catering");
        strcarbus = getIntent().getStringExtra("carbus");
        strother = getIntent().getStringExtra("other");
        strcamping = getIntent().getStringExtra("camping");
        budgetdb db=new budgetdb(this);
        eventname.setText(streventname);
        total.setText(strtotal);
        gift.setText(strgift);
        decoration.setText(strdecoration);
        catering.setText(strcatering);
        carbus.setText(strcarbus);
        other.setText(strother);
        camping.setText(strcamping);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strtotal = total.getText().toString();
                strgift = gift.getText().toString();
                strdecoration = decoration.getText().toString();
                strcatering = catering.getText().toString();
                strcarbus = carbus.getText().toString();
                strother = other.getText().toString();
                strcamping = camping.getText().toString();
                Boolean i=db.update_task(streventname,strtotal,strgift,strdecoration,strcatering,strcarbus,strother,strcamping);
                if(i==true)
                {
                    Toast.makeText(getApplicationContext(), "Update budget Successful", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), " No budget update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean i=db.deletetask(streventname,strtotal,strgift,strdecoration,strcatering,strcarbus,strother,strcamping);
                if(i==true)
                {
                    Toast.makeText(getApplicationContext(), "Delete budget Successful", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), " No budget deleted", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}