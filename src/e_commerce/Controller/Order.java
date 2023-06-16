package e_commerce.Controller;

import e_commerce.Model.Connect;

import static java.lang.System.out;


public class Order {

    ShoppingCart shoppingCart = new ShoppingCart();

    public void finishOrder(ShoppingCart shoppingCart) {
        if (shoppingCart.getItems().isEmpty()) {
            out.println("#############################");
            out.println("A lista de compras está vazia.");
        } else {
            out.println("#############################");
            out.println("Lista de compras:");
            double totalCartPrice = 0.00;
            for (Product item : shoppingCart.getItems()) {
                out.println("#############################");
                out.println("ID: " + item.getId());
                out.println("Nome: " + item.getName());
                out.println("Preço: " + item.getPrice());
                out.println("Quantidade: " + item.getQuantity());
                totalCartPrice += item.getQuantity() * item.getPrice();
            }
            out.println("#############################");
            out.println("TOTAL DO CARRINHO: R$" + totalCartPrice);
            out.println("#############################");
            out.println("PEDIDO FINALIZADO");
            this.clearCart(shoppingCart);
        }
    }

    public void clearCart(ShoppingCart shoppingCart) {
        shoppingCart.getItems().clear();
    }

}