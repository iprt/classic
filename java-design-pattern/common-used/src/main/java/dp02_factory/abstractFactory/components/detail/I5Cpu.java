package dp02_factory.abstractFactory.components.detail;

import dp02_factory.abstractFactory.components.Cpu;

/**
 * @author tech@intellij.io
 */
public class I5Cpu implements Cpu {
    @Override
    public String cpuName() {
        return "I5";
    }
}
