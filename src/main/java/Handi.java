import static spark.Spark.*;

public class Handi {
    public static void main(String[] args) {
        // Define heroku port
        Integer herokuPort = Integer.valueOf(System.getenv("PORT"));
        herokuPort =  (herokuPort > 0) ? herokuPort : Integer.valueOf(5000);
        port(herokuPort);


        get("/", (req, res) -> "Hello World");
    }
}