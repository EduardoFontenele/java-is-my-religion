package reflection.serverstarting;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        initServer();
        var webServer = new WebServer();
    }

    public static void initServer() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var constructor = ServerConfig.class.getDeclaredConstructor(long.class, String.class);
        constructor.setAccessible(true);
        constructor.newInstance(8080, "Hello, everyone");
    }
}
