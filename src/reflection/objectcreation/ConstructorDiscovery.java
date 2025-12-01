package reflection.objectcreation;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;


public class ConstructorDiscovery {

    public static void main(String[] args) {
        // printConstructorData(Person.class);
        Person person = (Person) createAnyInstanceFromClass(Person.class, "Eduardo Fontenele", 21);
        Person p2 = createAnyInstanceFromClassEnhanced(Person.class);
        System.out.println(person);
        System.out.println(p2);
    }

    public static Object createAnyInstanceFromClass(Class<?> clazz, Object ...args) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Object result = null;

        for(var c : constructors) {
            if(c.getParameterCount() == args.length) {
                try {
                    result = c.newInstance(args);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return result;
    }

    public static <T> T createAnyInstanceFromClassEnhanced(Class<?> clazz, Object ...args) {
        var constructors = clazz.getDeclaredConstructors();
        Object result = null;

        for(var c : constructors) {

            if(c.getParameterTypes().length == args.length) {
                try {
                    result = c.newInstance(args);
                } catch (Exception ignored) {
                }
            }
        }

        return (T) result;
    }

    public static void printConstructorData(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();

        System.out.println("Class " + clazz.getSimpleName() + " has " + constructors.length + " declared constructors");

        int i = 0;
        for(Constructor<?> constructor : constructors) {
            System.out.println("Constructor " + i + " parameters types:");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            List<String> paramTypesNames = Arrays.asList(parameterTypes)
                    .stream()
                    .map(Class::getSimpleName)
                    .toList();
            System.out.println(paramTypesNames);
            i++;

        }
    }

    private static class Person {
        private final Address address;
        private final String name;
        private final int age;

        @Override
        public String toString() {
            assert this.address != null;
            return String.format("{name:%s, address:{street:%s, number:%d}, age:%d}",
                    this.name, this.address != null ? this.address.street : "N/A",
                    this.address != null ? this.address.street : 0,
                    this.age);
        }

        private Person() {
            this.address = null;
            this.name = "N/A";
            this.age = 0;
        }

        private Person(Address address, String name, int age) {
            this.address = address;
            this.name = name;
            this.age = age;
        }

        private Person(String name, int age) {
            this.address = null;
            this.name = name;
            this.age = age;
        }

        public Address getAddress() {
            return address;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    private static class Address {
        private String street;
        private int number;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public Address(String street, int number) {
            this.street = street;
            this.number = number;
        }
    }
}
