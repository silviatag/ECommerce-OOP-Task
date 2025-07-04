import java.util.Date;

public class ExpirableProduct extends Product {
    private Date expiryDate;

    public ExpirableProduct(String name, double price, int quantity, Date expiryDate) { //constructor
        super(name, price, quantity); // uses super class "product"
        this.expiryDate = expiryDate; // adds expiry date
    }

    @Override
    public boolean isExpired() { // shows product is expireed after the expiry date
        return new Date().after(expiryDate);
    }
}
