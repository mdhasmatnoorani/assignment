package todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TodoListManager {
    private TodoManager taskManager = new TodoManager();

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Choose an option: Add, Complete, Delete, View, Undo, Redo, Exit");
                String choice = scanner.nextLine();

                try {
                    switch (choice.toLowerCase()) {
                        case "add":
                            System.out.println("Enter task description and due date (Eg: \"Buy groceries, Due: 2023-09-20\")");
                            String taskDetails = scanner.nextLine();
                            addTask(taskDetails);
                            break;
                        case "complete":
                            System.out.println("Enter task description to mark as completed:");
                            String completeTask = scanner.nextLine();
                            completeTask(completeTask);
                            break;
                        case "delete":
                            System.out.println("Enter task description to delete:");
                            String deleteTask = scanner.nextLine();
                            deleteTask(deleteTask);
                            break;
                        case "view":
                            System.out.println("Enter filter (Show all, Show completed, Show pending):");
                            String filter = scanner.nextLine();
                            viewTasks(filter);
                            break;
                        case "undo":
                            undo();
                            break;
                        case "redo":
                            redo();
                            break;
                        case "exit":
                        	System.out.println("Exit: Todo App");
                            return;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void addTask(String taskDetails) {
        try {
            String[] parts = taskDetails.split(", Due: ");
            String description = parts[0].trim();
            String dueDateStr = parts[1].trim();
            // Parse due date
            LocalDate dueDate = LocalDate.parse(dueDateStr);
            Todo task = new TodoBuilder().setDescription(description).setDueDate(dueDate).build();
            taskManager.executeCommand(new AddTodoCommand(taskManager, task));
            System.out.println("Todo added successfully ");
        } catch (Exception e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    public void completeTask(String taskDescription) {
        taskManager.executeCommand(new CompleteTodoCommand(taskManager, taskDescription));
        	System.out.println("Todo marked as completed");
    }

    public void deleteTask(String taskDescription) {
        taskManager.executeCommand(new DeleteTodoCommand(taskManager, taskDescription));
    }

    public void viewTasks(String filter) {
        List<Todo> tasks = taskManager.getTodo();
        switch (filter.toLowerCase()) {
        	case "show all":
        		if(tasks.isEmpty()) {
        			System.out.println("No tasks available. Please add a task to continue...");
        		}
        		tasks.forEach(System.out::println);
        		break;
            case "show completed":
            	if(tasks.isEmpty()) {
        			System.out.println("No tasks available. Please add a task to continue...");
        		}
                tasks.stream().filter(Todo::isCompleted).forEach(System.out::println);
                break;
            case "show pending":
            	if(tasks.isEmpty()) {
        			System.out.println("No tasks available. Please add a task to continue...");
        		}
                tasks.stream().filter(task -> !task.isCompleted()).forEach(System.out::println);
                break;
            default:
            	System.out.println("Invalid option. Please try again.");
            	break;
        }
    }

    public void undo() {
        taskManager.undo();
    }

    public void redo() {
        taskManager.redo();
    }

    public static void main(String[] args) {
        TodoListManager manager = new TodoListManager();
        manager.start();
    }
}
