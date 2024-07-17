package todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TodoManager {
    private List<Todo> todos = new ArrayList<>();
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    public void completeTask(String todoDescription) {
        Todo todo = findTodo(todoDescription);
        if (todo != null) {
            todo.setCompleted(true);
        }
    }

    public void deleteTodo(String todoDescription) {
        Todo todo = findTodo(todoDescription);
        if (todo != null) {
            todos.remove(todo);
        }
    }

    public List<Todo> getTodo() {
        return todos;
    }

    public Todo findTodo(String description) {
        return todos.stream()
                .filter(todo -> todo.getDescription().equalsIgnoreCase(description))
                .findFirst()
                .orElse(null);
    }

    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            redoStack.push(command);
            command.undo();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            undoStack.push(command);
            command.execute();
        }
    }
}
