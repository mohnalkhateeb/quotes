package quotes;

import java.io.*;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URL;

public class Quotes {
    private String author;
    private String text;

//    public Quotes(String author, String text) {
//        this.author = author;
//        this.text = text;
//    }

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
    public static Quotes readFromAPI(String urlPath, String backupFilePath)
    {
        StringBuilder content = new StringBuilder();
        Quotes newQuote = null;

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

            newQuote = new Quotes(content.toString().replaceAll("]$|^\\[",""));

            try
            {
                writeToFile(backupFilePath, newQuote);
            }
            catch (Exception errorFile)
            {
                System.err.println(errorFile);
            }
        }
        catch (IOException errorAPI)
        {
//            System.err.println(errorAPI);

            System.err.println("No internet connection.");
            try
            {
                newQuote = new Quotes(readFromFile(backupFilePath));
            }
            catch (Exception errorFile)
            {
                System.err.println(errorFile);
            }
        }

        return newQuote;
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

