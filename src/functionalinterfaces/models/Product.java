package functionalinterfaces.models;

public record Product(String name, Double price) {
    @Override
    public String toString() {
        return "{ name:$1, price:$2 }".replace("$1", name).replace("$2", Double.toString(price));
    }
}
