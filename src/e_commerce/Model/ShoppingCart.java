package e_commerce.Model;

import e_commerce.Controller.Connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class ShoppingCart {

    private List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }
    public void addToCart(int itemId) {
        Connect connect = new Connect();
        String sql = "SELECT * FROM Product WHERE id = " + itemId;
        ResultSet resultSet = connect.executeQuery(sql);

        try {
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nameProduct");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");

                Product item = new Product(id, name, price, quantity);
                items.add(item);

                out.println("Item adicionado ao carrinho:");
                out.println("#############################");
                out.println("ID: " + item.getId());
                out.println("Nome: " + item.getName());
                out.println("Preço: " + item.getPrice());
                out.println("Quantidade: " + item.getQuantity());
                out.println("#############################\n");
            } else {
                out.println("Nenhum item encontrado com a ID " + itemId + ".");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void viewCart() {
        if (items.isEmpty()) {
            out.println("A lista de compras está vazia.");
        } else {
            out.println("Lista de compras:");
            out.println("#############################\n");

            for (Product item : items) {
                out.println("#############################");
                out.println("ID: " + item.getId());
                out.println("Nome: " + item.getName());
                out.println("Preço: " + item.getPrice());
                out.println("Quantidade: " + item.getQuantity());
                out.println("#############################\n");
            }
        }
    }

}
