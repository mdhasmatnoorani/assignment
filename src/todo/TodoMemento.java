package todo;

import java.util.List;

public class TodoMemento {
    private final List<Todo> state;

    public TodoMemento(List<Todo> state) {
        this.state = state;
    }

    public List<Todo> getState() {
        return state;
    }
}
