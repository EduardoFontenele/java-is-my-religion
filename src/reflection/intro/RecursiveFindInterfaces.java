package reflection.intro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RecursiveFindInterfaces {

    public static void main(String[] args) {
        var info = findAllImplementedInterfaces(ArrayList.class);

        for(var classInfo : info) {
            System.out.println(classInfo.getSimpleName());
        }
    }

    private static Set<Class<?>> findAllImplementedInterfaces(Class<?> input) {
        Set<Class<?>> result = new HashSet<>();
        Class<?>[] inputInterfaces = input.getInterfaces();

        for(var inputInterface : inputInterfaces) {
            result.add(inputInterface);
            result.addAll(findAllImplementedInterfaces(inputInterface));
        }

        return result;
    }
}
