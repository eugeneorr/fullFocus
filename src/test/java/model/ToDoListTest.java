package model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ToDoListTest {

    @Test
    public void ToDoList() throws Exception {
        ToDoList toDoList = new ToDoList("toDoList.json");
        // Asserting for successful parsing data file
        assertNotNull(toDoList.getUsers());
    }

    @Test
    public void getUsers() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        User user = new User("Eugene");
        users.add(user);

        ToDoList toDoList = new ToDoList(users);

        // Asserting for equal taken users with initialized;
        assertEquals(toDoList.getUsers(), users);
    }

    @Test
    public void getUser() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        String username = "Eugene";
        User user = new User(username);
        users.add(user);

        ToDoList toDoList = new ToDoList(users);

        // Adding new user
        toDoList.addUser(user);

        // Asserting for equals taken user to added
        assertEquals(toDoList.getUser(username), user);
    }

    @Test
    public void addUser() throws Exception {
        ToDoList toDoList = new ToDoList(new ArrayList<User>());

        // Checking for initialized users count
        assertEquals(toDoList.getUsers().size(), 0);

        String username = "Eugene";
        User user = new User(username);
        toDoList.addUser(user);

        // Checking for new users count
        assertEquals(toDoList.getUsers().size(), 1);

        // Checking for contains new user
        assertTrue(toDoList.getUsers().contains(user));

    }

    @Test
    public void saveData() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        String username = "Eugene";
        User user = new User(username);
        users.add(user);
        ToDoList toDoList = new ToDoList(users);

        String testFilename = "testToDoList.json";

        // Save created data to test file
        toDoList.saveData(testFilename);

        // Parse saved data
        toDoList = new ToDoList(testFilename);

        // Asserting for contains saved users
        assertEquals(toDoList.getUser(username).getUsername(), username);
    }

}