package dp06_command.receiver;

/**
 * 真正的对象执行的方法
 *
 * @author tech@intellij.io
 */
public class AudioPlayer {

    private static AudioPlayer instance = null;

    private AudioPlayer() {
    }

    public static AudioPlayer getInstance() {
        if (null == instance) {
            instance = new AudioPlayer();
        }
        return instance;
    }

    public void play() {
        System.out.println("播放……");
    }

    public void rewind() {
        System.out.println("倒带……");
    }

    public void stop() {
        System.out.println("停止……");
    }
}
