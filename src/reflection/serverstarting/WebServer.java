package reflection.serverstarting;

public class WebServer {

    private final ServerConfig serverConfig;

    public WebServer() {
        this.serverConfig = ServerConfig.getInstance();
        startServer();
    }

    private void startServer() {
        System.out.println("Server started at port " + serverConfig.getServerAddress() + " and says " + serverConfig.getGreeting());
    }
}
