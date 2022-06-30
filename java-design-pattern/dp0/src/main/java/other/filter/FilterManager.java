package other.filter;

/**
 * FilterManagert
 *
 * @author zhuzhenjie
 * @since 2021/4/16
 */
public class FilterManager {
    FilterChain filterChain;

    public FilterManager(Target target) {
        this.filterChain = new FilterChain();
        filterChain.setTarget(target);
    }

    public void setFilter(Filter filter) {
        filterChain.addFilter(filter);
    }

    public void filterRequest(String request) {
        filterChain.execute(request);
    }
}
