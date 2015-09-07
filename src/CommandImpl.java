import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandImpl implements Serializable, Command {

    private Map parameterMap = new HashMap();
    private ArrayList<HashMap<String, String>> resultList = new ArrayList<>();

    private int statusCode;

    public CommandImpl() {
    }

    public void init() {
    }

    public void setParameter(String name, Object value) {
        parameterMap.put(name, value);
    }

    public Object getParameter(String name) {
        return parameterMap.get(name);
    }

    public void execute(String url) {
    }

    public List getResults() {
        return resultList;
    }


    public void addResult(ArrayList<HashMap<String, String>> s) {
        resultList = s;
    }


    public void clearResult() {
        resultList.clear();
    }

    public void setStatusCode(int code) {
        statusCode = code;
    }

    public int getStatusCode() {
        return statusCode;
    }

}