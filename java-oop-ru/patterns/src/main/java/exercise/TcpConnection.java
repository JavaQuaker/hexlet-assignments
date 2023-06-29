package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;



import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

// BEGIN
public class TcpConnection implements Connection {
    private Connection connection;
    private String ip;
    private int data;
    public TcpConnection(String ip, int data) {
        this.ip = ip;
        this.data = data;
    }
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }


    @Override
    public String getCurrentState() {
        return this.connection.getCurrentState();
    }

    @Override
    public void connect() {
        System.out.println("");

    }

    @Override
    public void disconnect() {
        System.out.println("");
    }

    @Override
    public void write(String data) {
        System.out.println("");
    }

}