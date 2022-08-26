package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import task.Task;

/**
 * Marks a task as not done
 */
public class UnmarkCommand extends Command {
    private final String[] inputStrings;

    public UnmarkCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Tasks are displayed as 1-indexed, but they are stored as 0-indexed
            // Hence, we need to account for this offset here
            int taskIndex = Integer.parseInt(inputStrings[1].trim()) - 1;

            Task task = tasks.getTask(taskIndex);
            task.unmarkTask();

            ui.showUnmarkTask(task);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            throw new DukeException("     ☹ OOPS!!! The index specified is invalid.");
        }
    }

    public boolean isExit() {
        return false;
    }

}
