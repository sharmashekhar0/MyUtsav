package com.example.myutsav;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class newtaskform extends AppCompatActivity {

    String strname,strnote,strcat,strstatus,struserid;
    EditText name,note,category;
    TextView statuscomplete,statuspending;
    Button add;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtaskform);
        name=findViewById(R.id.name);
        database2 g=new database2(this);
        note=findViewById(R.id.note);
        category=findViewById(R.id.category);
        statuscomplete=findViewById(R.id.complete);
        statuspending=findViewById(R.id.pending);
        add=findViewById(R.id.addtask);
        struserid= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strname=name.getText().toString();
                strnote= note.getText().toString();
                strcat= category.getText().toString();
                if(TextUtils.isEmpty(strname))
                {
                    name.setError("Task Name is required");
                    return;
                }
                if(TextUtils.isEmpty(strnote))
                {
                    note.setError("Task Note is required");
                    return;
                }
                if(TextUtils.isEmpty(strcat))
                {
                    category.setError("Task Category is required");
                    return;
                }
                else {
                    Boolean i = g.insert_data(struserid, strname, strcat, strstatus, strnote);
                    if (i == true) {
                        Toast.makeText(getApplicationContext(), "Task add", Toast.LENGTH_SHORT).show();
                        note.setText("");
                        category.setText("");
                        name.setText("");
                    } else {
                        Toast.makeText(newtaskform.this, "not add", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void status(View view)
    {
        switch (view.getId())
        {
            case R.id.complete:
                strstatus=statuscomplete.getText().toString();
                break;
            case R.id.pending:
                strstatus=statuspending.getText().toString();
                break;
            default:
                break;
        }
    }
}