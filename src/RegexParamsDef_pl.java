import java.util.ListResourceBundle;

public class RegexParamsDef_pl extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            {"charset", "ISO-8859-2"},
            {"header", new String[]{"Podaj rodzaj samochodów jaki chcesz wyœwietliæ"}},
            {"param_input", "Rodzaj:"},
            {"submit", "Poka¿ wyniki wyszukiwania"},
            {"footer", new String[]{}},

    };
}