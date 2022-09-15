package commands;

import dukeegg.Storage;
import dukeegg.TaskList;
import exceptions.DukeException;
import exceptions.InvalidTaskIndexException;
import task.Task;
import ui.Ui;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private final String[] inputStrings;

    /**
     * Constructs an unmark command, which marks the task with the specified task index as not done.
     *
     * @param inputStrings The specified input strings.
     */
    public UnmarkCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    /**
     * Marks a task as not done.
     * <p>
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Tasks are displayed as 1-indexed, but they are stored as 0-indexed.
            int taskIndex = Integer.parseInt(inputStrings[1].trim()) - 1;

            Task task = tasks.getTask(taskIndex);
            task.unmarkTask();

            return ui.showUnmarkTask(task);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            throw new InvalidTaskIndexException();
        }
    }

    public boolean isExit() {
        return false;
    }
}
