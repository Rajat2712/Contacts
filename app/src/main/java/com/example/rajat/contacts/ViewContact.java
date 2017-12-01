package com.example.rajat.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewContact extends AppCompatActivity{
   // int p;
    int id;
    Contact contact;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_contact);
        //TextView id=(TextView)findViewById(R.id.tvid);
        Intent i=getIntent();
        if(i!=null){
        id=i.getExtras().getInt("id");}
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView fname=(TextView)findViewById(R.id.tvfname);
        TextView lname=(TextView)findViewById(R.id.tvlname);
        TextView phone=(TextView)findViewById(R.id.tvphone);
        TextView email=(TextView)findViewById(R.id.tvemail);
        db=new DatabaseHandler(this);
        contact=db.getContact(id);
        //id.setText(MainActivity.contact.getID());
        /*fname.setText(MainActivity.contact.getFName());
        lname.setText(MainActivity.contact.getLName());
        phone.setText(MainActivity.contact.getPhoneNumber());
        email.setText(MainActivity.contact.getEmail());*/
        fname.setText(contact.getFName());
        lname.setText(contact.getLName());
        phone.setText(contact.getPhoneNumber());
        email.setText(contact.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.edit:
                Intent a=new Intent(getApplicationContext(),EditContact.class);
                a.putExtra("id",id);
                startActivity(a);
        }

        return super.onOptionsItemSelected(item);
    }
}
