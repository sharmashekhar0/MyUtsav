package com.example.myutsav;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class show_event extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<model> dataholder;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_event);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database g=new database(this);
        Cursor t=new database(this).getInfo();
        dataholder=new ArrayList<model>();

      /*  if(t.getCount()==0)
        {
            Toast.makeText(this, "no data found", Toast.LENGTH_SHORT).show();
        }
        StringBuffer buffer=new StringBuffer();
        while (t.moveToNext())
        {
            buffer.append("userid :"+t.getString(0)+"\n");
            buffer.append("eventname :"+t.getString(1)+"\n");
            buffer.append("eventdate :"+t.getString(2)+"\n");
            buffer.append("eventnote :"+t.getString(3)+"\n");
            buffer.append("eventstatus :"+t.getString(4)+"\n");
            buffer.append("eventcategar :"+t.getString(5)+"\n");
        }*/
        while (t.moveToNext())
        {
            model obj=new model(t.getString(1),t.getString(2),t.getString(3),t.getString(4),t.getString(5));
            dataholder.add(obj);
        }
        myadapter myadapterobj=new myadapter(dataholder,getApplicationContext());
        recyclerView.setAdapter(myadapterobj);
     /*   AlertDialog.Builder builder=new AlertDialog.Builder(show_event.this);
        builder.setCancelable(true);
        builder.setTitle("event data");
        // builder.setMessage(buffer.toString());
        builder.show();*/
    }
}