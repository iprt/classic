package dp06_command.command;

import dp06_command.receiver.AudioPlayer;

/**
 * @author tech@intellij.io
 */
public class StopCommand implements Command {

    private final AudioPlayer player;

    public StopCommand(AudioPlayer player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.stop();
    }
}
