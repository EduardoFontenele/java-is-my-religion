package reflection.intro;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        printClassInfo(Square.class, String.class, List.class, ArrayList.class, int.class, int[].class);
    }

    private static void printClassInfo(Class<?> ...classes) {
        for (Class<?> clazz : classes) {
            System.out.println("Class name: " + clazz.getSimpleName());
            System.out.println("Class package name: " + clazz.getPackageName());

            Class<?>[] implementedInterfaces = clazz.getInterfaces();

            System.out.println(clazz.getSimpleName() + " implements:");
            for(Class<?> implementedInteface : implementedInterfaces) {
                System.out.println(implementedInteface.getSimpleName());
            }

            System.out.println("Is primitive:" + clazz.isPrimitive());
            System.out.println("Is annotation:" + clazz.isAnnotation());
            System.out.println("Is array:" + clazz.isArray());
            System.out.println("Is record:" + clazz.isRecord());
            System.out.println("Is anonymous:" + clazz.isAnonymousClass());
            System.out.println();
        }
    }

    private static class Square implements Drawable {

        @Override
        public int getNumberOfCorners() {
            return 4;
        }
    }

    private static interface Drawable {
        int getNumberOfCorners();
    }

    private enum Color {
        BLUE,
        RED,
        GREEN
    }
}
