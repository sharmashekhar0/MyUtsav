package com.example.myutsav;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class sendsms extends AppCompatActivity {


    EditText name,number,msg,address;
    TextView statusaccepted,statuspending;
    Button smsbtn;
    ProgressBar progressBar;
    String strname,strnumber,strmsg,straddress,strstatus;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database3 g=new database3(this);
        setContentView(R.layout.activity_sendsms);
        progressBar=findViewById(R.id.progressbar);
        name=findViewById(R.id.guestname);
        number=findViewById(R.id.guestnumber);
        msg=findViewById(R.id.guestmsg);
        strstatus="Sent";
        statuspending=findViewById(R.id.pending);
        address=findViewById(R.id.guestaddress);
        smsbtn=findViewById(R.id.smsbtn);
        smsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    name.setError("Guest name is required");
                    return;
                }
                if (TextUtils.isEmpty(number.getText().toString())) {
                    number.setError("Guest mobile number is required");
                    return;
                }
                if (TextUtils.isEmpty(msg.getText().toString())) {
                    name.setError("Guest message is required");
                    return;
                }
                if (TextUtils.isEmpty(address.getText().toString())) {
                    name.setError("Guest address is required");
                    return;
                } else {
                    strnumber = number.getText().toString();
                    straddress=address.getText().toString();
                    strname=name.getText().toString();
                    strmsg = " Hello! ' " + name.getText().toString() + " ' We are happy to invite ' " + msg.getText().toString() + " ' my event on address which is ' " + address.getText().toString() + " ' where organize party.";
                    if (ContextCompat.checkSelfPermission(sendsms.this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(sendsms.this, new String[]{android.Manifest.permission.SEND_SMS}, 0);
                    } else {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(strnumber, null, strmsg, null, null);
                        Toast.makeText(sendsms.this, "Message send to " + name.getText().toString(), Toast.LENGTH_SHORT).show();
                        name.setText("");
                        number.setText("");
                        msg.setText("");
                        address.setText("");
                            Boolean i = g.insert_data(FirebaseAuth.getInstance().getCurrentUser().getUid().toString(),strname, strnumber,straddress,strstatus);
                            if (i == true) {
                                Toast.makeText(getApplicationContext(), "Guest add", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(sendsms.this, "not add", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        });
    }
}