import java.util.*;
import com.google.gson.Gson;
import spark.ResponseTransformer;

import static spark.Spark.*;

public class Handi {
    private static class Patient {
        String name;
        String phone;

        public Patient(String nameVal, String phoneVal) {
            name = nameVal;
            phone = phoneVal;
        }
    }

    private static int activeToken;
    private static ArrayList<Patient> patients;

    public static class JsonTransformer implements ResponseTransformer {
        private Gson gson = new Gson();

        @Override
        public String render(Object model) {
            return gson.toJson(model);
        }
    }

    public static void main(String[] args) {
        patients = new ArrayList<Patient>();
        activeToken = 0;

        // Define heroku port
        port((System.getenv("PORT") != "") ? Integer.valueOf(System.getenv("PORT")) : Integer.valueOf(5000));

        get("/", (req, res) -> {
            return "And you run and you run to catch up with the sun but it's sinking. Racing around to come up behind you again";
        });

        get("/patients", "application/json", (req, res) -> {
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("patients", patients);
            res.header("Content-Type", "application/json");
            return data;
        }, new JsonTransformer());

        get("/patients/:action", "application/json", (req, res) -> {
            String action = req.params(":action");
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("action", action);
            res.header("Content-Type", "application/json");
            return data;
        }, new JsonTransformer());

        post("/patients", "application/json", (req, res) -> {
            Patient patient = new Gson().fromJson(req.body(), Patient.class);
            patients.add(patient);
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("token", patients.size() - 1);
            res.header("Content-Type", "application/json");
            return data;
        }, new JsonTransformer());
    }
}