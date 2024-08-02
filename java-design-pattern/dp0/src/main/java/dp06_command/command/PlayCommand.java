package dp06_command.command;

import dp06_command.receiver.AudioPlayer;

/**
 * @author tech@intellij.io
 */
public class PlayCommand implements Command {

    private final AudioPlayer player;

    public PlayCommand(AudioPlayer player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.play();
    }
}
