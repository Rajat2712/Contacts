package com.example.rajat.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView searchView;
    ListView listView;
  public static DatabaseHandler db;
    Button btnSearch;
    TextView tvCount;
    int count;
    //private static final String Key_Id="id";
    private static final String Key_FName="fname";
    private static final String Key_LName="lname";
    private static final String Key_Ph_No="phone_number";
    private static final String Key_Email="email";
    private  List<Contact> contacts;
    public static Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView=(SearchView)findViewById(R.id.search_view);
        listView=(ListView)findViewById(R.id.list_view);
        btnSearch=(Button)findViewById(R.id.btnSearch);
        tvCount=(TextView)findViewById(R.id.tvCount);

        db=new DatabaseHandler(this);
        getData();
        count=db.getContactsCount();
        tvCount.setText("Total Contacts: "+ count);

        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),ViewContact.class);
                i.putExtra("id",contacts.get(position).getID());
                startActivity(i);
            }
        });
        setupSearchView();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddContact.class);
                startActivity(i);

            }
        });




    }

    public  void getData(){
        try {
            contacts = db.getAllContacts();
        }catch (Exception e) {
            e.printStackTrace();
        }

        setDataIntoList();


    }

    public  void setDataIntoList() {


            if(contacts!=null) {
                List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < contacts.size(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(Key_FName, contacts.get(i).getFName());
                    map.put(Key_LName, contacts.get(i).getLName());
                    fillMaps.add(map);
                }
                String[] from = new String[]{Key_FName, Key_LName};
                int[] to = new int[]{R.id.fName, R.id.lName};
                SimpleAdapter adapter = new SimpleAdapter(this, fillMaps,
                        R.layout.custom, from, to);
                listView.setAdapter(adapter);

            }

    }


    private void setupSearchView() {
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            listView.clearTextFilter();
        } else {
            listView.setFilterText(newText.toString());
        }
        return true;
    }
}
