package e_commerce.View;
import e_commerce.Model.Customer;
import e_commerce.Model.Product;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Application {
    public static void main(String[] args) {
        int option;
        Product product = new Product();
        do {
            Scanner scanner = new Scanner(in);

            out.println(" 1 - Fazer Pedido \n 2 - Gerenciar Produtos \n 3 - Lista de Produtos \n 4 - Finalizar Pedido \n 0 - Sair");
            out.print("Selecione uma opção: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    out.println("#############| Fazer Pedido Selecionado |#############");
                    break;
                case 2:
                    out.println("#############| Gerenciar Produtos Selecionado |#############");
                    product.menuProduct();
                    break;
                case 3:
                    out.println("#############| Visualizar Produto Selecionado |#############");
                    List<Product> productList = product.getProducts();

                    for (Product p : productList) {
                        out.println("ID: " + p.getId());
                        out.println("Nome: " + p.getName());
                        out.println("Preço: " + p.getPrice());
                        out.println("Quantidade: " + p.getQuantity());
                        out.println("--------------------------");
                    }
                    break;
                case 4:
                    out.println("Opção 3 selecionada");
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
