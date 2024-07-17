package todo;

import java.util.List;
import java.util.Stack;

public class Caretaker {
    private Stack<TodoMemento> undoStack = new Stack<>();
    private Stack<TodoMemento> redoStack = new Stack<>();

    public void saveState(List<Todo> state) {
        undoStack.push(new TodoMemento(state));
        redoStack.clear();
    }

    public TodoMemento undo() {
        if (!undoStack.isEmpty()) {
            TodoMemento memento = undoStack.pop();
            redoStack.push(memento);
            return memento;
        }
        return null;
    }

    public TodoMemento redo() {
        if (!redoStack.isEmpty()) {
            TodoMemento memento = redoStack.pop();
            undoStack.push(memento);
            return memento;
        }
        return null;
    }
}
