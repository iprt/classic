package org.iproute.action.state;

/**
 * 空闲状态
 *
 * @author Administrator
 */

/**
 * @author tech@intellij.io
 */
public class FreeState implements State {

    @Override
    public void handle() {
        System.out.println("房间空闲！！！没人住！");
    }

}
