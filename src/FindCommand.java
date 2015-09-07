import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class FindCommand extends CommandImpl implements Serializable {

    public FindCommand() {
    }

    private ArrayList<HashMap<String, String>> carsList;

    private void readAndSearchInFile(String urlToFile, String typeOfCar) throws IOException {


        String fullText = "";
        String line = "";

        FileReader fileReader =
                new FileReader(urlToFile);


        BufferedReader bufferedReader =
                new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null) {
            // System.out.println(line);
            fullText += line;
            fullText += " ";
        }


        bufferedReader.close();

        StringTokenizer stringTokenizer = new StringTokenizer(fullText);

        System.out.println(stringTokenizer.countTokens());


        int wordsAmount = stringTokenizer.countTokens();


        int amount = wordsAmount / 5;


        //osobowy Audi R8-Spyder 2013 17.6/100km

        carsList = new ArrayList<>();

        for (int i = 0; i < amount; i++) {

            HashMap<String, String> mapInside = new HashMap<>();

            mapInside.put("typ", stringTokenizer.nextToken());
            mapInside.put("marka", stringTokenizer.nextToken());
            mapInside.put("model", stringTokenizer.nextToken());
            mapInside.put("rok", stringTokenizer.nextToken());
            mapInside.put("spalanie", stringTokenizer.nextToken());


            System.out.println("typA= " + mapInside.get("typ") + ",   a typeOfCar= " + typeOfCar);


            if (mapInside.get("typ").equalsIgnoreCase(typeOfCar)) {
                carsList.add(mapInside);
                System.out.println("typ= " + mapInside.get("typ"));

            }
        }


        System.out.printf("Size of carsList: " + carsList.size());


    }


    public void execute(String url) {
        clearResult();

        String input = (String) getParameter("input");

        try {
            readAndSearchInFile(url, input);

        } catch (Exception e) {

        }

        if (input == null) {
            setStatusCode(1);
            return;
        } else {
            setStatusCode(0);


            addResult(carsList);
        }
    }
}


