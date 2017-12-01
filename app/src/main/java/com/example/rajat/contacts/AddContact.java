package com.example.rajat.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;


public class AddContact extends AppCompatActivity {
    EditText etfname;
    EditText etlname;
    EditText etemail;
    EditText etphone;
    String fname;
    String lname;
    String email;
    String phone;
    Button add;
    int count;
    //long num;
    DatabaseHandler databaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etfname=(EditText)findViewById(R.id.input_fname);
        etlname=(EditText)findViewById(R.id.input_lname);
        etemail=(EditText)findViewById(R.id.input_email);
        etphone=(EditText)findViewById(R.id.input_phone);
        add=(Button)findViewById(R.id.btn_add);
        databaseHandler=new DatabaseHandler(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = etfname.getText().toString().trim();
                lname = etlname.getText().toString().trim();
                phone = etphone.getText().toString().trim();
                email = etemail.getText().toString().trim();
                if(fname.length()==0||lname.length()==0||phone.length()==0||email.length()==0){
                    Toast.makeText(getApplicationContext(),"Field cannot be empty",Toast.LENGTH_SHORT).show();
                }else {
                    long num;
                    try {
                        num=databaseHandler.addContact(new Contact(fname, lname, phone, email));
                    }catch (SQLException e){
                        num=-5;
                    }


                    if (num > 0) {
                        //Toast.makeText(getApplicationContext(), "Row number: " + num, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        count=databaseHandler.getContactsCount();
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Contact Saved" + count, Toast.LENGTH_SHORT).show();
                    }else if (num == -1)
                        Toast.makeText(getApplicationContext(), "Error Duplicate value", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Error while inserting", Toast.LENGTH_SHORT).show();


                }
                }

        });


    }
}
