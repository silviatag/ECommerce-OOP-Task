import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<CartItem> items = new ArrayList<>(); // array that will hold the cart items

    public void add(Product product, int quantity) {
        if (quantity > product.getQuantity()) { // checks if the the quantity of the product wanted by the customer is more than that in stock
            throw new IllegalArgumentException("Requested quantity exceeds available stock.");
        }
        items.add(new CartItem(product, quantity)); // if the quantity is in stock, the item is added to cart
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
