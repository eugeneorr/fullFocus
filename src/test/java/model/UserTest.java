package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getUsername() throws Exception {
        String username = "Eugene";
        User user = new User(username);

        // Compare with initialized username
        assertEquals(username, user.getUsername());
    }

    @Test
    public void getGroups() throws Exception {
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group("groupName", new ArrayList<Task>()));
        User user = new User("Eugene", groups, new HashSet<String>());

        // Compare with initialized groups
        assertEquals(user.getGroups(), groups);
    }

    @Test
    public void addGroup() throws Exception {
        User user = new User("Eugene", new ArrayList<Group>(), new HashSet<String>());
        String newGroupName = "newGroupName";

        // Asserting for initial count of groups
        assertEquals(user.getGroups().size(), 0);

        // Adding new group
        user.addGroup("newGroupName");

        // Asserting for the existence of new group
        assertEquals(user.getGroups().size(), 1);

        // Asserting for groupName
        assertEquals(user.getGroups().get(0).getName(), newGroupName);
    }

    @Test
    public void addSharedUser() throws Exception {
        User user = new User("Eugene", new ArrayList<Group>(), new HashSet<String>());

        // Checking for initial count of shared users
        assertEquals(user.getSharedUsers().size(), 0);

        // Adding new shared username
        String newSharedUsername = "Shared";
        user.addSharedUser(newSharedUsername);

        // Asserting the existence of new shared user
        assertEquals(user.getSharedUsers().size(), 1);

        // Asserting for contains new shared user
        assertTrue(user.getSharedUsers().contains(newSharedUsername));
    }

    @Test
    public void getSharedUsers() throws Exception {
        HashSet<String> sharedUsers = new HashSet<>();
        sharedUsers.add("Ivan");
        User user = new User("Eugene", new ArrayList<Group>(), sharedUsers);

        // Asserting for getting shared users
        assertEquals(user.getSharedUsers(), sharedUsers);
    }

    @Test
    public void removeSharedUser() throws Exception {
        HashSet<String> sharedUsers = new HashSet<>();
        sharedUsers.add("Ivan");
        String usernameToDelete = "Marty";
        sharedUsers.add(usernameToDelete);
        User user = new User("Eugene", new ArrayList<Group>(), sharedUsers);

        // Asserting for initialized size
        assertEquals(user.getSharedUsers().size(), 2);

        // Removing shared user
        user.removeSharedUser(usernameToDelete);

        // Asserting new shared users count
        assertEquals(user.getSharedUsers().size(), 1);

        // Asserting for the lack of removed object
        assertFalse(user.getSharedUsers().contains(usernameToDelete));

    }

    @Test
    public void removeGroup() throws Exception {
        ArrayList<Group> groups = new ArrayList<>();
        Group groupToDelete = new Group("Movies");
        groups.add(groupToDelete);
        groups.add(new Group("Development"));
        User user = new User("Eugene", groups, new HashSet<String>());

        // Asserting for initialized size
        assertEquals(user.getGroups().size(), 2);

        // Removing shared user
        user.removeGroup(0);

        // Asserting new shared users count
        assertEquals(user.getGroups().size(), 1);

        // Asserting for the lack of removed object
        assertFalse(user.getGroups().contains(groupToDelete));
    }

}