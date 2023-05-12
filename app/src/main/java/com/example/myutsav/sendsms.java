package com.example.myutsav;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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
    Button smsbtn,watsapp,select;
    private static final int RESULT_PICK_CONTACT =1;
    private TextView phone;


    ProgressBar progressBar;
    String strname,strnumber,strmsg,straddress,strstatus,msgwatsapp,mapurl;
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
        watsapp=findViewById(R.id.whatsapp);
        statuspending=findViewById(R.id.pending);
        address=findViewById(R.id.guestaddress);
        smsbtn=findViewById(R.id.smsbtn);

        phone = findViewById (R.id.phone);
        select = findViewById (R.id.pickcontact);

        select.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult (in, RESULT_PICK_CONTACT);
            }
        });

        watsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    name.setError("Guest name is required");
                    return;
                }
               /* if (TextUtils.isEmpty(number.getText().toString())) {
                    number.setError("Guest mobile number is required");
                    return;
                }*/
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
                    mapurl="https://www.google.com/maps/place/"+straddress;
                    strmsg = "Dear " + name.getText().toString() + "\n\nWe cordially invite you to join us for " + msg.getText().toString() + ", at " + address.getText().toString() + ".\n\nWe would be delighted to have you with us to share in this special event."+"\n\n Event location on "+mapurl;
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT,strmsg);
                    intent.setType("text/plain");
                    intent.setPackage("com.whatsapp");
                    startActivity(intent);

                        Boolean i = g.insert_data(FirebaseAuth.getInstance().getCurrentUser().getUid().toString(),strname, strnumber,straddress,strstatus);
                    name.setText("");
                    number.setText("");
                    msg.setText("");
                    address.setText("");
                        if (i == true) {
                            Toast.makeText(getApplicationContext(), "Guest Added", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(sendsms.this, "Not Added", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });

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
                    mapurl="https://www.google.com/maps/place"+straddress;
                    strmsg = "Dear " + name.getText().toString() + "\n\nWe cordially invite you to join us for " + msg.getText().toString() + ", at " + address.getText().toString() + ".\n\nWe would be delighted to have you with us to share in this special event."+"\n\n Event location on "+mapurl;
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Toast.makeText(this, "Failed To pick contact", Toast.LENGTH_SHORT).show();
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;

        try {
            String phoneNo = null;
            Uri uri = data.getData ();
            cursor = getContentResolver ().query (uri, null, null,null,null);
            cursor.moveToFirst ();
            int phoneIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Phone.NUMBER);

            phoneNo = cursor.getString (phoneIndex);

            number.setText (phoneNo);


        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}