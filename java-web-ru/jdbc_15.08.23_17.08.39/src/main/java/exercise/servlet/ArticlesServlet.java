package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;


public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }
    private Map<String, String> getArticleById(String id, Connection connection) throws SQLException {
        String query = "SELECT * FROM articles WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();

        if (!rs.first()) {
            return null;
        }

        return Map.of(
                "id", id,
                "title", rs.getString("title"),
                "body", rs.getString("body")
        );
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<Map<String, String>> articles = new ArrayList<>();
        int articlesPerPage = 10;
        String page = request.getParameter("page");
        int normalizedPage = page == null ? 1 : Integer.parseInt(page);
        int offset = (normalizedPage - 1) * articlesPerPage;

        String query = "SELECT * FROM articles ORDER BY id LIMIT ? OFFSET ?";

        try {
            // Используем PreparedStatement
            // Он позволяет подставить в запрос значения вместо знака ?
            PreparedStatement statement = connection.prepareStatement(query);
            // Указываем номер позиции в запросе (номер начинается с 1) и значение,
            // которое будет подставлено
            statement.setInt(1, articlesPerPage);
            statement.setInt(2, offset);
            // Выполняем запрос
            // Результат выполнения представлен объектом ResultSet
            ResultSet rs = statement.executeQuery();

            // При помощи метода next() можно итерировать по строкам в результате
            // Указатель перемещается на следующую строку в результатах
            while (rs.next()) {
                articles.add(Map.of(
                                // Так можно получить значение нужного поля в текущей строке
                                "id", rs.getString("id"),
                                "title", rs.getString("title"),
                                "body", rs.getString("body")
                        )
                );
            }

        } catch (SQLException e) {
            // Если произошла ошибка, устанавливаем код ответа 500
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        // Устанавливаем значения атрибутов

        request.setAttribute("articles", articles);
        request.setAttribute("page", page);

        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        Map<String, String> article;
        String id = getId(request);
//        String query = "SELECT id, title, body FROM articles WHERE id = ?";
        try {
            article = getArticleById(id, connection);
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setInt(1, Integer.parseInt(id));
//            ResultSet rs = statement.executeQuery();

//            while (rs.next()) {
//                article.putAll(Map.of(
//                                // Так можно получить значение нужного поля в текущей строке
//                                "id", rs.getString("id"),
//                                "title", rs.getString("title"),
//                                "body", rs.getString("body")
//                        )
//                );
//            }

        } catch (SQLException e) {

            // Если произошла ошибка, устанавливаем код ответа 500
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        request.setAttribute("article", article);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}