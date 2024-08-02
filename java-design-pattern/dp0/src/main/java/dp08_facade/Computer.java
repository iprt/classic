package dp08_facade;

/**
 * @author tech@intellij.io
 */
public class Computer {

    private final Cpu cpu;
    private final Memory memory;
    private final Screen screen;

    public Computer() {
        this.cpu = new Cpu();
        this.memory = new Memory();
        this.screen = new Screen();
    }

    public void start() {
        cpu.start();
        memory.start();
        screen.start();
    }

    public void stop() {
        screen.stop();
        memory.stop();
        cpu.stop();
    }
}
