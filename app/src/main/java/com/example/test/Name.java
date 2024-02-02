package com.example.test;

public class Name {
    String img;
    String fname;
    String lname;
    String email;


    public Name(String img, String fname, String lname, String email) {
        this.img = img;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public Name() {

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail(){ return email;}

    public void setEmail(String email) {
        this.email = email;
    }



}

