package com.example.rajat.contacts;

/**
 * Created by Rajat on 7/26/2016.
 */
public class Contact {

    int _id;
    String _fname;
    String _lname;
    String _phone_number;
    String _email;

    // Empty constructor
    public Contact() {

    }

    // constructor
    public Contact(int _id,String _fname, String _lname, String _phone_number, String _email) {
        this._id=_id;
        this._fname = _fname;
        this._lname = _lname;
        this._phone_number = _phone_number;
        this._email = _email;
    }

    public Contact(String _fname, String _lname, String _phone_number, String _email) {
        this._fname = _fname;
        this._lname = _lname;
        this._phone_number = _phone_number;
        this._email = _email;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getLName() {
        return this._lname;
    }


    public void setLName(String _lname) {
        this._lname = _lname;
    }

    public String getFName() {
        return this._fname;
    }

    public void setFName(String _fname) {
        this._fname = _fname;
    }


    public String getPhoneNumber() {
        return this._phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this._phone_number = phone_number;
    }


    public void setEmail(String _email) {
         this._email = _email;
    }

    public String getEmail(){
        return this._email;
    }










}
