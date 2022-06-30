package org.iproute.action.Mediator;

/**
 * @author winterfell
 */
public class Development implements Department {

    private Mediator m;

    public Development(Mediator m) {
        super();
        this.m = m;
        m.register("development", this);
    }

    @Override
    public void selfAction() {
        System.out.println("研发");
    }

    @Override
    public void outAction() {
        System.out.println("缺钱");
    }

}
