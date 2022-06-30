package org.iproute.segment;

/**
 * @author zhuzhenjie
 **/
public class Test {

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        // 查询区间之和
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> {
            return a + b;
        });

//        System.out.println(segmentTree.query(0,2));

    }
}
