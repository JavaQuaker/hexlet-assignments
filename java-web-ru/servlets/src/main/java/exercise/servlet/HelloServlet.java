package exercise.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
@WebServlet(name = "HelloServlet", urlPatterns = "/")
public final class HelloServlet extends HttpServlet {
    // BEGIN
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain");
        String name = req.getParameter("name");
        if (name == null) {
            res.getWriter().write("Hello, Guest!");
        } else {
            res.getWriter().write("Hello, " + name + "!");
        }
    }
    // END
}
