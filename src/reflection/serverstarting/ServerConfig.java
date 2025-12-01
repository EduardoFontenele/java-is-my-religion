package reflection.serverstarting;

public class ServerConfig {
    private static ServerConfig INSTANCE;
    private final long serverAddress;
    private final String greeting;

    private ServerConfig(long serverAddress, String greeting) {
        this.serverAddress = serverAddress;
        this.greeting = greeting;

        if(INSTANCE == null) {
            INSTANCE = this;
        }
    }

    public static ServerConfig getInstance() {
        return INSTANCE;
    }

    public long getServerAddress() {
        return serverAddress;
    }

    public String getGreeting() {
        return greeting;
    }
}
