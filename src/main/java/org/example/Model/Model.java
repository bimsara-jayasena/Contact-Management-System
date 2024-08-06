package org.example.Model;

public class Model {

    private String name;
    private String contactNo;
    public Model(String name, String contactNo) {
        this.name = name;
        this.contactNo = contactNo;
    }
    public Model() {

    }
    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }




}
