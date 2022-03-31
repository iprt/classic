package other.filter;

/**
 * Filter
 *
 * @author zhuzhenjie
 * @since 2021 /4/16
 */
public interface Filter {

    /**
     * Execute.
     *
     * @param request the request
     */
    void execute(String request);
}