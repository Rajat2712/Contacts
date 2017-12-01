package com.example.rajat.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Rajat on 7/28/2016.
 */
public class EditContact extends AppCompatActivity {
    EditText fname;
    EditText lname;
    EditText phone;
    EditText email;

    Contact contact;
    DatabaseHandler db;
    int p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         fname=(EditText)findViewById(R.id.tvfname);
         lname=(EditText)findViewById(R.id.tvlname);
         phone=(EditText)findViewById(R.id.tvphone);
         email=(EditText)findViewById(R.id.tvemail);
        Intent a=getIntent();
         p=a.getExtras().getInt("id");
        //id.setText(MainActivity.contact.getID());
        /*
        fname.setText(MainActivity.contact.getFName());
        lname.setText(MainActivity.contact.getLName());
        phone.setText(MainActivity.contact.getPhoneNumber());
        email.setText(MainActivity.contact.getEmail());
        */
        db=new DatabaseHandler(this);
        contact=db.getContact(p);
        fname.setText(contact.getFName());
        lname.setText(contact.getLName());
        phone.setText(contact.getPhoneNumber());
        email.setText(contact.getEmail());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
               String Sfname = fname.getText().toString().trim();
               String Slname = lname.getText().toString().trim();
               String  Sphone = phone.getText().toString().trim();
               String  Semail = email.getText().toString().trim();
                Contact c=new Contact(Sfname,Slname,Sphone,Semail);
                db.updateContact(c,p);
                Toast.makeText(getApplicationContext(),"Update Successful",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),ViewContact.class);
                i.putExtra("id", p);
                startActivity(i);

                break;
            case R.id.cancle:
                Intent a=new Intent(getApplicationContext(),ViewContact.class);
                a.putExtra("id", p);
                startActivity(a);
                break;
            case R.id.delete:
                db.deleteContact(p);
                Toast.makeText(getApplicationContext(),"Delete Successful",Toast.LENGTH_SHORT).show();
                Intent d=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(d);
                break;






        }
        return super.onOptionsItemSelected(item);
    }
}
