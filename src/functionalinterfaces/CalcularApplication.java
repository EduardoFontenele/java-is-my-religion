package functionalinterfaces;

public class CalcularApplication {
    public static void main(String[] args) {
        var calcularImpl = new Calcular() {
            @Override
            public Double execute(double a, double b) {
                return a + b;
            }
        };

        System.out.println(calcularImpl.execute(5d, 5d));
        Calcular calcularImpl2 = (x, y) -> x * y;
        System.out.println(calcularImpl2.execute(5d, 5d));
    }
}
