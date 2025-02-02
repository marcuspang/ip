package commands;

import dukeegg.Storage;
import dukeegg.TaskList;
import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import task.TaskType;
import task.Todo;
import ui.Ui;

/**
 * Creates a new Todo.
 */
public class TodoCommand extends Command {
    public static final String SYNTAX = "todo DESCRIPTION";

    private final String[] inputStrings;

    /**
     * Constructs a todo command, which creates a new todo based on the input strings.
     *
     * @param inputStrings The specified input strings.
     */
    public TodoCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    /**
     * Creates a new todo.
     * <p>
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputStrings.length == 1) {
            throw new EmptyDescriptionException(TaskType.T);
        }

        Todo todo = new Todo(inputStrings[1].trim(), false);
        tasks.addTask(todo);

        return ui.showAddTask(todo, tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}
