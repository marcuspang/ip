package commands;

import dukeegg.Storage;
import dukeegg.TaskList;
import dukeegg.Ui;
import exceptions.DukeException;
import exceptions.InvalidTaskIndexException;
import task.Task;

/**
 * Deletes the specified task.
 */
public class DeleteCommand extends Command {
    private final String[] inputStrings;

    /**
     * Constructs a delete command, which deletes a task based on the input strings.
     *
     * @param inputStrings The specified input strings.
     */
    public DeleteCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    /**
     * Deletes the task in the task list based on the input index.
     * <p>
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Tasks are displayed as 1-indexed, but they are stored as 0-indexed.
            int taskIndex = Integer.parseInt(inputStrings[1].trim()) - 1;

            Task task = tasks.removeTask(taskIndex);
            return ui.showRemoveTask(task, tasks.size());
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            throw new InvalidTaskIndexException();
        }
    }

    public boolean isExit() {
        return false;
    }
}
