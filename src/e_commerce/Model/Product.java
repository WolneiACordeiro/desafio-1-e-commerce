package e_commerce.Model;

import e_commerce.Controller.Connect;

import javax.swing.*;

public class Product {
    private Integer id;
    private String name;
    private double price;
    private Integer quantity;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void registerProduct(String name, Double price, Integer quantity) {
        Connect connect = new Connect();
        String sql = "INSERT INTO Product (nameProduct, price, quantity) VALUES ('" + name + "','" + price + "','" + quantity + "')";
        connect.executeSQL(sql);
        JOptionPane.showMessageDialog(null, "Gravado com Sucesso!");
    }
}
