package com.example.myutsav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class eventform extends AppCompatActivity {

    String strname,strnote,strcat,strdate,strstatus,struserid;
    EditText name,note,date,category;
    TextView statuscomplete,statuspending;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklisteventform);
        name=findViewById(R.id.name);
        database g=new database(this);
        note=findViewById(R.id.note);
        date=findViewById(R.id.date);
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
               strdate= date.getText().toString();
               strcat= category.getText().toString();
               if(TextUtils.isEmpty(strname))
               {
                   name.setError("Event Name is required");
                   return;
               }
                if(TextUtils.isEmpty(strnote))
                {
                    note.setError("Event Note is required");
                    return;
                }
                if(TextUtils.isEmpty(strdate))
                {
                    date.setError("Event Date is required");
                    return;
                }
               if(TextUtils.isEmpty(strcat))
                {
                    category.setError("Event Category is required");
                    return;
                }
                else {
                     Boolean i = g.insert_data(struserid, strname, strdate, strcat, strstatus, strnote);
                    if (i == true) {
                        Toast.makeText(getApplicationContext(), "Event add", Toast.LENGTH_SHORT).show();
                        note.setText("");
                        date.setText("");
                        category.setText("");
                        name.setText("");
                    } else {
                        Toast.makeText(eventform.this, "not add", Toast.LENGTH_SHORT).show();
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