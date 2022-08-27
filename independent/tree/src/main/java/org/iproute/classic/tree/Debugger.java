package org.iproute.classic.tree;

/**
 * Debugger
 *
 * @author zhuzhenjie
 * @since 2022/8/27
 */
public interface Debugger {

    // debug boolean 变量

    /**
     * Sets debug.
     *
     * @param debug the debug
     */
    void setDebug(boolean debug);

    /**
     * Gets debug.
     *
     * @return the debug
     */
    boolean getDebug();

    /**
     * Debug print.
     *
     * @param msg the msg
     */
    default void debugPrintln(String msg) {
        if (getDebug()) {
            System.out.println(msg);
        }
    }
}
