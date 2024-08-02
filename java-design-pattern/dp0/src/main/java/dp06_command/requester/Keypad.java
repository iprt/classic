package dp06_command.requester;

import dp06_command.command.Command;

/**
 * 封装的请求者
 *
 * @author tech@intellij.io
 */
public class Keypad {

    private final Command play;
    private final Command rewind;
    private final Command stop;

    public Keypad(Command play, Command rewind, Command stop) {
        this.play = play;
        this.rewind = rewind;
        this.stop = stop;
    }

    /**
     * Play.
     */
    public void play() {
        // 这里已经把原始的对象调用封装成了 类的调用
        play.execute();
    }

    /**
     * Rewind.
     */
    public void rewind() {
        rewind.execute();
    }

    /**
     * Stop.
     */
    public void stop() {
        stop.execute();
    }
}
