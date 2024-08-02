package other.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * FilterChain
 *
 * @author zhuzhenjie
 * @since 2021 /4/16
 */
public class FilterChain {

    private final List<Filter> filters = new ArrayList<>();
    private Target target;

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void execute(String request) {
        for (Filter filter : filters) {
            filter.execute(request);
        }
        target.execute(request);
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}