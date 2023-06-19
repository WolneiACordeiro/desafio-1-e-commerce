package e_commerce.Controller;

import e_commerce.Model.Connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.PreparedStatement;

import static java.lang.System.in;
import static java.lang.System.out;

public class Product {
    private Integer id;
    private String name;
    private double price;
    private Integer quantity;

    public Product() {
    }

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    Scanner scanner = new Scanner(in);
    Connect connect = new Connect();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void menuProduct () {
        int option;
        do {
            out.println(" 1 - Cadastrar \n 2 - Alterar \n 3 - Remover \n 0 - Voltar");
            out.print("Selecione uma opção: ");
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1 -> {
                    out.println("#############| Cadastrar Produto Selecionado |#############");
                    this.createProduct();
                }
                case 2 -> {
                    out.println("#############| Alterar Produto Selecionado |#############");
                    this.alterProduct();
                }
                case 3 -> {
                    out.println("#############| Remover Produto Selecionado |#############");
                    this.removeProduct();
                }
                case 0 -> out.println("Voltar");
                default -> out.println("Opção inválida!");
            }
        } while (option != 0);
    }

    public boolean productExists(int id) {
        String sql = "SELECT COUNT(id) FROM Product WHERE id =" + id;
        ResultSet resultSet = connect.executeQuery(sql);
        boolean check = false;

        try {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count != 0) {
                    check = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return check;
    }
    public void createProduct() {

        boolean validName = false;
        String nameProduct = "";
        while (!validName) {
            out.print("Insira o nome do produto: ");
            try {
                nameProduct = scanner.nextLine();
                if (nameProduct.trim().isEmpty()) {
                    throw new IllegalArgumentException("O nome do produto não pode estar vazio. Por favor insira um nome válido. Exemplo(Mamão)");
                } else {
                    validName = true;
                }
            } catch (IllegalArgumentException ex) {
                out.println(ex.getMessage());
            }
        }

        boolean validPrice = false;
        double priceProduct = 0.00;
        while (!validPrice) {
            out.print("Insira o preço do produto: ");
            try {
                priceProduct = Double.parseDouble(scanner.nextLine());
                if (priceProduct < 0) {
                    out.println("O preço não pode ser menor que zero. Por favor insira um valor válido.");
                } else {
                    validPrice = true;
                }
            } catch (NumberFormatException e) {
                out.println("Preço inválido. Insira um valor numérico válido. Exemplo(2.00)");
            }
        }

        boolean validQuantity = false;
        int quantityProduct = 0;
        while (!validQuantity) {
            out.print("Insira a quantidade do produto: ");
            try {
                quantityProduct = Integer.parseInt(scanner.nextLine());
                if (quantityProduct < 0) {
                    out.println("A quantidade não pode ser menor que zero. Por favor insira um valor válido.");
                } else {
                    validQuantity = true;
                }
            } catch (NumberFormatException e) {
                out.println("Quantidade inválida. Insira uma quantidade de valor numérico inteiro. Exemplo(10)");
            }
        }

            int option = -1;

            out.println("#############| CONFIRMAR REGISTRO DE PRODUTO? |#############");
            out.println("#############################");
            out.println("Nome: " + nameProduct);
            out.println("Preço: " + priceProduct);
            out.println("Quantidade: " + quantityProduct);
            out.println("#############################");

            String finalNameProduct = nameProduct;
            double finalPriceProduct = priceProduct;
            int finalQuantityProduct = quantityProduct;

        confirmAction(option, () -> registerProduct(finalNameProduct, finalPriceProduct, finalQuantityProduct));

    }

    public void alterProduct() {
        out.print("Insira o código do produto: ");
        var itemId = scanner.nextLine();
        if (productExists(Integer.parseInt(itemId))) {
            String sql = "SELECT * FROM Product WHERE id = " + itemId;
            ResultSet resultSet = connect.executeQuery(sql);

            try{
                if (resultSet.next()) {
                    int productQuantity = resultSet.getInt("quantity");
                    String productName = resultSet.getString("nameProduct");
                    double productPrice = resultSet.getDouble("price");

                    boolean validName = false;
                    String nameProduct = "";
                    while (!validName) {
                        out.print("Insira o novo nome do produto [Nome Atual - " + productName +"]: ");
                        try {
                            nameProduct = scanner.nextLine();
                            if (nameProduct.trim().isEmpty()) {
                                throw new IllegalArgumentException("O nome do produto não pode estar vazio. Por favor insira um nome válido. Exemplo(Mamão)");
                            } else {
                                validName = true;
                            }
                        } catch (IllegalArgumentException ex) {
                            out.println(ex.getMessage());
                        }
                    }

                    boolean validPrice = false;
                    double priceProduct = productPrice;
                    while (!validPrice) {
                        out.print("Insira o novo preço do produto [Preço Atual - " + productPrice + "]: ");
                        try {
                            priceProduct = Double.parseDouble(scanner.nextLine());
                            if (priceProduct < 0) {
                                out.println("O preço não pode ser menor que zero. Por favor insira um valor válido.");
                            } else {
                                validPrice = true;
                            }
                        } catch (NumberFormatException e) {
                            out.println("Preço inválido. Insira um valor numérico válido. Exemplo(2.00)");
                        }
                    }

                    boolean validQuantity = false;
                    int quantityProduct = productQuantity;
                    while (!validQuantity) {
                        out.print("Insira a nova quantidade do produto [Quantidade Atual - " + productQuantity + "]: ");
                        try {
                            quantityProduct = Integer.parseInt(scanner.nextLine());
                            if (quantityProduct < 0) {
                                out.println("A quantidade não pode ser menor que zero. Por favor insira um valor válido.");
                            } else {
                                validQuantity = true;
                            }
                        } catch (NumberFormatException e) {
                            out.println("Quantidade inválida. Insira uma quantidade de valor numérico inteiro. Exemplo(10)");
                        }
                    }

                        int option = -1;

                        out.println("#############| CONFIRMAR ATUALIZAÇÃO DE PRODUTO? |#############");
                        out.println("#############################");
                        out.println("Nome: " + nameProduct);
                        out.println("Preço: " + priceProduct);
                        out.println("Quantidade: " + quantityProduct);
                        out.println("#############################");

                        String finalNameProduct = nameProduct;
                        double finalPriceProduct = priceProduct;
                        int finalQuantityProduct = quantityProduct;
                        confirmAction(option, () -> this.updateProduct(Integer.valueOf(itemId), finalNameProduct, finalPriceProduct, finalQuantityProduct));

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            out.println("Produto não encontrado.");
        }
    }

    public void removeProduct() {
        out.print("Insira o código do produto a ser removido: ");
        try {
            var itemId = scanner.nextLine();
            if (productExists(Integer.parseInt(itemId))) {
                String sql = "SELECT * FROM Product WHERE id = " + itemId;
                ResultSet resultSet = connect.executeQuery(sql);

                try{
                    if (resultSet.next()) {
                        int productQuantity = resultSet.getInt("quantity");
                        String productName = resultSet.getString("nameProduct");
                        double productPrice = resultSet.getDouble("price");

                        int option = -1;

                        out.println("#############| CONFIRMAR REMOÇÃO DE PRODUTO? |#############");
                        out.println("#############################");
                        out.println("Nome: " + productName);
                        out.println("Preço: " + productPrice);
                        out.println("Quantidade: " + productQuantity);
                        out.println("#############################");

                        confirmAction(option, () -> this.deleteProduct(Integer.valueOf(itemId)));
                    }
                } catch (SQLException ex) {
                        ex.printStackTrace();
                }

            } else {
                out.println("Produto não encontrado.");
            }
        } catch (NumberFormatException e) {
            out.println("Código inválido. O código deve ser de valor numérico inteiro. Exemplo(1)");
        }
    }

    public void showProducts() {
        List<Product> productList = this.getProducts();

        for (Product p : productList) {
            out.println("#############################");
            out.println("ID: " + p.getId());
            out.println("Nome: " + p.getName());
            out.println("Preço: " + p.getPrice());
            out.println("Quantidade: " + p.getQuantity());
            out.println("#############################");
        }
    }

    public void findProduct() {
        out.print("Digite o nome do produto que quer encontrar: ");
        var productName = scanner.nextLine();
        this.showProductsByName(productName);
    }

    public void registerProduct(String name, Double price, Integer quantity) {
        String sql = "INSERT INTO Product (nameProduct, price, quantity) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, quantity);

            preparedStatement.executeUpdate();
            System.out.println("PRODUTO CADASTRADO COM SUCESSO!");
            out.println("#############################");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERRO AO CADASTRAR O PRODUTO: " + ex.getMessage());
            out.println("#############################");
        } finally {
            connect.disconnect();
        }
    }

    public void updateProduct(Integer id, String name, Double price, Integer quantity) {
        String sql = "UPDATE Product SET nameProduct = ?, price = ?, quantity = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
            System.out.println("PRODUTO ATUALIZADO COM SUCESSO!");
            out.println("#############################");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERRO AO ATUALIZAR O PRODUTO: " + ex.getMessage());
            out.println("#############################");
        } finally {
            connect.disconnect();
        }
    }


    public void deleteProduct(Integer id) {
        String sql = "DELETE FROM Product WHERE id = " + id;
        connect.executeSQL(sql);
        out.println("PRODUTO DELETADO COM SUCESSO!");
        out.println("#############################");
    }

    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        ResultSet resultSet = connect.executeQuery(sql);

        try {
            while (resultSet.next()) {
                int productId = resultSet.getInt("id");
                String productName = resultSet.getString("nameProduct");
                double productPrice = resultSet.getDouble("price");
                int productQuantity = resultSet.getInt("quantity");

                Product product = new Product();
                product.setId(productId);
                product.setName(productName);
                product.setPrice(productPrice);
                product.setQuantity(productQuantity);

                productList.add(product);
            }

            if (productList.isEmpty()) {
                out.println("Nenhum produto cadastrado.");
            }

        } catch (SQLException e) {
            out.println("ERRO AO RETORNAR OS PRODUTOS: " + e.getMessage());
        } finally {
            connect.disconnect();
        }

        return productList;
    }


    public void showProductsByName(String productName) {
        List<Product> productList = this.getProducts();
        boolean productFound = false;

        for (Product p : productList) {
            if (p.getName().toLowerCase().contains(productName.toLowerCase())) {
                out.println("#############################");
                out.println("ID: " + p.getId());
                out.println("Nome: " + p.getName());
                out.println("Preço: " + p.getPrice());
                out.println("Quantidade: " + p.getQuantity());
                out.println("#############################");
                productFound = true;
            }
        }

        if (!productFound) {
            out.println("Nenhum produto encontrado com o nome contendo '" + productName + "'.");
        }
    }

    public void confirmAction(int option, Runnable method) {
        do {
            boolean validOption = false;
            while (!validOption) {
                out.println("SELECIONE UMA OPÇÃO: (1 - SIM) e OPÇÃO (2 - NÃO)");
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    if ((option != 1) && (option != 2)) {
                        out.println("Escolha apenas entre a OPÇÃO (1 - SIM) e OPÇÃO (2 - NÃO)");
                    } else {
                        validOption = true;
                    }
                } catch (NumberFormatException e) {
                    out.println("A únicas opções são os valores numéricos inteiros: OPÇÃO (1 - SIM) e OPÇÃO (2 - NÃO)");
                }
            }
            switch (option) {
                case 1:
                    method.run();
                    break;
                case 2:
                    out.println("RETORNAR");
                    out.println("#############################");
                    break;
                default:
                    out.println("Opção inválida");
                    break;
            }
        } while ((option != 2) && (option != 1));
    }

}
