import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ControllerServ extends HttpServlet {

    private ServletContext context;
    private Command command;
    private String presentationServ;
    private String getParamsServ;
    private Object lock = new Object();


    public void init() {

        context = getServletContext();

        presentationServ = context.getInitParameter("presentationServ");
        getParamsServ = context.getInitParameter("getParamsServ");
        String commandClassName = context.getInitParameter("commandClassName");


        try {
            Class commandClass = Class.forName(commandClassName);
            command = (Command) commandClass.newInstance();
        } catch (Exception exc) {
            throw new NoCommandException("Couldn't find or instantiate " +
                    commandClassName);
        }
    }


    public void serviceRequest(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        RequestDispatcher dispatcher = context.getRequestDispatcher(getParamsServ);
        dispatcher.include(request, response);

        HttpSession session = request.getSession();

        String[] paramNames = BundleInfo.getCommandParamNames();
        for (int i = 0; i < paramNames.length; i++) {

            String pval = (String) session.getAttribute("param_" + paramNames[i]);

            System.out.println("Param names= " + pval);

            if (pval == null) return;

            command.setParameter(paramNames[i], pval);

        }

        synchronized (lock) {

            ServletContext context = getServletContext();
            String urlToFile = context.getRealPath("/WEB-INF/auta.txt");

            command.execute(urlToFile);

            List results = (List) command.getResults();

            session.setAttribute("StatusCode", new Integer(command.getStatusCode()));

            session.setAttribute("Results", results);
        }

        dispatcher = context.getRequestDispatcher(presentationServ);

        dispatcher.forward(request, response);
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