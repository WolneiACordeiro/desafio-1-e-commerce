package e_commerce.View;
import e_commerce.Controller.Product;
import e_commerce.Controller.ShoppingCart;

import java.util.Scanner;

import static java.lang.System.*;

public class Application {
    public static void main(String[] args) {
        int option;
        Product product = new Product();
        ShoppingCart shoppingCart = new ShoppingCart();
        do {
            Scanner scanner = new Scanner(in);
            out.println("#############| MENU |#############");
            out.println(" 1 - Adicionar ao Carrinho \n 2 - Visualizar Carrinho \n 3 - Lista de Produtos \n 4 - Procurar Produto  \n 5 - Gerenciar Produtos \n 0 - Sair");
            out.println("Selecione uma opção: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> {
                    out.println("#############| Adicionar ao Carrinho - Selecionado |#############");
                    shoppingCart.selectProductToCart();
                }
                case 2 -> {
                    out.println("#############| Visualizar Carrinho - Selecionado |#############");
                    shoppingCart.viewCart();
                }
                case 3 -> {
                    out.println("#############| Lista de Produtos - Selecionado |#############");
                    product.showProducts();
                }
                case 4 -> {
                    out.println("#############| Procurar Produto - Selecionado |#############");
                    product.findProduct();
                }
                case 5 -> {
                    out.println("#############| Gerenciar Produtos - Selecionado |#############");
                    product.menuProduct();
                }
                case 0 -> out.println("Sair");
                default -> out.println("Opção inválida");
            }
        } while (option != 0);
    }
}
