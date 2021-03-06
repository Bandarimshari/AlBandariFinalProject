package com.example.Albandri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Insert extends AppCompatActivity
{
   EditText fname,lname,email,phone;
   Button submit,back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);

        fname=(EditText)findViewById(R.id.add_fname);
        email=(EditText)findViewById(R.id.add_email);
        lname=(EditText)findViewById(R.id.add_lname);
        phone=(EditText)findViewById(R.id.add_phone);

        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FirebaseActivity.class));
                finish();
            }
        });

        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }

    private void processinsert()
    {
        Map<String, Object> map=new HashMap<>();
        map.put("first_name",fname.getText().toString());
        map.put("lname",lname.getText().toString());
        map.put("email",email.getText().toString());
        map.put("phone",phone.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("users").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       fname.setText("");
                       lname.setText("");

                      email.setText("");
                       phone.setText("");
                        Toast.makeText(getApplicationContext(),"Inserted Successfully", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert", Toast.LENGTH_LONG).show();
                    }
                });

    }
}