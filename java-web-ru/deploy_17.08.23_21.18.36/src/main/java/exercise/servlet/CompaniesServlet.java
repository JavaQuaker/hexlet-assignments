package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        String searchParam = request.getParameter("search");
        PrintWriter out = response.getWriter();
        List<String> companies = getCompanies();

        if (searchParam == null || searchParam.equals("")) {
            out.println(outputCollectionWithSeparator(companies, "\n"));
            return;
        }
        List<String> matchCompany = companies.stream()
                .filter(company -> company.contains(searchParam))
                .collect(Collectors.toList());
        if (!matchCompany.isEmpty()) {
            out.println(outputCollectionWithSeparator(matchCompany, "\n"));
        } else {
            out.println("Companies not found");
        }
        // END
    }

    private StringJoiner outputCollectionWithSeparator(List<String> items, String separator) {
        StringJoiner result = new StringJoiner(separator);
        for (String item: items) {
            result.add(item);
        }
        return result;
    }
        // END
    }

