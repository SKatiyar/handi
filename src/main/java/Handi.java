import static spark.Spark.*;

public class Handi {
    public static void main(String[] args) {
        get("/", (req, res) -> "Hello World");
    }
}