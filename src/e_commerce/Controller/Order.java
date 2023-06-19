package e_commerce.Controller;

import e_commerce.Model.Connect;

import javax.swing.*;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;


public class Order {

    ShoppingCart shoppingCart = new ShoppingCart();
    int option;

    public void finishOrder(ShoppingCart shoppingCart) {
        if (shoppingCart.getItems().isEmpty()) {
            out.println("#############################");
            out.println("NÃO É POSSÍVEL FINALIZAR UM PEDIDO COM O CARRINHO VAZIO.");
        } else {
            do {
                Scanner scanner = new Scanner(in);
                out.println("#############| DESEJA FINALIZAR O SEU PEDIDO? |#############");
                out.println(" 1 - SIM | 2 - NÃO");
                out.println("Selecione uma opção: ");
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1 -> {
                        out.println("#############################");
                        out.println("Lista de compras:");
                        double totalCartPrice = 0.00;
                        for (Product item : shoppingCart.getItems()) {
                            out.println("#############################");
                            out.println("ID: " + item.getId());
                            out.println("Nome: " + item.getName());
                            out.println("Preço: R$" + item.getPrice());
                            out.println("Quantidade: " + item.getQuantity());
                            totalCartPrice += item.getQuantity() * item.getPrice();
                            updateProductQuantity(item.getId(), item.getQuantity());
                        }
                        out.println("#############################");
                        out.println("TOTAL PAGO: R$" + totalCartPrice);
                        out.println("#############################");
                        out.println("PEDIDO REALIZADO COM SUCESSO");
                        this.clearCart(shoppingCart);
                    }
                    case 2 -> out.println("Retornar");
                    default -> out.println("Opção inválida");
                }
            } while ((option != 2) && (option != 1));

        }
    }

    public void clearCart(ShoppingCart shoppingCart) {
        shoppingCart.getItems().clear();
    }

    public void updateProductQuantity(Integer id, Integer quantity) {
        Connect connect = new Connect();
        String sql = "UPDATE Product SET quantity = quantity - " + quantity + " WHERE id = " + id;
        connect.executeSQL(sql);
    }

}