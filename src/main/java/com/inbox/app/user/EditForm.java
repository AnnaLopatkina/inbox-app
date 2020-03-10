package com.inbox.app.user;

import java.util.HashSet;

public class EditForm {

    private final String name;
    private final String firstname;
    private final String username;
    private final String email;
    private final String city ;
    private final String  phone ;
    private final String address ;
    private final String birthday ;
    private final Gender gender ;
    private final String description ;

    public EditForm(String name , String firstname , String username , String email, String city , String phone , String address ,String birthday ,
                    Gender gender ,String description){
        this.name = name ;
        this.firstname = firstname ;
        this.email = email ;
        this.username = username;
        this.city = city ;
        this.phone = phone ;
        this.address = address ;
        this.birthday = birthday ;
        this.gender = gender ;
        this.description = description ;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getCity(){return city;}

    public String getPhone(){return phone;}

    public String getAddress(){return address;}

    public String getBirthday(){return birthday;}

    public String getDescription(){return description;}
}

