package other.filter;

import other.filter.impl.AuthenticationFilter;
import other.filter.impl.DebugFilter;

/**
 * Main
 *
 * @author zhuzhenjie
 * @since 2021/4/16
 */
public class Main {

    public static void main(String[] args) {

        FilterManager filterManager = new FilterManager(new Target());

        filterManager.setFilter(new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        filterManager.filterRequest("hello world");
    }
}
