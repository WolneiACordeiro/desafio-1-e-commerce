package e_commerce.Model;

import java.util.List;

public class Order {
    private int id;
    Customer customer = new Customer();
    private List<Product> product;

    private boolean confirmed;
}
