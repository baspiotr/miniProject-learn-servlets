import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Piotr on 2015-04-24.
 */
@WebServlet(name = "PresentationServ")
public class PresentationServ extends HttpServlet {

    public void servicePre(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {


        System.out.println("Jestem w presentation servlet");

        ServletContext context = getServletContext();


        String getParamsServ = context.getInitParameter("getParamsServ");


        RequestDispatcher dispatcher = context.getRequestDispatcher(getParamsServ);
        dispatcher.include(request, response);


        HttpSession session = request.getSession();

        ArrayList<HashMap<String, String>> results = (ArrayList<HashMap<String, String>>) session.getAttribute("Results");
        Integer code = (Integer) session.getAttribute("StatusCode");


        response.setContentType("text/html; charset=ISO-8859-2");

        PrintWriter out = response.getWriter();

        int size = results.size();

        String outStr = "";

        outStr += "<html>" +

                "<META HTTP-EQUIV=\"Content-Language\" CONTENT=\"pl\">" +
                "<META HTTP-EQUIV=\"Content-type\" CONTENT=\"text/html; charset=ISO-8859-2\"> " +
                "<head><title>OutputTableServlet</title> " +
                "</head><body><h1>Tabela z wybranymi samochodami</h1><table border=\"1\"><th>Marka</th>" +
                "<th>Model</th>" +
                "<th>Rok produkcji</th>" +
                "<th>Spalanie</th>";
        for (int i = 0; i < size; i++) {
            outStr += "<tr><td>" + results.get(i).get("marka") + "</td>" +
                    "<td>" + results.get(i).get("model") + "</td>" +
                    "<td>" + results.get(i).get("rok") + "</td>" +
                    "<td>" + results.get(i).get("spalanie") + "</td></tr>";

        }

        outStr += "</table>" +
                "</body>" +
                "</html>";


        out.println(outStr);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        servicePre(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        servicePre(request, response);
    }
}
