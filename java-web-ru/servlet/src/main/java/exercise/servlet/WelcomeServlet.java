package exercise.servlet;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// BEGIN

public class WelcomeServlet extends HttpServlet {
    protected void doGet(HttpServlet request, HttpServlet response) throws ServletException, IOException {
        response.setContentType("text");
        PrintWriter writer = response.getWriter();
        writer.println("Hello, Hexlet!");

    }
}
// END
