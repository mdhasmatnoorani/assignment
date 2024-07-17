package todo;

public class CompleteTodoCommand implements Command {
    private TodoManager todoManager;
    private String todoDescription;
    private Todo todo;

    public CompleteTodoCommand(TodoManager todoManager, String todoDescription) {
        this.todoManager = todoManager;
        this.todoDescription = todoDescription;
    }

    @Override
    public void execute() {
        todo = todoManager.findTodo(todoDescription);
        if (todo != null) {
            todo.setCompleted(true);
        }
    }

    @Override
    public void undo() {
        if (todo != null) {
            todo.setCompleted(false);
        }
    }
}
