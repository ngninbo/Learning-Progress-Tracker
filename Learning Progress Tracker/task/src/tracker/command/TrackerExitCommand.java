package tracker.command;

public class TrackerExitCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Bye!");
    }
}
