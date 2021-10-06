package quotes;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Quotes {
    private String author;
    private String text;

    public Quotes(String text) {
        this.text = text;
        this.author = "ron-swanson";
    }

    public Quotes() {


    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }
    public static Quotes readFromAPI( String backupFile)
    {
        String urlPath = "https://ron-swanson-quotes.herokuapp.com/v2/quotes";
        StringBuilder content = new StringBuilder();
        Quotes quote = null;

        try
        {

            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
//            System.out.println(connection.getResponseCode());

            // synchronous: Java is going to be working on running line 15 for a while.
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = input.readLine()) != null) {
                content.append(inputLine);
            }
            input.close();

            quote = new Quotes(content.toString().replaceAll("]$|^\\[",""));

            try
            {
                fileWriter(backupFile, quote);
            }
            catch (Exception errorFile)
            {
                System.err.println(errorFile);
            }
        }
        catch (IOException errorAPI)
        {

            System.err.println("No internet connection.");
            try
            {
                quote = new Quotes(readFromJsonFile(backupFile));
            }
            catch (Exception error)
            {
                System.err.println(error);
            }
        }

        return quote;
    }

    public static void fileWriter(String filePath, Quotes quote) throws Exception
    {
        Gson gson = new Gson();

        BufferedReader file = new BufferedReader(new FileReader(filePath));

        TypeToken<ArrayList<Quotes>> token = new TypeToken<ArrayList<Quotes>>(){};

        ArrayList<Quotes> quotesFromFiles = gson.fromJson(file, token.getType());

        quotesFromFiles.add(quote);

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        writer.write(gson.toJson(quotesFromFiles));

        writer.close();
        file.close();
    }


//    public  void convertFromJson()  {
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        try {
//            gson.fromJson(new BufferedReader(new FileReader("./app/src/main/resources/quotes.json")), Quotes.class);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        List<Quotes> quotesBook = gson.fromJson("./app/src/main/resources/quotes.json", (Type) Quotes.class);
//        System.out.println(quotesBook.toString());
//
//
//    }
public static String readFromJsonFile(String path) throws Exception
{
    Gson gson = new Gson();

    BufferedReader file = new BufferedReader(new FileReader(path));
    Quotes[] quotesFromFiles = gson.fromJson(file, Quotes[].class);

    int randomIndex = (int)(Math.random() * quotesFromFiles.length);
    String randomQuote = quotesFromFiles[randomIndex].toString();

    return randomQuote;
}

    @Override
    public String toString() {
        return "Quotes{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

