package com.example.myutsav;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class updateguest extends AppCompatActivity {
    EditText name,number,address;
    TextView statusaccept,statuspending;
    Button add,delete;
    String struserid,strstatus,strname,straddress,strnumber,strStatus,updateaddress,updatestatus,updatemsg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateguest);
        name=findViewById(R.id.guestname);
        database3 g=new database3(this);
        number=findViewById(R.id.guestnumber);
        address=findViewById(R.id.guestaddress);
        statusaccept=findViewById(R.id.accept);
        statuspending=findViewById(R.id.pending);
        add=findViewById(R.id.smsbtn);
        delete=findViewById(R.id.delete);
        strname=getIntent().getStringExtra("taskname");
        strnumber=getIntent().getStringExtra("tasknumber");
        straddress=getIntent().getStringExtra("taskaddress");
        strStatus=getIntent().getStringExtra("taskstatus");
        struserid= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        name.setText(strname);
        number.setText(strnumber);
        address.setText(straddress);
        //name edit text
        name.setFocusable(false);
        name.setEnabled(false);
        name.setCursorVisible(false);
        name.setKeyListener(null);
        name.setBackgroundColor(Color.GRAY);
        //note edittext.setFocusable(false);
        number.setEnabled(false);
        number.setCursorVisible(false);
        number.setKeyListener(null);
        number.setBackgroundColor(Color.GRAY);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean i=g.deletetask(strnumber,strname);
                if(i==true)
                {
                    Toast.makeText(getApplicationContext(), "Delete task Successful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), " No task deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateaddress=address.getText().toString();

                Boolean i=g.update_task(updateaddress,strname,strnumber,updatestatus);
                if(i==true)
                {
                    Toast.makeText(getApplicationContext(), "Update task Successful", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), " No task update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void status(View view)
    {
        switch (view.getId())
        {
            case R.id.accept:
                updatestatus=statusaccept.getText().toString();
                break;
            case R.id.pending:
                updatestatus=statuspending.getText().toString();
                break;
            default:
                break;
        }
    }
}