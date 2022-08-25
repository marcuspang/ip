/**
 * Creates a new Todo
 */
public class TodoCommand extends Command {
    private final String[] inputStrings;

    TodoCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputStrings.length == 1) {
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }

        Todo todo = new Todo(inputStrings[1], false);
        tasks.addTask(todo);

        ui.showAddTask(todo, tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}
