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

    public List<Product> getItems() {
        return items;
    }

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    Scanner scanner = new Scanner(in);

    Utils utils = new Utils();

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
        out.print("Digite o código do produto que deseja adicionar ao carrinho: ");
        var itemId = scanner.nextLine();
            if (product.productExists(Integer.parseInt(itemId))) {
                Connect connect = new Connect();
                String sql = "SELECT * FROM Product WHERE id = " + itemId;
                ResultSet resultSet = connect.executeQuery(sql);

                if(this.itemCartExist(Integer.parseInt(itemId))){
                    try {
                        if (resultSet.next()) {
                            boolean itemExistenceInCart = true;
                            int stockQuantity = resultSet.getInt("quantity");
                            String stockName = resultSet.getString("nameProduct");
                            double stockPrice = resultSet.getDouble("price");
                            int currentItemQuantity = getItemQuantity(Integer.parseInt(itemId));
                            out.print("ESTE ITEM JÁ SE ENCONTRA NO SEU CARRINHO\nInsira quantas novas unidades deseja comprar (1) | Ou quantas deseja remover (-1):\n" + stockName + " | Preço(UN) R$" + stockPrice + "| Em estoque " + stockQuantity + "(UN) | No Carrinho " + currentItemQuantity + "(UN)\n");
                            this.checkQuantityOrder(Integer.parseInt(itemId), stockQuantity, itemExistenceInCart);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    try {
                        if (resultSet.next()) {
                            boolean itemExistenceInCart = false;
                            int stockQuantity = resultSet.getInt("quantity");
                            String stockName = resultSet.getString("nameProduct");
                            double stockPrice = resultSet.getDouble("price");
                            out.print("Insira a quantidade que deseja comprar de:\n" + stockName + " | Preço(UN) R$" + stockPrice + "| Em estoque " + stockQuantity + "(UN)\n");
                            this.checkQuantityOrder(Integer.parseInt(itemId), stockQuantity, itemExistenceInCart);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                    out.println("Nenhum item encontrado com o Código " + itemId + ".");
                }
    }
    public int getItemQuantity(int itemId) {
        for (Product item : items) {
            if (item.getId() == itemId) {
                return item.getQuantity();
            }
        }
        return 0;
    }
    public void checkQuantityOrder (int itemId, int stockQuantity, boolean itemExistenceInCart){
        try {
            int itemQuantity = Integer.parseInt(scanner.nextLine());
            if (itemExistenceInCart) {
                itemQuantity += getItemQuantity(itemId);
            }
            if (itemQuantity > stockQuantity) {
                out.print("Desculpe o estoque disponível é de apenas " + stockQuantity + " Unidades para esse produto!\n");
            } else if ((itemQuantity > 0) && (!itemExistenceInCart)) {
                this.addToCart(itemId, itemQuantity);
            } else if (itemQuantity > 0) {
                this.updateItem(itemId, itemQuantity);
            } else {
                out.print("A quantidade desse produto no carrinho precisa ser maior que '0')\n");
            }
        } catch (NumberFormatException e) {
            out.print("Por favor insira apenas quantidades numéricas.\n");
        }
    }

    public void removeProductFromCart() {
        if (this.getItems().isEmpty()) {
            out.println("CARRINHO VAZIO!");
        } else {
            out.print("Digite o código do produto que deseja remover do carrinho: ");
            var itemId = scanner.nextLine();
            if(this.itemCartExist(Integer.parseInt(itemId))) {
                Connect connect = new Connect();
                String sql = "SELECT * FROM Product WHERE id = " + itemId;
                ResultSet resultSet = connect.executeQuery(sql);
                try{
                    if (resultSet.next()) {
                        int currentItemQuantity = getItemQuantity(Integer.parseInt(itemId));
                        String productName = resultSet.getString("nameProduct");
                        double productPrice = resultSet.getDouble("price");

                        int option = -1;

                        out.println("#############| REMOVER PRODUTO DO CARRINHO? |#############");
                        out.println("#############################");
                        out.println("Nome: " + productName);
                        out.println("Preço: R$" + productPrice);
                        out.println("Quantidade: " + currentItemQuantity);
                        out.println("#############################");

                        utils.confirmAction(option, () -> this.removeFromCart(Integer.valueOf(itemId)));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                out.println("Nenhum item encontrado com o Código " + itemId + ".");
            }
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

                Product item = new Product(id, name, price, itemQuantity);
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
                out.println("#############################");
                out.println("Item atualizado:");
                out.println("ID: " + item.getId());
                out.println("Nome: " + item.getName());
                out.println("Preço: R$" + item.getPrice());
                out.println("Quantidade: " + item.getQuantity());
                return;
            }
        }
        out.println("Nenhum item encontrado no carrinho com o Código " + itemId + ".");
    }

    public void removeFromCart(int itemId) {
        for (int i = 0; i < items.size(); i++) {
            Product item = items.get(i);
            if (item.getId() == itemId) {
                items.remove(i);
                out.println("#############################");
                out.println("Item removido do carrinho:");
                out.println("ID: " + item.getId());
                out.println("Nome: " + item.getName());
                out.println("Preço(UN): R$" + item.getPrice());
                out.println("Quantidade: " + item.getQuantity());
                out.println("#############################");
                return;
            }
        }
        out.println("Nenhum item encontrado com o ID " + itemId + " no carrinho.");
    }

    public void viewCart() {
        if (items.isEmpty()) {
            out.println("#############################");
            out.println("O carrinho está VAZIO.");
        } else {
            out.println("#############################");
            out.println("Lista de compras:");
            double totalCartPrice = 0.00;
            for (Product item : items) {
                out.println("#############################");
                out.println("ID: " + item.getId());
                out.println("Nome: " + item.getName());
                out.println("Preço: R$" + item.getPrice());
                out.println("Quantidade: " + item.getQuantity());
                totalCartPrice += item.getQuantity() * item.getPrice();
            }
            out.println("#############################");
            out.println("TOTAL DO CARRINHO: R$" + totalCartPrice);
        }
    }

}
