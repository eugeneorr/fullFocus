package model;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class ToDoList {

    private ArrayList<User> users;
    public static ToDoList toDoList = new ToDoList("toDoList.json");

    public ToDoList(String filename) {
        parseDatabase(filename);
    }

    public ToDoList(ArrayList<User> users) {
        this.users = users;
    }

    private void parseDatabase(String filename) {
        JsonParser parser = new JsonParser();
        try {
            Object obj = parser.parse(new FileReader("/Users/Eugene/Desktop/java_projects/fullfocus/src/main/database/" + filename));
            JsonArray jsonUsersArray = (JsonArray) obj;
            users = new ArrayList<>();
            for (JsonElement jsonUserElement: jsonUsersArray) {
                JsonObject jsonUserObject = jsonUserElement.getAsJsonObject();
                String username = jsonUserObject.get("username").getAsString();
                JsonArray jsonGroupsArray = jsonUserObject.getAsJsonArray("groups");
                ArrayList<Group> groups = new ArrayList<>();
                for (JsonElement jsonGroupElement: jsonGroupsArray) {
                    JsonObject jsonGroupObject = jsonGroupElement.getAsJsonObject();
                    String name = jsonGroupObject.get("name").getAsString();
                    JsonArray jsonTasksArray = jsonGroupObject.getAsJsonArray("tasks");
                    ArrayList<Task> tasks = new ArrayList<>();
                    for (JsonElement jsonTaskElement: jsonTasksArray) {
                        JsonObject jsonTaskObject = jsonTaskElement.getAsJsonObject();
                        String taskText = jsonTaskObject.get("text").getAsString();
                        tasks.add(new Task(taskText));
                    }
                    groups.add(new Group(name, tasks));
                }
                HashSet<String> sharedUsers = new HashSet<>();
                JsonArray jsonSharedUsersArray = jsonUserObject.getAsJsonArray("sharedUsers");
                for (JsonElement jsonSharedUserElement: jsonSharedUsersArray) {
                    String sharedUserUsername = jsonSharedUserElement.getAsString();
                    sharedUsers.add(sharedUserUsername);
                }
                users.add(new User(username, groups, sharedUsers));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read toDoList.json: " + e);
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser(String username) {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
        //saveData("toDoList.json");
    }

    public void saveData(String filename) {
        try (Writer writer = new FileWriter("/Users/Eugene/Desktop/java_projects/fullfocus/src/main/database/" + filename)) {
            Gson gson = new GsonBuilder().create();
            writer.write(gson.toJson(users));
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write to auth file: " + e);
        }
    }

}
