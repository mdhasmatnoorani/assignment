package todo;

public class DeleteTodoCommand implements Command {
    private TodoManager todoManager;
    private String todoDescription;
    private Todo todo;

    public DeleteTodoCommand(TodoManager todoManager, String todoDescription) {
        this.todoManager = todoManager;
        this.todoDescription = todoDescription;
    }

    @Override
    public void execute() {
        todo = todoManager.findTodo(todoDescription);
        if (todo != null) {
            todoManager.deleteTodo(todoDescription);
        }
    }

    @Override
    public void undo() {
        if (todo != null) {
            todoManager.addTodo(todo);
        }
    }
}
