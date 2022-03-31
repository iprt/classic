package dp02_factory.abstractFactory.components.detail;

import dp02_factory.abstractFactory.components.Power;

/**
 * @author winterfell
 */
public class SilveryPower implements Power {

    @Override
    public String powerType() {
        return "silvery";
    }
}
