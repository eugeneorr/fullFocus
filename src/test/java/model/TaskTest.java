package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void getText() throws Exception {
        String taskText = "taskText";
        Task task = new Task(taskText);

        // Compare with initialized text
        assertEquals(task.getText(), taskText);
    }

    @Test
    public void setText() throws Exception {
        String oldText = "oldTaskText";
        Task task = new Task(oldText);

        String newText = "newTaskText";
        task.setText(newText);

        // Compare with old text
        assertNotEquals(oldText, task.getText());

        // Compare with new text;
        assertEquals(newText, task.getText());
    }

    @Test
    public void equals() throws Exception {
        Task task1 = new Task("task#1");
        Task task2 = new Task("task#2");

        // Different texts
        assertFalse(task1.equals(task2));

        // Different class objects
        assertFalse(task1.equals("String"));

        // Compare with null
        assertFalse(task1.equals(null));

        // Set task1 text to task2
        task2.setText("task#1");

        // Compare with same texts
        assertTrue(task1.equals(task2));
    }

}