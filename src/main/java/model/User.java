package model;

import java.util.ArrayList;
import java.util.HashSet;

public class User {

    private String username;
    private ArrayList<Group> groups;
    private HashSet<String> sharedUsers;

    User(String username, ArrayList<Group> groups, HashSet<String> sharedUsers) {
        this.username = username;
        this.groups = groups;
        this.sharedUsers = sharedUsers;
    }

    public User(String username) {
        this.username = username;
        this.groups = new ArrayList<>();
        this.sharedUsers = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void addGroup(String name) {
        groups.add(new Group(name));
        //ToDoList.toDoList.saveData("toDoList.json");
    }

    public void addSharedUser(String username) {
        sharedUsers.add(username);
        //ToDoList.toDoList.saveData("toDoList.json");
    }

    public HashSet<String> getSharedUsers() {
        return sharedUsers;
    }

    public void removeSharedUser(String username) {
        sharedUsers.remove(username);
        //ToDoList.toDoList.saveData("toDoList.json");
    }

    public void removeGroup(int index) {
        groups.remove(index);
        //ToDoList.toDoList.saveData("toDoList.json");
    }


}
