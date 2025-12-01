package functionalinterfaces;

import functionalinterfaces.models.Product;

import java.util.List;
import java.util.function.Supplier;

public class SupplierApp {
    public static void main(String[] args) {
        Supplier<List<Product>> productsSupplier = () -> List.of(
                new Product("Xbox 360", 1100d),
                new Product("PS3", 1303D),
                new Product("Nintendo Switch", 1200D)
        );

        productsSupplier.get().forEach(System.out::println);
    }
}
