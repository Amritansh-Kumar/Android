package com.example.amritansh.contentproviderdemo.Models;

public class Contact {

    String number;
    String name;

    public Contact(String number, String name){
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
