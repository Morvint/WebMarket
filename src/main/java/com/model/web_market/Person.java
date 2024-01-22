package com.model.web_market;

public class Person {
    private String name;
    private String password;
    private String status;

    public  Person(String n,String p, String s)
    {
        name = n;
        password = p;
        status = s;
    }
    public String getName() {return name;}
    public String getPassword() {return  password;}
    public String getStatus() {return  status;}
}
