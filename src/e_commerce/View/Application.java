package e_commerce.View;
import e_commerce.Model.Product;
import e_commerce.Model.ShoppingCart;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Application {
    public static void main(String[] args) {
        int option;
        Product product = new Product();
        ShoppingCart shoppingCart = new ShoppingCart();
        do {
            Scanner scanner = new Scanner(in);

            out.println(" 1 - Fazer Pedido \n 2 - Gerenciar Produtos \n 3 - Lista de Produtos \n 4 - Procurar Produto  \n 5 - Finalizar Pedido \n 0 - Sair");
            out.print("Selecione uma opção: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    out.println("#############| Fazer Pedido - Selecionado |#############");
                    out.print("Insira o código do produto: ");
                    var id = scanner.nextLine();
                    shoppingCart.addToCart(Integer.parseInt(id));
                    break;
                case 2:
                    out.println("#############| Gerenciar Produtos - Selecionado |#############");
                    product.menuProduct();
                    break;
                case 3:
                    out.println("#############| Visualizar os Produtos - Selecionado |#############");
                    product.showProducts();
                    break;
                case 4:
                    out.println("#############| Procurar Produto - Selecionado |#############");
                    product.findProduct();
                    break;
                case 5:
                    out.println("Opção 3 selecionada");
                    shoppingCart.viewCart();
                    break;
                case 0:
                    out.println("Sair");
                    break;
                default:
                    out.println("Opção inválida");
                    break;
            }
        } while (option != 0);
    }
}
