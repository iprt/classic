package dp06_command;

import dp06_command.command.PlayCommand;
import dp06_command.command.RewindCommand;
import dp06_command.command.StopCommand;
import dp06_command.receiver.AudioPlayer;
import dp06_command.requester.Keypad;

/**
 * @author tech@intellij.io
 */
public class Main {

    public static void main(String[] args) {

        AudioPlayer player = AudioPlayer.getInstance();

        Keypad keypad = new Keypad(
                new PlayCommand(player),
                new RewindCommand(player),
                new StopCommand(player)
        );


        keypad.play();

        keypad.rewind();

        keypad.stop();

    }

}
