package e_commerce.Controller;

import e_commerce.Model.Connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class ShoppingCart {

    private List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public boolean itemCartExist(int itemId) {
        for (Product item : items) {
            if (item.getId() == itemId) {
                return true;
            }
        }
        return false;
    }

    public void selectProductToCart() {
        Product product = new Product();
        Scanner scanner = new Scanner(in);
        out.print("Digite o código do produto que deseja adicionar ao carrinho: ");
        var itemId = scanner.nextLine();
            if (product.productExists(Integer.parseInt(itemId))) {
                Connect connect = new Connect();
                String sql = "SELECT * FROM Product WHERE id = " + itemId;
                ResultSet resultSet = connect.executeQuery(sql);
                if(this.itemCartExist(Integer.parseInt(itemId))){
                    out.println("O produto já está na lista!");
                }else{
                    out.println("O produto não está na lista!");
                }
                try {
                    if (resultSet.next()) {
                    int stockQuantity = resultSet.getInt("quantity");
                    String stockName = resultSet.getString("nameProduct");
                    double stockPrice = resultSet.getDouble("price");
                    out.print("Insira a quantidade que deseja comprar de:\n" + stockName + " | Preço(UN) R$" + stockPrice + "| Em estoque " + stockQuantity + "(UN)\n");
                        try {
                            var itemQuantity = scanner.nextLine();

                            if (Integer.parseInt(itemQuantity) > stockQuantity) {
                                out.print("Desculpe o estoque disponível é de apenas " + stockQuantity + " Unidades para esse produto!\n");
                            } else if (Integer.parseInt(itemQuantity) > 0) {
                                this.addToCart(Integer.parseInt(itemId), Integer.parseInt(itemQuantity));
                            } else if (Integer.parseInt(itemQuantity) <= 0) {
                                out.print("Nenhum item foi adicionado ao carrinho (Digite uma quantidade numérica que seja maior que '0')");
                            }
                        } catch (NumberFormatException e) {
                                out.print("A quantidade digitada não é um número válido.");
                            }
                    }
                } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
            } else {
                    out.println("Nenhum item encontrado com o Código " + itemId + ".");
                }
    }
    public void addToCart(int itemId, int itemQuantity) {
        Connect connect = new Connect();
        String sql = "SELECT * FROM Product WHERE id = " + itemId;
        ResultSet resultSet = connect.executeQuery(sql);

        try {
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nameProduct");
                double price = resultSet.getDouble("price");
                int quantity = itemQuantity;

                Product item = new Product(id, name, price, quantity);
                items.add(item);
                out.println("#############################");
                out.println("Item adicionado ao carrinho:");
                out.println("ID: " + item.getId());
                out.println("Nome: " + item.getName());
                out.println("Preço(UN): R$" + item.getPrice());
                out.println("Quantidade: " + item.getQuantity());
                out.println("#############################");
            } else {
                out.println("Nenhum item encontrado com o Código " + itemId + ".");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateItem(int itemId, int newQuantity) {
        for (Product item : items) {
            if (item.getId() == itemId) {
                item.setQuantity(newQuantity);
                out.println("Item atualizado:");
                out.println("ID: " + item.getId());
                out.println("Nome: " + item.getName());
                out.println("Preço: " + item.getPrice());
                out.println("Quantidade: " + item.getQuantity());
                return;
            }
        }
        out.println("Nenhum item encontrado no carrinho com o Código " + itemId + ".");
    }


    public void viewCart() {
        if (items.isEmpty()) {
            out.println("A lista de compras está vazia.");
        } else {
            out.println("#############################");
            out.println("Lista de compras:");

            for (Product item : items) {
                out.println("#############################");
                out.println("ID: " + item.getId());
                out.println("Nome: " + item.getName());
                out.println("Preço: " + item.getPrice());
                out.println("Quantidade: " + item.getQuantity());
            }
        }
    }

}
