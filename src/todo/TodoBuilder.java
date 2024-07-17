package todo;

import java.time.LocalDate;

public class TodoBuilder {
    private String description;
    private LocalDate dueDate;

    public TodoBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TodoBuilder setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public Todo build() {
        return new Todo(description, dueDate);
    }
}
