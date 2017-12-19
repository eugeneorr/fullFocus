package model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void getName() throws Exception {
        String groupName = "groupName";
        Group group = new Group(groupName, new ArrayList<Task>());

        // Compare with initialized group name;
        assertEquals(group.getName(), groupName);
    }

    @Test
    public void getTasks() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("First task."));
        Group group = new Group("groupName", tasks);

        // Compare with initialized tasks;
        assertEquals(group.getTasks(), tasks);
    }

    @Test
    public void addTask() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("First task."));
        tasks.add(new Task("Second task."));
        int oldTasksCount = tasks.size();
        Group group = new Group("groupName", tasks);
        Task newTask = new Task("Third task.");
        group.addTask(newTask);

        // Asserting for tasks count
        assertEquals(group.getTasks().size(), oldTasksCount + 1);

        // Asserting for contains new task
        assertTrue(group.getTasks().contains(newTask));

        // Compare last task with new task
        assertEquals(group.getTasks().get(group.getTasks().size() - 1), newTask);
    }

    @Test
    public void removeTask() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        Task firstTask = new Task("First task.");
        tasks.add(firstTask);
        tasks.add(new Task("Second task."));
        int oldTasksCount = tasks.size();
        Group group = new Group("groupName", tasks);

        // Remove first task
        group.removeTask(0);

        // Asserting for task count
        assertEquals(group.getTasks().size(), oldTasksCount - 1);

        // Asserting for the lack of removed task
        assertFalse(group.getTasks().contains(firstTask));
    }

    @Test
    public void updateTask() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("First task."));
        tasks.add(new Task("Second task."));
        Group group = new Group("groupName", tasks);

        // Update first task with new text
        String newText = "New text for first task";
        group.updateTask(0, newText);

        // Asserting for equal to new text
        assertEquals(group.getTasks().get(0).getText(), newText);
    }

    @Test
    public void equals() throws Exception {
        ArrayList<Task> groupTasks1 = new ArrayList<>();
        groupTasks1.add(new Task("task1_1"));
        groupTasks1.add(new Task("task1_2"));
        Group group1 = new Group("groupName1", groupTasks1);

        ArrayList<Task> groupTasks2 = new ArrayList<>();
        groupTasks2.add(new Task("task2_1"));
        groupTasks2.add(new Task("task2_2"));
        Group group2 = new Group("groupName2", groupTasks2);

        // Different texts
        assertFalse(group1.equals(group2));

        // Different class objects
        assertFalse(group1.equals("String"));

        // Compare with null
        assertFalse(group1.equals(null));

        // Create object group3 same as group1
        ArrayList<Task> groupTasks3 = new ArrayList<>();
        groupTasks3.add(new Task("task1_1"));
        groupTasks3.add(new Task("task1_2"));
        Group group3 = new Group("groupName1", groupTasks3);

        // Compare with similar groups
        assertTrue(group1.equals(group3));
    }

}