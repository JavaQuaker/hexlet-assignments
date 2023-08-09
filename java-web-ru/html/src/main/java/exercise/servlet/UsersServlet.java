package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        File file = new File("src/main/resources/users.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> result = mapper.readValue(file, new TypeReference<List<Map<String, String>>>() {
        });

        return result;

        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN

        StringBuilder body = new StringBuilder();
        body.append("""
                    <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application</title>
                        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                              rel=\"stylesheet\"
                              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                              crossorigin=\"anonymous\">
                    </head>
                            
                    """);
        List<Map<String, String>> result = getUsers();
        for (Map<String, String> map : result) {
            String id = map.get("id");
            String firstName = map.get("firstName");
            String lastname = map.get("lastName");
            String fullName = firstName + " " + lastname;

            body.append("<table>");
            body.append("<tr>"+ "<th>" + "id" + "</th>" + "<th>" + "fullName" +"</th>" + "</tr>");
            body.append("<tr>" + "<td>" + id + "</td>" + "<td>" + "<a href='/users/" + id + "'>" + fullName + "</a>" + "</td>" + "</tr>");
            body.append("</table>");

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(body.toString());
        }
        body.append("""
                </body>
                </html>
                """);
        PrintWriter print = response.getWriter();
        print.println(body.toString());
    }

        // END


    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        StringBuilder body = new StringBuilder();
        body.append("""
                    <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application</title>
                        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                              rel=\"stylesheet\"
                              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                              crossorigin=\"anonymous\">
                    </head>
                            
                    """);
        List<Map<String, String>> result = getUsers();
        PrintWriter print = response.getWriter();
        for (Map<String, String> map : result) {
            if ((id == null) || (id.isEmpty())) {
                response.sendError(404, "Not found");
            } else {
                id = map.get("id");
                String firstName = map.get("firstName");
                String lastName = map.get("lastName");
                String email = map.get("email");
                body.append("<table>");
                body.append("<tr>" + "<th>" + "firstName" + "</th>" + "<th>" + "lastName" + "</th>" + "<th>" + "id" +
                        "</th>" + "<th>" + "email" + "</tr>");
                body.append("<tr>" + "<td>" + firstName + "</td>" + "<td>" + lastName + "</td>" + "<td>" + id + "<td>"
                        + email + "</td>" + "</tr>");
                body.append("</table>");
            }
            body.append("""
                    </body>
                    </html>
                    """);

        }
            print.println(body.toString());
        }
        // END
    }


