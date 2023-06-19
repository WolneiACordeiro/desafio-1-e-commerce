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
            out.println(" 1 - Adicionar ao Carrinho \n 2 - Remover do Carrinho \n 3 - Visualizar seu Carrinho \n 4 - Finalizar Pedido \n 5 - Lista de Produtos \n 6 - Procurar Produto  \n 7 - Gerenciar Produtos \n 0 - Sair");
            out.println("SELECIONE UMA OPÇÃO: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1 -> {
                        out.println("\n#############| Adicionar ao Carrinho - Selecionado |#############");
                        shoppingCart.selectProductToCart();
                    }
                    case 2 -> {
                        out.println("\n#############| Remover do Carrinho - Selecionado |#############");
                        shoppingCart.removeProductFromCart();
                    }
                    case 3 -> {
                        out.println("\n#############| Visualizar seu Carrinho - Selecionado |#############");
                        shoppingCart.viewCart();
                    }
                    case 4 -> {
                        out.println("\n#############| Finalizar Pedido - Selecionado |#############");
                        order.finishOrder(shoppingCart);
                    }
                    case 5 -> {
                        out.println("\n#############| Lista de Produtos - Selecionado |#############");
                        product.showProducts();
                    }
                    case 6 -> {
                        out.println("\n#############| Procurar Produto - Selecionado |#############");
                        product.findProduct();
                    }
                    case 7 -> {
                        out.println("\n#############| Gerenciar Produtos - Selecionado |#############");
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