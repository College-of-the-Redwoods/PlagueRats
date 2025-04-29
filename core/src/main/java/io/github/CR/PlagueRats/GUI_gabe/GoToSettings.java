package io.github.CR.PlagueRats.GUI_gabe;

public class GoToSettings implements Command {
    private final Receiver receiver;

    public GoToSettings(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.settings();
    }
}
