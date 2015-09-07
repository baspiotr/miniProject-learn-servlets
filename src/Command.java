import java.util.List;

public interface Command {
    void init();

    void setParameter(String name, Object value);

    Object getParameter(String name);

    void execute(String url);

    List getResults();

    void setStatusCode(int code);

    int getStatusCode();
}