package e_commerce.View;
import e_commerce.Model.Customer;
import e_commerce.Model.Product;

import java.util.Scanner;

import static java.lang.System.*;

public class Application {
    public static void main(String[] args) {
        int option;
        Customer customer = new Customer();
        Product product = new Product();
        do {
            Scanner scanner = new Scanner(in);

            out.println(" 1 - Cadastrar Cliente \n 2 - Cadastrar Produto \n 3 - Checar o Carrinho \n 4 - Fazer pedido \n 0 - Sair");
            out.print("Selecione uma opção: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    out.println("#############| Cadastrar Cliente Selecionado |#############");
                    out.print("Insira um nome: ");
                    var name_customer = scanner.nextLine();
                    out.print("Insira um e-mail: ");
                    var email_customer = scanner.nextLine();
                    customer.registerCustomer(name_customer, email_customer);
                    break;
                case 2:
                    out.println("#############| Cadastrar Produto Selecionado |#############");
                    out.print("Insira um nome: ");
                    var name_product = scanner.nextLine();
                    out.print("Insira um preço: ");
                    var price_product = scanner.nextLine();
                    out.print("Insira uma quantidade: ");
                    var quantity_product = scanner.nextLine();
                    product.registerProduct(name_product, Double.parseDouble(price_product), Integer.parseInt(quantity_product));
                    break;
                case 3:
                    out.println("Opção 3 selecionada");
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
