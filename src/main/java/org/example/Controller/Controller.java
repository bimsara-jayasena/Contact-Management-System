package org.example.Controller;

import org.example.Model.Model;
import org.example.View.View;
import org.example.DAO.Dao;

import java.util.ArrayList;
import java.util.List;

public class Controller {



    public static void add(String name,String contactNo){

        Model model=new Model(name,contactNo);
        Dao.create(model);

    }
    public static List<Model> view(){
        List<Model> list=Dao.read();
        return list;

    }
    public static List<Model> findUserByName(String name){
        List<Model> models=Dao.findContact(name);
        return models;
    }
    public static boolean update(String contactName,String newName,String newContactNo){
        return Dao.update(contactName,newName,newContactNo);
    }
    public static boolean delete(String fullName){
        return Dao.delete(fullName);
    }
}
