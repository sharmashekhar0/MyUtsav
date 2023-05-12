package com.example.myutsav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;
import com.example.myutsav.database;

public class registerpage extends AppCompatActivity {

    Button registerbtn;

    TextView logintext;
    EditText fullname1,email1,phone1,datepicking,password;
    ProgressBar progressbar1;
    private FirebaseAuth auth;
    private DatabaseReference  reference,dbref;
    public static final String TAG="TAG";
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);
        auth=FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
         registerbtn = findViewById(R.id.regbtn);
         logintext=findViewById(R.id.textviewregister);
         fullname1=findViewById(R.id.fullname);
        password=findViewById(R.id.pass);
         email1=findViewById(R.id.email);
         phone1=findViewById(R.id.phone);
         datepicking=findViewById(R.id.datepicker);
         progressbar1=findViewById(R.id.progressbar);
         auth=FirebaseAuth.getInstance();
         logintext.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(getApplicationContext(),Loginpage.class);
                 startActivity(intent);
             }
         });

         registerbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                   final  String email=email1.getText().toString().trim();
                   final String fullname=fullname1.getText().toString();
                   final String phone=phone1.getText().toString();
                   final String datepick=datepicking.getText().toString();
                   String password1=password.getText().toString();
              /*   Random random=new Random();
                 String ran= String.valueOf(random.nextInt(10000));
                 String userid;
                 userid=fullname.replaceAll(" ","").toLowerCase()+ran;*/

                   if(TextUtils.isEmpty(email))
                   {
                       email1.setError("Email is required!");
                       return;
                   }
                   if(TextUtils.isEmpty(datepick))
                   {
                       datepicking.setError("Event date is required!");
                   }
                   if(phone.length()!=10 && TextUtils.isDigitsOnly(phone))
                   {
                       phone1.setError("Phone number must 10 digit");
                       return;
                   }
                   if(TextUtils.isEmpty(password1))
                   {
                       password.setError("Password not satisfy!");
                       return;
                   }
                   if(TextUtils.isEmpty(datepick))
                   {
                       datepicking.setError("Event Date is required");
                       return;
                   }
                   if(TextUtils.isEmpty(fullname))
                   {
                       fullname1.setError("Name is required!");
                   }
                   else {
                       register(email,password1,fullname,phone,datepick);

                   }
             }
         });
    }

    private void register(String email,String password1,String fullname,String phone,String datepick)
    {
        auth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(registerpage.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful())
                  {
                    UploadData(email,password1,fullname,phone,datepick);
                      progressbar1.setVisibility(View.VISIBLE);
                      Toast.makeText(registerpage.this, "User Created", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(registerpage.this,MainActivity.class);
                      intent.putExtra("username",fullname);
                      intent.putExtra("date",datepick);
                      intent.putExtra("useridtransfer",email);
                      startActivity(intent);
                      finish();

                  }
                  else
                  {

                      Toast.makeText(registerpage.this,"Error:"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                      progressbar1.setVisibility(View.INVISIBLE);
                  }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(registerpage.this, "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void UploadData(String email, String password, String fullname, String phone, String datepick) {
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser firebaseuser=FirebaseAuth.getInstance().getCurrentUser();
        String userid=firebaseuser.getUid();
        UserHelperClass user=new UserHelperClass(email,password,fullname,phone,datepick);
        reference.child(userid).setValue(user);
    }

}
