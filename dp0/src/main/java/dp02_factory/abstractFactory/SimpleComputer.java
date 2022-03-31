package dp02_factory.abstractFactory;

import dp02_factory.abstractFactory.components.Cpu;
import dp02_factory.abstractFactory.components.GraphicsCard;
import dp02_factory.abstractFactory.components.Motherboard;
import dp02_factory.abstractFactory.components.Power;
import dp02_factory.abstractFactory.components.detail.AmdGraphicsCard;
import dp02_factory.abstractFactory.components.detail.B365Motherboard;
import dp02_factory.abstractFactory.components.detail.I5Cpu;
import dp02_factory.abstractFactory.components.detail.SilveryPower;

/**
 * @author winterfell
 */
public class SimpleComputer implements Computer {
    @Override
    public Cpu createCpu() {
        return new I5Cpu();
    }

    @Override
    public GraphicsCard createGraphicsCard() {
        return new AmdGraphicsCard();
    }

    @Override
    public Motherboard createMotherboard() {
        return new B365Motherboard();
    }

    @Override
    public Power createPower() {
        return new SilveryPower();
    }

    @Override
    public void show() {

    }
}
