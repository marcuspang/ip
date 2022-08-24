import java.util.Scanner;

/**
 * Main class for Duke Chatbot
 */
public class Duke {
    // Constants for messages to be printed to the console
    public static final String LONG_LINE = "    ____________________________________________________________\n";
    public static final String GREETING_MESSAGE = LONG_LINE
            + "     Hello! I'm Duke\n"
            + "     What can I do for you?\n"
            + LONG_LINE;
    public static final String GOODBYE_MESSAGE = LONG_LINE
            + "     Bye. Hope to see you again soon!\n"
            + LONG_LINE;

    private final Storage taskStorage = new Storage();

    public static void main(String[] args) {
        // Initialize Duke chatbot
        Duke chatBot = new Duke();

        // Greeting message is always printed
        System.out.println(GREETING_MESSAGE);

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        // Continuously executes user's command until "bye" is entered
        while (!command.equals("bye")) {
            chatBot.executeCommand(command);
            command = in.nextLine();
        }
        in.close();
        chatBot.taskStorage.saveTasks();

        // Goodbye message is always printed
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Executes user's command and prints the corresponding response message to the
     * console
     *
     * @param command The specified command.
     */
    public void executeCommand(String command) {
        // Used to generate the response message after executing commands
        StringBuilder sb = new StringBuilder();

        // Limit of 2 is used to avoid splitting the second argument in command
        String[] inputStrings = command.split(" ", 2);
        try {
            switch (inputStrings[0]) {
            case "list": {
                // Return all tasks
                sb.append(LONG_LINE).append("     Here are the tasks in your list:\n");
                for (int i = 0; i < taskStorage.size(); i++) {
                    sb.append("     ").append(i + 1).append(".").append(taskStorage.getTask(i)).append("\n");
                }
                sb.append(LONG_LINE);
                break;
            }
            case "mark": {
                // Tasks are displayed as 1-indexed, but they are stored as 0-indexed
                // Hence, we need to account for this offset here
                int taskIndex = Integer.parseInt(inputStrings[1]) - 1;
                Task task = this.taskStorage.getTask(taskIndex);
                task.markTask();

                sb.append(LONG_LINE)
                        .append("     Nice! I've marked this task as done:\n")
                        .append("       ").append(task).append("\n")
                        .append(LONG_LINE);
                break;
            }
            case "unmark": {
                // Tasks are displayed as 1-indexed, but they are stored as 0-indexed
                // Hence, we need to account for this offset here
                int taskIndex = Integer.parseInt(inputStrings[1]) - 1;
                Task task = this.taskStorage.getTask(taskIndex);
                task.unmarkTask();

                sb.append(LONG_LINE)
                        .append("     OK, I've marked this task as not done yet:\n")
                        .append("       ").append(task).append("\n")
                        .append(LONG_LINE);
                break;
            }
            case "todo": {
                if (inputStrings.length == 1) {
                    throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.\n");
                }
                Todo todo = new Todo(inputStrings[1], false);

                sb.append(this.addTask(todo));
                break;
            }
            case "deadline": {
                if (inputStrings.length == 1) {
                    throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.\n");
                }
                String[] deadlineStrings = inputStrings[1].split(" /by ", 2);
                if (deadlineStrings.length == 1 || deadlineStrings[1].equals("")) {
                    throw new DukeException("     ☹ OOPS!!! The date/time of a deadline cannot be empty.\n");
                }
                Deadline deadline = new Deadline(deadlineStrings[0], false, deadlineStrings[1]);

                sb.append(this.addTask(deadline));
                break;
            }
            case "event": {
                if (inputStrings.length == 1) {
                    throw new DukeException("     ☹ OOPS!!! The description of an event cannot be empty.\n");
                }
                String[] eventStrings = inputStrings[1].split(" /at ", 2);
                if (eventStrings.length == 1 || eventStrings[1].equals("")) {
                    throw new DukeException("     ☹ OOPS!!! The date/time of an event cannot be empty.\n");
                }
                Event event = new Event(eventStrings[0], false, eventStrings[1]);

                sb.append(this.addTask(event));
                break;
            }
            case "delete": {
                // Tasks are displayed as 1-indexed, but they are stored as 0-indexed
                // Hence, we need to account for this offset here
                int taskIndex = Integer.parseInt(inputStrings[1]) - 1;

                sb.append(this.removeTask(taskIndex));
                break;
            }
            default: {
                throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            // Catches exceptions thrown when parsing integers in mark/unmark commands or
            // when accessing invalid taskList index
            sb.append(LONG_LINE).append("     ☹ OOPS!!! The index specified is invalid.\n").append(LONG_LINE);
        } catch (DukeException exception) {
            // All DukeExceptions will be printed
            sb.append(LONG_LINE).append(exception).append(LONG_LINE);
        }

        // Print out response after executing command
        System.out.println(sb);
    }

    /**
     * Add task to the current task list, and returns the formatted response message to be printed to the
     * console
     *
     * @param task The specified task.
     * @return formatted message after adding a task.
     */
    public String addTask(Task task) {
        this.taskStorage.addTask(task);
        return LONG_LINE
                + "     Got it. I've added this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + this.taskStorage.size() + " task(s) in the list.\n"
                + LONG_LINE;
    }

    /**
     * Remove task from current task list, and returns the formatted response message to be printed to the
     * console
     *
     * @param taskIndex The specified task index.
     * @return formatted message after removing a task.
     */
    public String removeTask(int taskIndex) {
        Task task = this.taskStorage.removeTask(taskIndex);
        return LONG_LINE
                + "     Noted. I've removed this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + this.taskStorage.size() + " task(s) in the list.\n"
                + LONG_LINE;
    }

}
