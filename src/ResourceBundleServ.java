import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;



public class ResourceBundleServ extends HttpServlet {

    private String resBundleName;

    public void init() {
        resBundleName = getServletContext().getInitParameter("resBundleName");
    }

    public void serviceRequest(HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession ses = req.getSession();
        ResourceBundle paramsRes = (ResourceBundle) ses.getAttribute("resBundle");


        if (paramsRes == null) {
            Locale loc = req.getLocale();
            paramsRes = ResourceBundle.getBundle(resBundleName, loc);
            ses.setAttribute("resBundle", paramsRes);


            BundleInfo.generateInfo(paramsRes);
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



