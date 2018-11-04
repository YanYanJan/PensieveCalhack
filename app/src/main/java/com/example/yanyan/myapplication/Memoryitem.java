package com.example.yanyan.myapplication;

import android.view.LayoutInflater;

/**
 * Created by yanyan on 11/3/18.
 */

public class Memoryitem {
    public int id;
    public String Date;
    public String Location;
    public byte[] photo;

    //    public Post(String booktitle, String author, String hashtag, String quote, String rate,
//                String Reviews,String Audiopath,String Bookcover, String Datetime,String fetch){
    public Memoryitem(String Date, String Location, byte[] photo){
//String book, String author, String tags, String quote, String rate, String thoughts,String record,String bookcover, String datetime


        this.id = id;
        this.Date =Date;
        this.Location = Location;

    }


    public String getdate() {
        return Date;
    }
    public void setdate(String Date) {
        this.Date = Date;
    }

    public String getLocation() {
        return Location;
    }
    public void setLocation(String Location) {
        this.Location = Location;
    }
    public byte[] getphoto() {
        return photo;
    }
    public void setBookcover(byte[] photo) {
        this.photo = photo;
    }

}
