package e_commerce.Model;

import e_commerce.Controller.Connect;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

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

    public void menuProduct () {
        int option;
        do {
            Scanner scanner = new Scanner(in);
            out.println(" 1 - Cadastrar \n 2 - Alterar \n 3 - Remover \n 0 - Voltar");
            out.print("Selecione uma opção: ");
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    out.println("#############| Cadastrar Produto Selecionado |#############");
                    this.createProduct();
                    break;
                case 2:
                    out.println("#############| Alterar Produto Selecionado |#############");
                    this.alterProduct();
                    break;
                case 3:
                    out.println("#############| Remover Produto Selecionado |#############");
                    this.removeProduct();
                    break;
                case 0:
                    out.println("Voltar");
                    break;
                default:
                    out.println("Opção inválida!");
                    break;
            }
        } while (option != 0);
    }

    private boolean productExists(int id) {
        Connect connect = new Connect();
        String sql = "SELECT COUNT(id) FROM Product WHERE id =" + id;
        connect.executeSQL(sql);
        return false;
    }
    public void createProduct() {
        Scanner scanner = new Scanner(in);
        out.print("Insira o nome do produto: ");
        var nameProduct = scanner.nextLine();
        out.print("Insira o preço do produto: ");
        var priceProduct = scanner.nextLine();
        out.print("Insira a quantidade do produto: ");
        var quantityProduct = scanner.nextLine();
        this.registerProduct(nameProduct, Double.parseDouble(priceProduct), Integer.parseInt(quantityProduct));
    }

    public void alterProduct() {
        Scanner scanner = new Scanner(in);
        out.print("Insira o código do produto: ");
        var id = scanner.nextLine();
        if (productExists(Integer.parseInt(id))) {
            out.print("Insira o novo nome do produto: ");
            var nameProduct = scanner.nextLine();
            out.print("Insira o novo preço do produto: ");
            var priceProduct = scanner.nextLine();
            out.print("Insira a nova quantidade do produto: ");
            var quantityProduct = scanner.nextLine();

            // Realizar a atualização do produto
            updateProduct(Integer.parseInt(id), nameProduct, Double.parseDouble(priceProduct), Integer.parseInt(quantityProduct));
        } else {
            out.println("Produto não encontrado.");
        }
    }

    public void removeProduct() {
        Scanner scanner = new Scanner(in);
        out.print("Insira o código do produto a ser removido: ");
        var id = scanner.nextLine();
        this.deleteProduct( Integer.parseInt(id));
    }
    public void registerProduct(String name, Double price, Integer quantity) {
        Connect connect = new Connect();
        String sql = "INSERT INTO Product (nameProduct, price, quantity) VALUES ('" + name + "','" + price + "','" + quantity + "')";
        connect.executeSQL(sql);
        JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
    }

    public void updateProduct(Integer id, String name, Double price, Integer quantity) {
        Connect connect = new Connect();
        String sql = "UPDATE Product SET nameProduct = '" + name + "', price = " + price + ", quantity = " + quantity + " WHERE id = " + id;
        connect.executeSQL(sql);
        JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
    }

    public void deleteProduct(Integer id) {
        Connect connect = new Connect();
        String sql = "DELETE FROM Product WHERE id = " + id;
        connect.executeSQL(sql);
        JOptionPane.showMessageDialog(null, "Deletado com Sucesso!");
    }

    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        Connect connect = new Connect();
        String sql = "SELECT * FROM Product";
        ResultSet resultSet = connect.executeQuery(sql);

        try {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("nameProduct"));
                product.setPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                productList.add(product);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar os produtos: " + e.getMessage());
        } finally {
            connect.disconnect();
        }

        return productList;
    }
}
