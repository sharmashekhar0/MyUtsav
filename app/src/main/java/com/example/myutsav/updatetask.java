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

public class updatetask extends AppCompatActivity {
    EditText name,note,category;
    TextView statuscomplete,statuspending;
    Button add,delete;
    String struserid,strstatus,strname,strnote,strcat,strStatus,updatenote,updatestatus;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatetask);
        name=findViewById(R.id.name);
        database2 g=new database2(this);
        note=findViewById(R.id.note);
        /*category=findViewById(R.id.category);*/
        statuscomplete=findViewById(R.id.complete);
        statuspending=findViewById(R.id.pending);
        add=findViewById(R.id.addtask);
        delete=findViewById(R.id.delete);
        strname=getIntent().getStringExtra("taskname");
        strnote=getIntent().getStringExtra("tasknote");
        strcat=getIntent().getStringExtra("taskcat");
        strStatus=getIntent().getStringExtra("taskstatus");
        struserid= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        note.setText(strnote);
        name.setText(strname);
        /*category.setText(strcat);*/
       //name edit text
        name.setFocusable(false);
       name.setEnabled(false);
       name.setCursorVisible(false);
       name.setKeyListener(null);
       name.setBackgroundColor(Color.GRAY);
       //note edittext
        /*category.setFocusable(false);
        category.setEnabled(false);
        category.setCursorVisible(false);
        category.setKeyListener(null);
        category.setBackgroundColor(Color.GRAY);*/
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean i=g.deletetask(strnote,strStatus,strname,strcat);
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
             Boolean i=g.update_task(updatenote,updatestatus,strname,strcat);
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