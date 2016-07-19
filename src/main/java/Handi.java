import static spark.Spark.*;

public class Handi {
    public static void main(String[] args) {
        // Define heroku port
        port((System.getenv("PORT") != "") ? Integer.valueOf(System.getenv("PORT")) : Integer.valueOf(5000));


        get("/", (req, res) -> " If you didn't care what happened to me, <br> And I didn't care for you, <br> We would zig zag our way through the boredom and pain <br> Occasionally glancing up through the rain. <br> Wondering which of the buggars to blame <br> And watching for pigs on the wing.");
    }
}