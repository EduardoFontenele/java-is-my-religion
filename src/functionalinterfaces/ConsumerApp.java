package functionalinterfaces;

import functionalinterfaces.models.Product;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerApp {
    public static void main(String[] args) {
        var p1 = new Product("Xbox 360", 1100d);
        var p2 = new Product("PS3", 1300D);
        var p3 = new Product("Nintendo Switch", 1200D);
        var products = List.of(p1, p2, p3);

        Consumer<Product> printInfo = System.out::println;
        products.forEach(printInfo);
    }
}
