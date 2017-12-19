package model;

import java.util.ArrayList;

public class Group {

    private String name;
    private ArrayList<Task> tasks;

    public Group(String name, ArrayList<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    public Group(String name) {
        this.name = name;
        tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        if (task == null) { return; }
        tasks.add(task);
        //ToDoList.toDoList.saveData("toDoList.json");
    }

    public void removeTask(int index) {
        tasks.remove(index);
        //ToDoList.toDoList.saveData("toDoList.json");
    }

    public void updateTask(int index, String newText) {
        tasks.get(index).setText(newText);
        //ToDoList.toDoList.saveData("toDoList.json");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Group group = (Group) obj;
        return group.name.equals(this.name) && group.tasks.equals(this.tasks);
    }

}
