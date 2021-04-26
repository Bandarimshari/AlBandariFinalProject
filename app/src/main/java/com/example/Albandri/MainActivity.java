package com.example.Albandri;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DBHelper DB;
    EditText fname,lname, email, phone;
    Button insert, update, delete, view ,wrapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DB = new DBHelper(this);
        wrapper = findViewById(R.id.wrapper);
        fname = findViewById(R.id.first_name);
        lname = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Full Name :"+res.getString(0)+" "+res.getString(1)+"\n");
                    buffer.append("Email Address :"+res.getString(2)+"\n");
                    buffer.append("Phone Number :"+res.getString(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
        wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWrapper();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fnameTXT =fname.getText().toString();
                String lnameTXT = lname.getText().toString();
                String emailTXT = email.getText().toString();
                String phoneTXT = phone.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(fnameTXT,lnameTXT, emailTXT, phoneTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneTXT = phone.getText().toString();
                Boolean checkudeletedata = DB.deletedata(phoneTXT);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fnameTXT =fname.getText().toString();
                String lnameTXT = lname.getText().toString();
                String emailTXT = email.getText().toString();
                String phoneTXT = phone.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(fnameTXT,lnameTXT, emailTXT, phoneTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
    }

    public void openWrapper(){
        Intent intent = new Intent(this, Wrapper.class);
        startActivity(intent);
    }
}