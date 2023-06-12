package e_commerce.Model;

import e_commerce.Controller.Connect;

import javax.swing.*;

public class Customer {
    private Integer id;
    private String name;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void registerCustomer(String name, String email) {
        Connect connect = new Connect();
        String sql = "INSERT INTO Customer (nameCustomer, email) VALUES ('" + name + "','" + email + "')";
        connect.executeSQL(sql);
        JOptionPane.showMessageDialog(null, "Gravado com Sucesso!");
    }
}
