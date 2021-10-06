/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

public class App {

    public static void main(String[] args) throws Exception {
//        Quotes q = new Quotes();
//        System.out.println(q.readFromJsonFile("app/src/main/resources/recentquotes.json"));
//        System.out.println(q.readFromJsonFile("app/src/main/resources/testQuote.json"));
        String filePath = "./app/src/main/resources/recentquotes.json";
//        String uRLPath = "https://ron-swanson-quotes.herokuapp.com/v2/quotes";

        try
        {

            System.out.println(Quotes.readFromAPI(filePath));

        }
        catch (Exception error)
        {
            System.err.println(error);
        }



    }
}
