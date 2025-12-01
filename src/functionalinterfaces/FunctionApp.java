package functionalinterfaces;

import functionalinterfaces.models.Product;

import java.util.List;
import java.util.function.Function;

public class FunctionApp {
    public static void main(String[] args) {
        Function<Product, String> isPriceEvenOrOdd = product -> product.price() % 2 == 0 ? "Even" : "Odd";

        var p1 = new Product("Xbox 360", 1100d);
        var p2 = new Product("PS3", 1303D);
        var p3 = new Product("Nintendo Switch", 1200D);
        var products = List.of(p1, p2, p3);

        products.forEach(p -> System.out.println(isPriceEvenOrOdd.apply(p)));
    }
}
