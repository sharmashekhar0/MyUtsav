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

public class updateevent extends AppCompatActivity {
    EditText name,note,category,date;
    TextView statuscomplete,statuspending;
    Button add,delete;
    String struserid,strstatus,strname,strnote,strcat,strStatus,updatenote,updatestatus,strdate,updatedate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateevent);
        name=findViewById(R.id.name);
        database g=new database(this);
        note=findViewById(R.id.note);
        category=findViewById(R.id.category);
        date=findViewById(R.id.date);
        statuscomplete=findViewById(R.id.complete);
        statuspending=findViewById(R.id.pending);
        add=findViewById(R.id.addtask);
        delete=findViewById(R.id.delete);
        strname=getIntent().getStringExtra("taskname");
        strnote=getIntent().getStringExtra("tasknote");
        strcat=getIntent().getStringExtra("taskcat");
        strStatus=getIntent().getStringExtra("taskstatus");
        strdate=getIntent().getStringExtra("taskdate");
        struserid= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        note.setText(strnote);
        name.setText(strname);
        category.setText(strcat);
        date.setText(strdate);
        //name edit text
        name.setFocusable(false);
        name.setEnabled(false);
        name.setCursorVisible(false);
        name.setKeyListener(null);
        name.setBackgroundColor(Color.GRAY);
        //category edittext
        category.setFocusable(false);
        category.setEnabled(false);
        category.setCursorVisible(false);
        category.setKeyListener(null);
        category.setBackgroundColor(Color.GRAY);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean i=g.deletetask(strname,strcat);
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
                updatenote=note.getText().toString();
                updatedate=date.getText().toString();
                Boolean i=g.update_task(updatenote,updatestatus,strname,strcat,updatedate);
                if(i==true)
                {
                    Toast.makeText(getApplicationContext(), "Update event Successful", Toast.LENGTH_SHORT).show();

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
            case R.id.complete:
                updatestatus=statuscomplete.getText().toString();
                break;
            case R.id.pending:
                updatestatus=statuspending.getText().toString();
                break;
            default:
                break;
        }
    }
}