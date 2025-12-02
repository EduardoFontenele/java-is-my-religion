package reflection.fieldsinspection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReflectiveJsonSerializer {
    public static void main(String[] args) throws IllegalAccessException {
        var e1 = new Employee(
                1L,
                "Eduardo Fontenele",
                true,
                new Address("Q. 209 - Águas Claras", 7),
                EmployeeType.DEVELOPER,
                List.of(5, 9, 10, 11),
                new String[]{"Jason Voorhees", "Michael Myers", "Ezio Auditore da Firenze", "Noob Saibot"},
                List.of(new Job(1L, "Implement DB"), new Job(2L, "Create queries")),
                24,
                new int[]{1, 3, 5}
        );

        System.out.println(writeValueAsJson(Employee.class, e1));
    }

    /**
     * Converts a Java object to its JSON representation using reflection.
     *
     * @param clazz the class for field introspection
     * @param object the instance to be serialized
     * @return formatted JSON with indentation
     * @throws IllegalAccessException if private fields cannot be accessed
     */
    public static String writeValueAsJson(Class<?> clazz, Object object) throws IllegalAccessException {
        return serialize(clazz, object, 1);
    }

    // TODO: 1 - Implement arrays reflection
    // TODO: 2 - Implements reflection for maps
    private static String serialize(Class<?> clazz, Object object, int tabs) throws IllegalAccessException {
        var sb = new StringBuilder();
        var fields = clazz.getDeclaredFields();

        sb.append("{");

        if(fields.length > 1) sb.append("\n");

        for(int i = 0; i < fields.length; i++) {
            var field = fields[i];
            field.setAccessible(true);
            sb.append("\t".repeat(tabs));
            var fieldType = field.getType();
            sb.append("\"").append(field.getName()).append("\"").append(": ");

            if(fieldType.isPrimitive()) {
                sb.append(field.get(object));
            } else if(isStringOrEnum(fieldType)) {
                sb.append("\"").append(field.get(object)).append("\"");
            } else if (fieldType.isArray()) {
                var array = field.get(object);
                var elementsType = array.getClass().getSimpleName();

                switch (elementsType) {
                    case "int[]" -> {
                        var intArray = (int[]) array;
                        sb.append("[");
                        for(int j = 0; j < intArray.length; j++) {
                            if(j != intArray.length - 1) sb.append(intArray[j]).append(",");
                            sb.append(intArray[j]);
                        }
                        sb.append("]");
                    }
                    case "Object[]", "String[]" -> {
                        var objects = (Object[]) field.get(object);
                        var list = Arrays.asList(objects);
                        appendListTypeJsonNode(sb, list ,list.size(), tabs);
                    }
                }


            } else if(fieldType == List.class) {
                var list = (List<?>) field.get(object);
                appendListTypeJsonNode(sb, list, list.size(), tabs);
            } else if(fieldType == Map.class) {
                var map = (Map<?,?>) field.get(object);

            } else {
                sb.append(serialize(field.getType(), field.get(object), tabs + 1));
            }

            if(i != fields.length - 1) {
                sb.append(",");
            }

            sb.append("\n");
        }

        sb.append("\t".repeat(tabs - 1)).append("}");

        return sb.toString();
    }

    private static void appendListTypeJsonNode(StringBuilder sb, List<?> list, int listSize, int tabs) throws IllegalAccessException {
        if(listSize == 0) {
            sb.append("[]");
            return;
        }

        var elementType = list.stream().findFirst().get().getClass();

        sb.append("[");

        if(isPrimitive(elementType)) {
            for(int j = 0; j < listSize; j++) {
                if(j != listSize - 1) {
                    sb.append(list.get(j)).append(", ");
                    continue;
                }
                sb.append(list.get(j));
            }
        } else if(isStringOrEnum(elementType)) {
            for(int j = 0; j < listSize; j++) {
                if(j != listSize - 1) {
                    sb.append("\"").append(list.get(j)).append("\", ");
                    continue;
                }
                sb.append("\"").append(list.get(j)).append("\"");
            }
        } else {
            sb.append("\n\t");
            for(int j = 0; j < listSize; j++) {
                if(j != listSize - 1) {
                    sb.append(serialize(list.get(j).getClass(), list.get(j), tabs + 1));
                    sb.append(", ");
                    continue;
                }

                sb.append(serialize(list.get(j).getClass(), list.get(j), tabs + 1));
                sb.append("\t".repeat(tabs - 1));

            }
        }

        sb.append("]");
    }

    private enum Primitives {
        BYTE(byte.class, Byte.class),
        SHORT(short.class, Short.class),
        INTEGER(int.class, Integer.class),
        LONG(long.class, Long.class),
        FLOAT(float.class, Float.class),
        DOUBLE(double.class, Double.class),
        BOOLEAN(boolean.class, Boolean.class),
        CHAR(char.class, Character.class);

        private final Class<?> primitiveClass;
        private final Class<?> wrapperClass;

        Primitives(Class<?> primitiveClass, Class<?> wrapperClass) {
            this.primitiveClass = primitiveClass;
            this.wrapperClass = wrapperClass;
        }

        public Class<?> getPrimitiveClass() {
            return this.primitiveClass;
        }

        public Class<?> getWrapperClass() {
            return this.wrapperClass;
        }

    }

    private static boolean isStringOrEnum(Class<?> type) {
        return type == String.class || type.isEnum();
    }

    private static boolean isPrimitive(Class<?> clazz) {
        for (var type : Primitives.values()) {
            if (type.getPrimitiveClass().equals(clazz) || type.getWrapperClass().equals(clazz)) {
                return true;
            }
        }
        return false;
    }
}
