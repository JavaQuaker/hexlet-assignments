package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// BEGIN
@WebServlet("/")
public class WelcomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text");
        PrintWriter writer = resp.getWriter();
        writer.println("Hello, Hexlet!");
    }
}
// END
