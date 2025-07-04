import java.util.*;

public class ECommerceSystem {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty"); //cant check out if cart is empty

        double subtotal = 0;
        double shipping = 0;
        List<Shippable> shippables = new ArrayList<>();

        for (CartItem item : cart.getItems()) { //loops over items in the cart
            Product product = item.product;
            if (product.isExpired()) throw new IllegalStateException(product.getName() + " is expired"); //handles if the product expiration date has passed
            if (item.quantity > product.getQuantity()) throw new IllegalStateException(product.getName() + " is out of stock"); //handles if product is out of stock

            subtotal += product.getPrice() * item.quantity; //total prices of all items in the cart
            product.reduceQuantity(item.quantity); //reduces quantity of product in inventory

            if (product.requiresShipping() && product instanceof Shippable) {
                for (int i = 0; i < item.quantity; i++) shippables.add((Shippable) product); //adds shippable items
            }
        }

        shipping = shippables.stream().mapToDouble(Shippable::getWeight).sum() * 10; // 10 pounds per kilo
        double total = subtotal + shipping;

        if (customer.getBalance() < total) throw new IllegalStateException("Insufficient balance"); // if the price is more than the customer's balance

        if (!shippables.isEmpty()) ShippingService.ship(shippables);

        customer.deduct(total); //reduces the price from customers balance

        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.quantity + "x " + item.product.getName() + " " + (item.product.getPrice() * item.quantity));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shipping);
        System.out.println("Amount " + total);
        System.out.println("Remaining balance " + customer.getBalance());
    }

    public static void main(String[] args) {

        System.out.println("----------case 1------------");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 5);
        Date futureDate = calendar.getTime();

        Product cheese = new ExpirableShippableProduct("Cheese", 100, 5, futureDate, 0.2);
        Product biscuits = new ExpirableShippableProduct("Biscuits", 150, 3, futureDate, 0.7);
        Product tv = new ShippableProduct("TV", 300, 3, 8);
        Product scratchCard = new Product("Scratch Card", 50, 10) {
        };

        Customer customer = new Customer("Silvia", 1000);
        Cart cart = new Cart();

        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);
        cart.add(tv, 1);

        checkout(customer, cart);

        System.out.println("----------case 2------------");

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DATE, 5);
        Date futureDate2 = calendar.getTime();

        Product Milk = new ExpirableShippableProduct("Milk", 30, 1, futureDate2, 0.2);
        Product Chips = new ExpirableShippableProduct("Chips", 150, 3, futureDate2, 0.7);
        Product Blender = new ShippableProduct("Blender", 2000, 3, 8);


        Customer customer2 = new Customer("Mohamed", 1000);
        Cart cart2 = new Cart();

        cart2.add(Milk, 2);
        cart2.add(Chips, 1);
        cart2.add(Blender, 1);

        checkout(customer2, cart2); // should throw an error because milk is out of stock

        System.out.println("----------case 3------------");  // to see the results of this case please comment out "case 2" since its made to handle an error

        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(Calendar.DATE, 5);
        Date futureDate3 = calendar.getTime();

        Product Coffee = new ExpirableShippableProduct("Coffee", 30, 5, futureDate3, 0.2);
        Product Cake = new ExpirableShippableProduct("Cake", 150, 3, futureDate3, 0.7);
        Product CoffeeMaker = new ShippableProduct("Coffee Maker", 4000, 3, 8);


        Customer customer3 = new Customer("Mohamed", 1000);
        Cart cart3 = new Cart();

        cart3.add(Coffee, 1);
        cart3.add(Cake, 1);
        cart3.add(CoffeeMaker, 1);

        checkout(customer3, cart3); // should throw an error because the customer's balance doesnt cover the total price
    }
}
