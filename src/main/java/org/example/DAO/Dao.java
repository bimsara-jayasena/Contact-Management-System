package org.example.DAO;

import org.example.Model.Model;
import org.example.Util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Dao {


    public static String create(Model model) {
        String status = "";

        String sql = "INSERT INTO contacts(name,contactNo) VALUES(?,?)";
        String checkQry = "SELECT * FROM contacts WHERE name=?";
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                PreparedStatement checkSt = connection.prepareStatement(checkQry);

        ) {

            checkSt.setString(1, model.getName());

            ResultSet res = checkSt.executeQuery();
            if (res.next() && res.getInt(1) > 0) {
                status = "Item Already exist";
            } else {
                statement.setString(1, model.getName());
                statement.setString(2, model.getContactNo());
                statement.executeUpdate();
                status = "new Data Added";
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;

    }

    public static List<Model> read() {
        String sql = "SELECT * FROM contacts";

        List<Model> models = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet res = statement.executeQuery();) {
            while (res.next()) {
                Model model = new Model();
                model.setName(res.getString("name"));
                model.setContactNo(res.getString("contactNo"));
                models.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

    public static List<Model> findContact(String name) {
        List<Model> list = new ArrayList<>();
        String sql = "SELECT * FROM contacts";
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);

        ) {

            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Model model = new Model();
                if (res.getString("name").toLowerCase().contains(name.toLowerCase())) {
                    model.setName(res.getString("name"));
                    model.setContactNo(res.getString("contactNo"));
                    list.add(model);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean update(String userName, String newName, String newContactNo) {
        boolean updated = false;
        int id = 0;
        //find contact is exist or not
        List<Model> modelList = findContact(userName);

        if (!modelList.isEmpty()) {
            //get contact Id
            id = getUserId(userName);

            String sql = "UPDATE contacts SET name=?,contactNo=? WHERE id=?";
            try (
                    Connection connection = DatabaseUtil.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql);
            ) {
                statement.setString(1, newName);
                statement.setString(2, newContactNo);
                statement.setInt(3, id);

                statement.executeUpdate();
                updated = statement.executeUpdate() > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated;

    }

    public static boolean delete(ArrayList<String> list) {
        String sql = "DELETE FROM contacts WHERE id=?";
        boolean deleted = false;
        //find contact exist or not
        for (String name : list) {
            List<Model> models = findContact(name);
            if (!models.isEmpty()) {
                //get contact ID
                int id = getUserId(name);
                //perform delete operation
                try (
                        Connection connection = DatabaseUtil.getConnection();
                        PreparedStatement statement = connection.prepareStatement(sql);
                ) {
                    statement.setInt(1, id);
                    deleted = statement.executeUpdate() > 0;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(name + " not found in the contact list");
            }
        }

        return deleted;


    }

    public static int getUserId(String userName) {
        int id = 0;
        String sql = "SELECT id FROM contacts WHERE name=?";
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, userName);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                id = res.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}


