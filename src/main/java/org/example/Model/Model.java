package org.example.Model;

public class Model {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private String contactNo;

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



    public Model(String name, String contactNo) {
        this.name = name;
        this.contactNo = contactNo;
    }
    public Model() {

    }
}
