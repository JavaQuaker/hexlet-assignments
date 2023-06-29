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
        return "disconnect";
    }

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {
        TcpConnection c = this.tcpConnection;
        c.setConnection(new Connected(c));
    }

    @Override
    public void write(String data) {

    }
}



// END
