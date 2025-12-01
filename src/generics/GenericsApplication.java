package generics;

public class GenericsApplication {
    private static class MagicBox<T> {
        private T content;

        public void putAway(T content) {
            this.content = content;
        }

        public T remove() {
            T ref = this.content;
            this.content = null;
            return ref;
        }

        public boolean isEmpty() {
            return content == null;
        }
    }

    private static class Util {
        public static <T, J> String printType(T object, J something) {
            System.out.println(something.getClass().getCanonicalName());
            return object.getClass().getSimpleName();
        }
    }

    public static void main(String[] args) {
        var stringMagicBox = new MagicBox<>();
        System.out.println(stringMagicBox.isEmpty());
        stringMagicBox.putAway("Something Special");
        System.out.println(stringMagicBox.isEmpty());
        System.out.println(stringMagicBox.remove());
        System.out.println(stringMagicBox.isEmpty());

        var name = "Eduardo Fontenele";
        System.out.println(Util.printType(name, 1));
    }
}
