public class Customer {
    String name;
    double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deduct(double amount) { //reduces the amount spent on products from the customer's balance after purchase
        balance -= amount;
    }
}
