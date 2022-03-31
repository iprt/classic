package org.iproute.other.filter;

/**
 * Created by      IDEA
 *
 * @author :       zhuzhenjie
 * Date    :       2018-10-11
 * Time    :       17:15
 * Version :       1.0
 * Company :       Beijing Tepia (Wuhan R&D Center)
 **/
/**
 * @author winterfell
 */
public class DebugFilter implements Filter {
    @Override
    public void execute(String request) {
        System.out.println("request log : " + request);
    }
}
