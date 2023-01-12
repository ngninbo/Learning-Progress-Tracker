package tracker.command;

public class TrackerBackCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Enter 'exit' to exit the program.");
    }
}
