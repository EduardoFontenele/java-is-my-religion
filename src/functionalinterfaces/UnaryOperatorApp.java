package functionalinterfaces;

import java.util.function.UnaryOperator;

public class UnaryOperatorApp {
    public static void main(String[] args) {
        UnaryOperator<Integer> plusTwo = x -> x + 2;
        UnaryOperator<Integer> timesTwo = x -> x * 2;
        UnaryOperator<Integer> plusFive = x -> x + 5;

        Integer someValue = plusTwo
                .andThen(timesTwo)
                .andThen(plusFive)
                .apply(7);

        System.out.println(someValue);
    }
}
