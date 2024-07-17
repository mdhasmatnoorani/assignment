package todo;

public interface Command {
    void execute();
    void undo();
}
