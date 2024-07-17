package todo;

public class AddTodoCommand implements Command {
    private TodoManager todoManager;
    private Todo todo;

    public AddTodoCommand(TodoManager todoManager, Todo task) {
        this.todoManager = todoManager;
        this.todo = task;
    }

    @Override
    public void execute() {
        todoManager.addTodo(todo);
    }

    @Override
    public void undo() {
        todoManager.getTodo().remove(todo);
    }
}
