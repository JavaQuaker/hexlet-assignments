package exercise.connections;

import exercise.TcpConnection;

public interface Connection {
    // BEGIN
    String getCurrentState();
    void connect();
    void disconnect();
    void write(String data);
    // END
}
