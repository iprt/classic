package dp02_factory.abstractFactory;

import dp02_factory.abstractFactory.components.Cpu;
import dp02_factory.abstractFactory.components.GraphicsCard;
import dp02_factory.abstractFactory.components.Motherboard;
import dp02_factory.abstractFactory.components.Power;
import dp02_factory.abstractFactory.components.detail.GoldenPower;
import dp02_factory.abstractFactory.components.detail.I7Cpu;
import dp02_factory.abstractFactory.components.detail.NvidiaGraphicsCard;
import dp02_factory.abstractFactory.components.detail.Z390Motherboard;

/**
 * The type Excellent computer.
 *
 * @author winterfell
 */
public class ExcellentComputer implements Computer {

    @Override
    public Cpu createCpu() {
        return new I7Cpu();
    }

    @Override
    public GraphicsCard createGraphicsCard() {
        return new NvidiaGraphicsCard();
    }

    @Override
    public Motherboard createMotherboard() {
        return new Z390Motherboard();
    }

    @Override
    public Power createPower() {
        return new GoldenPower();
    }

    @Override
    public void show() {

    }
}
