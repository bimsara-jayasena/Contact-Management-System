package org.example.View;

import org.example.Controller.Controller;
import org.example.Model.Model;

import java.util.*;

public class View {
    private final Scanner scanner=new Scanner(System.in);

    public View(){
        String output="0";
        while(!output.equals("6")){
            output=getUserInput();
            switch (output){
                case "1": addUser();break;
                case "2":view();break;
                case "3":search();break;
                case "4":update();break;
                case "5":delete();break;
            }
        }
    }
    public String getUserInput(){
        String[] list={
                "1.Add",
                "2.View",
                "3.Search",
                "4.Update",
                "5.Delete",
                "6.Exit"
        };
        for (String l:list) {
            System.out.println(l);
        }
        System.out.println("What do you Want to Do:");


        return scanner.nextLine();
    }

    public void addUser(){


        System.out.println("Enter Name:");
        String name=scanner.nextLine();

        System.out.println("Enter Contact No.:");
        String contact=scanner.nextLine();

        Controller.add(name,contact);
        System.out.println("Done");
    }
    public void view(){
           List<Model> list=Controller.view();
            if(list.isEmpty()){
                System.out.println("No Entries");
            }else{
                System.out.println("Total: "+list.size());

                list.forEach(contact->{
                    System.out.println("Name: "+contact.getName());
                    System.out.println("Contact No.: "+contact.getContactNo()+"\n");

                });
            }
    }
    public void search(){

        System.out.print("Enter Name you want to find: ");
        String name=scanner.nextLine();

        List<Model> list=Controller.findUserByName(name);
        if(list.isEmpty()){
            System.out.println("No Entries");
        }else{
            System.out.println("Contact Found!");
            list.forEach(contact->{
                    System.out.println("Name: "+contact.getName());
                    System.out.println("Contact No.: "+contact.getContactNo());
            });
        }
    }
    public void update(){

        System.out.print("Enter Contact Name you want to update: ");
        String contactName=scanner.nextLine();

        System.out.print("Enter new Name: ");
        String newName=scanner.nextLine();
        System.out.print("Enter new number: ");
        String newNumber=scanner.nextLine();

        if(Controller.update(contactName,newName,newNumber)){
            System.out.println("Contact Updated!");
        }else{
            System.out.println("Error");
        }
    }
    public void delete(){
        ArrayList<String> list=new ArrayList<>();

        System.out.print("Enter 'm' for multiple deletion,enter 's' for single deletion: ");
        String selection=scanner.nextLine();

        if(selection.equals("s")){
            System.out.print("Enter Contact Name you want to Delete: ");
            list.add(scanner.nextLine());


            if(Controller.delete(list)){
                System.out.println("Contact Removed!");
            }else{
                System.out.println("Error");
            }
        }else{
            System.out.print("Enter Contact Names you want to Delete: ");//eg:-devid,anna etc
            String contactName=scanner.nextLine();
            String[] names=contactName.split(",");
            list.addAll(Arrays.asList(names));

            if(Controller.delete(list)){
                System.out.println("Contacts Removed!");
            }else{
                System.out.println("Error");
            }
        }

    }


}
