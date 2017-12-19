package auth;

import com.google.gson.*;
import model.ToDoList;
import model.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;


public class Auth {

    public static Auth auth = new Auth("auth.json");

    private ArrayList<UserAuth> authentication = new ArrayList<>();

    private Auth(String filename) {
        JsonParser jsonParser = new JsonParser();
        try {
            JsonArray authArray = (JsonArray) jsonParser.parse(new FileReader("/Users/Eugene/Desktop/java_projects/fullfocus/src/main/database/" + filename));
            for (JsonElement jsonAuthElememt: authArray) {
                JsonObject jsonUserObject = jsonAuthElememt.getAsJsonObject();
                String username = jsonUserObject.get("username").getAsString();
                String password = jsonUserObject.get("password").getAsString();
                authentication.add(new UserAuth(username, password));
            }
        } catch (IOException e) {
            System.out.println("Failed to fetch auth file: " + e);
        }
    }

    public Auth() {
        authentication = new ArrayList<>();
    }

    public boolean authenticate(String username, String password) {
        for (UserAuth userAuth: authentication) {
            if (userAuth.username.equals(username) && userAuth.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isVacantUsername(String username) {
        for (UserAuth userAuth: authentication) {
            if (userAuth.username.equals(username)) {
                return false;
            }
        }
        return true;
    }

    public void addUser(String username, String password, boolean isTest) {
        authentication.add(new UserAuth(username, password));
        if (isTest) { return; }
        try (Writer writer = new FileWriter("/Users/Eugene/Desktop/java_projects/fullfocus/src/main/database/auth.json")) {
            Gson gson = new GsonBuilder().create();
            writer.write(gson.toJson(authentication));
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write to auth file: " + e);
        }
    }

    private class UserAuth {

        private String username;
        private String password;

        UserAuth(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
