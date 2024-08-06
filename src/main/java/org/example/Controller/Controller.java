package org.example.Controller;

import org.example.Model.Model;
import org.example.DAO.Dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Controller {
    public static void add(String name,String contactNo){
        Dao.create(new Model(name,contactNo));

    }
    public static List<Model> view(){
        return Dao.read();
    }
    public static List<Model> findUserByName(String name){
        return Dao.findContact(name);
    }
    public static boolean update(String contactName,String newName,String newContactNo){
        return Dao.update(contactName,newName,newContactNo);
    }
    public static boolean delete(ArrayList<String> list){
        return Dao.delete(list);
    }
}
