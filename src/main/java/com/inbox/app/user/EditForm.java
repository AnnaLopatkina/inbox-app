package com.inbox.app.user;

import java.util.Set;

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
    private final String job;
    private final Set<Hobby> hobbies;
    private final Set<User> contact;

    public EditForm(String name, String firstname, String username, String job, String email, String city, String phone, String address, String birthday,
                    Gender gender, String description, Set<Hobby> hobbies, Set<User> contact){
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
        this.job = job;
        this.hobbies = hobbies;
        this.contact = contact;
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

    public String getJob(){return job;}

    public Gender getGender(){return gender;}

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public Set<User> getContact() {
        return contact;
    }

    public String toString(){
        String str = "";
        str = "name: " + name + "firstname: " + firstname + "username: " + username + "city: " + this.city + ", job: " + this.job + ", phone: "+this.phone+ ", address: "+this.address+", birthday: "+this.birthday +
                ", gender: "+this.gender+", description: "+this.description;;
        return str;
    }
}

