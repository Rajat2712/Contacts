package com.example.rajat.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajat on 7/26/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String Key_Id="id";
    private static final String Key_FName="fname";
    private static final String Key_LName="lname";
    private static final String Key_Ph_No="phone_number";
    private static final String Key_Email="email_id";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager1";
    private static final String TABLE_NAME = "contacts";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE  IF NOT EXISTS  " + TABLE_NAME + "("
                + Key_Id + " INTEGER PRIMARY KEY AUTOINCREMENT," + Key_FName + " TEXT," + Key_LName + " TEXT,"
                + Key_Ph_No + " TEXT," + Key_Email +" TEXT UNIQUE" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


   public long addContact(Contact contact) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Key_FName, contact.getFName());
        values.put(Key_LName, contact.getLName());
        values.put(Key_Ph_No, contact.getPhoneNumber());
       values.put(Key_Email, contact.getEmail());


       //db.insert(TABLE_NAME, null, values);
        //db.close();

       return db.insert(TABLE_NAME, null, values);
    }

    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { Key_Id,
                        Key_FName,Key_LName,Key_Ph_No,Key_Email }, Key_Id + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4));
        db.close();
        return contact;
    }

    List<Contact> getContactN(String fname){

        SQLiteDatabase db=this.getReadableDatabase();
        List<Contact> contactList=new ArrayList<Contact>();
        Cursor cursor=db.query(TABLE_NAME, null, Key_FName + "=?", new String[]{String.valueOf(fname)}, null, null, null, null);
        if(cursor!=null)
            cursor.moveToFirst();

        do{
            Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4));
            contactList.add(contact);


        }while (cursor.moveToNext());

        db.close();
       return contactList;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setFName(cursor.getString(1));
                contact.setLName(cursor.getString(2));
                contact.setPhoneNumber(cursor.getString(3));
                contact.setEmail(cursor.getString(4));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }else {contactList=null; }
        cursor.close();
        db.close();


        return contactList;
    }


    public int updateContact(Contact contact,int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Key_FName, contact.getFName());
        values.put(Key_LName, contact.getLName());
        values.put(Key_Ph_No, contact.getPhoneNumber());
        values.put(Key_Email, contact.getEmail());

        return db.update(TABLE_NAME, values, Key_Id + " = ?",
                new String[] { String.valueOf(id) });
    }


    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, Key_Id + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }


    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }

}
