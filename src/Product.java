public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) { //constructor
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public boolean isExpired() { // default is false so we can change it in the expirable product subclass
        return false;
    }

    public boolean requiresShipping() { // default is false so we can change it in the shippable product subclass
        return false;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) { //reduces quanitiy after certain amount is purchased by customers
        quantity -= amount;
    }
}
