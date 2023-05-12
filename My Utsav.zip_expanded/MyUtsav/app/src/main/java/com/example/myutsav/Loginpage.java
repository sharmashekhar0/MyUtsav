package com.example.myutsav;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Loginpage extends AppCompatActivity {
    EditText login1,password1;
    TextView regtext;
    String user;
    Button loginbtn1;
    ProgressBar progressbar1;
    private FirebaseAuth auth=FirebaseAuth.getInstance();
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        login1=findViewById(R.id.email);
        password1=findViewById(R.id.password);
        regtext=findViewById(R.id.textreg);
        progressbar1=findViewById(R.id.progressbar);
        loginbtn1=findViewById(R.id.loginbtn);
        regtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),registerpage.class);
                startActivity(intent);
            }
        });

        loginbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=login1.getText().toString().trim();
                String password=password1.getText().toString();
                if (TextUtils.isEmpty(email))
                {
                    login1.setError("Email is empty!");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    password1.setError("Password is empty!");
                    return;
                }
                else {
                    progressbar1.setVisibility(View.VISIBLE);
                    loginfunction(email,password);

                }
         }
        });
    }

    private void loginfunction(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            reference=FirebaseDatabase.getInstance().getReference("users");
                           user= auth.getCurrentUser().getUid();
                           reference.child(user).addValueEventListener(new ValueEventListener() {
                               @Override
                               public void onDataChange(@NonNull DataSnapshot snapshot) {
                                   if(snapshot.exists()) {
                                       String name= snapshot.child("fullname").getValue(String.class);
                                   String event=snapshot.child("date").getValue(String.class);
                                   String email=snapshot.child("email").getValue(String.class);
                                   Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                   i.putExtra("fullname",name);
                                   i.putExtra("date",event);
                                   i.putExtra("useridtransfer",email);
                                   startActivity(i);
                                   progressbar1.setVisibility(View.GONE);
                                   finish();
                                   }

                               }

                               @Override
                               public void onCancelled(@NonNull DatabaseError error) {
                                   Toast.makeText(getApplicationContext(),"Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();

                               }
                           });

                        } else {
                            Toast.makeText(Loginpage.this, task.getException().getMessage() , Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser()==null)
        {
            progressbar1.setVisibility(View.GONE);
        }
        else {
            progressbar1.setVisibility(View.VISIBLE);
            if (auth.getCurrentUser() != null) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
                user = auth.getCurrentUser().getUid();
                ref.child(user).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String name = snapshot.child("fullname").getValue(String.class);
                            String event = snapshot.child("date").getValue(String.class);
                            String email = snapshot.child("email").getValue(String.class);
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.putExtra("fullname", name);
                            i.putExtra("date", event);
                            i.putExtra("useridtransfer", email);
                            startActivity(i);
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Loginpage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
/*private void loginfunction(String email,String password)
{
auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Loginpage.this,new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        user=auth.getCurrentUser().getUid();
        Toast.makeText(Loginpage.this, "uID"+user, Toast.LENGTH_SHORT).show();
        reference.child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    String validateuserid=snapshot.child(user).child("userid").getValue(String.class);
                    String fullname=snapshot.child(user).child("fullname").getValue(String.class);
                    String date=snapshot.child(user).child("date").getValue(String.class);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })
             checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String validateuserid=snapshot.child(userid1).child("userid").getValue(String.class);
                    String fullname=snapshot.child(userid1).child("fullname").getValue(String.class);
                    String date=snapshot.child(userid1).child("date").getValue(String.class);
                    String userid4=snapshot.child(userid1).child("userid").getValue(String.class);
                    try {
                        if(validateuserid.equals(userid1)) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Loginpage.this, MainActivity.class);
                                intent.putExtra("fullname",fullname);
                                intent.putExtra("date",date);
                                intent.putExtra("useridtransfer",userid4);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(Loginpage.this, "Error:" + task.getException(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(Loginpage.this, "Error:" + task.getException(), Toast.LENGTH_LONG).show();
                            progressbar1.setVisibility(View.GONE);
                        }
                    }catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Loginpage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
});*/


}


