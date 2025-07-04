import java.util.*;

public class ShippingService {
    public static void ship(List<Shippable> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        Map<String, Integer> grouped = new HashMap<>();
        Map<String, Double> weights = new HashMap<>();

        for (Shippable item : items) {
            grouped.put(item.getName(), grouped.getOrDefault(item.getName(), 0) + 1);
            weights.put(item.getName(), item.getWeight());
            totalWeight += item.getWeight();
        }

        for (String name : grouped.keySet()) {
            System.out.println(grouped.get(name) + "x " + name + " " + (weights.get(name) * 1000) + "g");
        }

        System.out.println("Total package weight " + totalWeight + "kg");
    }
}
