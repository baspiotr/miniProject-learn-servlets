import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class GetParamsServ extends HttpServlet {

    private ServletContext context;
    private String resBundleServ;

    public void init() {
        context = getServletContext();
        resBundleServ = context.getInitParameter("resBundleServ");
    }


    public void serviceRequest(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {


        RequestDispatcher disp = context.getRequestDispatcher(resBundleServ);
        disp.include(request, response);


        String charset = BundleInfo.getCharset();

        String[] headers = BundleInfo.getHeaders();

        String[] paramNames = BundleInfo.getCommandParamNames();

        String[] paramDescr = BundleInfo.getCommandParamDescr();

        String submitMsg = BundleInfo.getSubmitMsg();

        String[] footers = BundleInfo.getFooters();

        request.setCharacterEncoding(charset);

        HttpSession session = request.getSession();

        response.setCharacterEncoding(charset);

        request.setCharacterEncoding(charset);

        System.out.println("Charset= " + charset);


        PrintWriter out = response.getWriter();


        out.println("<html> " +
                "<head> " +
                "<title>GetParamServ</title> " +
                "</head> " +
                "<body> ");

        out.println("<center><h2>");
        for (int i = 0; i < headers.length; i++)
            out.println(headers[i]);


        out.println("</center></h2><hr>");

        out.println("<form method=\"post\">");
        for (int i = 0; i < paramNames.length; i++) {
            out.println(paramDescr[i] + "<br>");
            out.print("<input type=\"text\" size=\"30\" name=\"" +
                    paramNames[i] + "\"");

            String sessionAttribute = (String) session.getAttribute("param_" + paramNames[i]);
            if (sessionAttribute != null) out.print(" value=\"" + sessionAttribute + "\"");
            out.println("><br>");
        }
        out.println("<br><input type=\"submit\" value=\"" + submitMsg + "\">");
        out.println("</form>");


        out.println("</body> " +
                "</html> ");


        for (int i = 0; i < paramNames.length; i++) {
            String paramVal = request.getParameter(paramNames[i]);

            if (paramVal == null) return;

            session.setAttribute("param_" + paramNames[i], paramVal);

        }
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        serviceRequest(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        serviceRequest(request, response);
    }

}