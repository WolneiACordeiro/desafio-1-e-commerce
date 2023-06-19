package e_commerce.View;
import e_commerce.Controller.Order;
import e_commerce.Controller.Product;
import e_commerce.Controller.ShoppingCart;

import java.util.Scanner;

import static java.lang.System.*;

public class Application {
    public static void main(String[] args) {
        Product product = new Product();
        ShoppingCart shoppingCart = new ShoppingCart();
        Order order = new Order();

        int option;

        out.println(System.getProperty("java.version"));

        do {

            Scanner scanner = new Scanner(in);
            out.println("\n#############| MENU |#############");
            out.println(" 1 - Adicionar ao Carrinho \n 2 - Remover do Carrinho \n 3 - Visualizar Carrinho \n 4 - Finalizar Pedido \n 5 - Lista de Produtos \n 6 - Procurar Produto  \n 7 - Gerenciar Produtos \n 0 - Sair");
            out.println("SELECIONE UMA OPÇÃO: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1 -> {
                        out.println("#############| Adicionar ao Carrinho - Selecionado |#############");
                        shoppingCart.selectProductToCart();
                    }
                    case 2 -> {
                        out.println("#############| Remover do Carrinho - Selecionado |#############");
                        shoppingCart.removeProductFromCart();
                    }
                    case 3 -> {
                        out.println("#############| Visualizar Carrinho - Selecionado |#############");
                        shoppingCart.viewCart();
                    }
                    case 4 -> {
                        out.println("#############| Finalizar Pedido - Selecionado |#############");
                        order.finishOrder(shoppingCart);
                    }
                    case 5 -> {
                        out.println("#############| Lista de Produtos - Selecionado |#############");
                        product.showProducts();
                    }
                    case 6 -> {
                        out.println("#############| Procurar Produto - Selecionado |#############");
                        product.findProduct();
                    }
                    case 7 -> {
                        out.println("#############| Gerenciar Produtos - Selecionado |#############");
                        product.menuProduct();
                    }
                    case 0 -> out.println("SAIR");
                    default -> out.println("Opção inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas entradas do tipo numérico inteiro são válidas.");
                option = -1;
            }
        } while (option != 0);
    }
}