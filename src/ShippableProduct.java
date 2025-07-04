public class ShippableProduct extends Product implements Shippable {
    private double weight;

    public ShippableProduct(String name, double price, int quantity, double weight) { //constructor
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
