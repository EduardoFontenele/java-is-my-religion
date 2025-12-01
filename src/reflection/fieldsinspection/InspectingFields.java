package reflection.fieldsinspection;

import java.lang.reflect.Field;

public class InspectingFields {
    public static void main(String[] args) throws IllegalAccessException {
        var movie = new Movie(
                "Ursos Mansos e Peludos",
                2004,
                15.99,
                true,
                Category.COMEDY
        );
        printFieldsInformation(Movie.class, movie);
    }

    public static <T> void printFieldsInformation(Class<? extends T> clazz, T obj) throws IllegalAccessException {

        for(Field field : clazz.getDeclaredFields()) {
            System.out.printf("Field name: %s | field type: %s\n", field.getName(), field.getType().getSimpleName());
            System.out.printf("Is synthetic: %s\n",field.isSynthetic());
            System.out.printf("Field value: %s\n", field.get(obj).toString());
        }
    }

    private static class Movie extends Product {

        public static final double MINIMUM_PRICE = 10.99;

        private boolean isReleased;
        private Category category;
        private double actualPrice;

        public Movie(String name, int year, double price, boolean isReleased, Category category) {
            super(name, year);
            this.actualPrice = Math.max(price, MINIMUM_PRICE);
            this.isReleased = isReleased;
            this.category = category;
        }

        public class MovieStats {
            private double timesWatched;

            public MovieStats(double timesWatched) {
                this.timesWatched = timesWatched;
            }

            public double getRevenue() {
                return timesWatched * actualPrice;
            }
        }
    }

    private static class Product {
        protected String name;
        protected int year;
        protected double actualPrice;

        public Product(String name, int year) {
            this.name = name;
            this.year = year;
        }
    }

    private enum Category {
        ADVENTURE,
        COMEDY,
        ACTION
    }
}
