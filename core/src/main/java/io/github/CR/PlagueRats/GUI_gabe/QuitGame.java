package io.github.CR.PlagueRats.GUI_gabe;

public class QuitGame implements Command {
    private final Receiver receiver;

    public QuitGame(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.quitGame();
    }
}
