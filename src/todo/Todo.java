package todo;

import java.time.LocalDate;

public class Todo {
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    public Todo(String description, LocalDate dueDate) {
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return description + " - " + (completed ? "Completed" : "Pending") + ", Due: " + dueDate;
    }
}
