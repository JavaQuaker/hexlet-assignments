package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect() {
        System.out.println("Try to connect when connection already established. Message must contains word Error");
    }

    @Override
    public void disconnect() {
        TcpConnection c = this.tcpConnection;
        c.setConnection(new Connected(c));
    }

    @Override
    public void write(String data) {
        System.out.println("two");
    }
}



// END
