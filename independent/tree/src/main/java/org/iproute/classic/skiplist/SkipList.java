package org.iproute.classic.skiplist;

import java.util.Random;
import java.util.Stack;

/**
 * SkipList
 *
 * @author tech@intellij.io
 */
public class SkipList<T> {
    // 头节点
    SkipNode<T> HEAD;

    // 当前跳表的索引层数
    int highLevel;

    // 随机
    final Random random;

    // 最大层
    final int MAX_LEVEL = 32;

    public SkipList() {
        this.random = new Random();
        this.HEAD = new SkipNode<>(Integer.MIN_VALUE, null);
        highLevel = 0;
    }

    public SkipNode<T> search(int key) {
        SkipNode<T> node = HEAD;
        while (node != null) {
            if (node.key == key) {
                return node;
            } else if (node.right == null) {
                // 右节点没有值 只能下降寻找 优先判断
                node = node.down;
            } else if (node.right.key > key) {
                // 右节点的值大于 需要寻找的值
                // 下降寻找
                node = node.down;
            } else if (node.right.key < key) {
                // 右节点的值 小于 需要寻找的值
                // 向右寻找
                node = node.right;
            }
        }
        return null;
    }

    /**
     * 删除操作你需要谨记以下几点，删除也是从head开始
     * <p>
     * 1. 删除当前节点和这个节点的前后节点都有关系
     * <p>
     * 2. 删除当前层节点之后，下一层该key的节点也要删除，一直删除到最底层
     */
    public void delete(int key) {
        // 删除不需要考虑层数
        SkipNode<T> team = HEAD;

        while (team != null) {
            if (team.right == null) { // 右侧没有了，说明这一层找到，没有只能下降
                team = team.down;
            } else if (team.right.key == key) { // 找到节点，右侧即为待删除节点
                // 删除右侧节点
                team.right = team.right.right;
                team = team.down;// 向下继续查找删除
            } else if (team.right.key > key) { // 右侧已经不可能了，向下
                team = team.down;
            } else { // 节点还在右侧
                team = team.right;
            }
        }

    }

    /**
     * 插入需要考虑是否插入索引，插入几层
     * <p>
     * 但我们使用随机化的方法去判断是否向上层插入索引
     * <p>
     * 产生一个[0-1]的随机数如果小于0.5就向上插入索引，插入完毕后再次使用随机数判断是否向上插入索引
     */
    public void add(SkipNode<T> node) {
        int key = node.key;
        SkipNode<T> findNode = search(key);

        if (findNode != null)// 如果存在这个key的节点
        {
            findNode.value = node.value;
            return;
        }
        Stack<SkipNode<T>> stack = new Stack<>();// 存储向下的节点，这些节点可能在右侧插入节点

        SkipNode<T> team = HEAD;// 查找待插入的节点   找到最底层的哪个节点。

        while (team != null) {
            if (team.right == null) { // 右侧没有了，只能下降
                stack.add(team); // 将曾经向下的节点记录一下
                team = team.down;
            } else if (team.right.key > key) { // 需要下降去寻找

                team = team.down; ////将曾经向下的节点记录一下
            } else {
                team = team.right;
            }
        }

        int level = 1;// 当前层数，从第一层添加(第一层必须添加，先添加再判断)
        SkipNode<T> downNode = null;// 保持前驱节点(即down的指向，初始为null)

        while (!stack.isEmpty()) {
            // 在该层插入node
            team = stack.pop();// 抛出待插入的左侧节点
            SkipNode<T> nodeTeam = new SkipNode<>(node.key, node.value);// 节点需要重新创建
            nodeTeam.down = downNode; // 处理竖方向
            downNode = nodeTeam; // 标记新的节点下次使用

            if (team.right == null) {// 右侧为null 说明插入在末尾
                team.right = nodeTeam;
            }

            // 水平方向处理
            else {// 右侧还有节点，插入在两者之间
                nodeTeam.right = team.right;
                team.right = nodeTeam;
            }

            // 考虑是否需要向上
            if (level > MAX_LEVEL)// 已经到达最高级的节点啦
                break;

            double num = random.nextDouble();//[0-1]随机数

            if (num > 0.5)// 运气不好结束
                break;

            level++;

            if (level > highLevel) { // 比当前最大高度要高但是依然在允许范围内 需要改变head节点
                highLevel = level;
                // 需要创建一个新的节点
                SkipNode<T> highHeadNode = new SkipNode<>(Integer.MIN_VALUE, null);
                highHeadNode.down = HEAD;
                HEAD = highHeadNode;// 改变head
                stack.add(HEAD);// 下次抛出head
            }

        }

    }

    public void printList() {
        SkipNode<T> teamNode = HEAD;
        int index = 1;
        SkipNode<T> last = teamNode;
        while (last.down != null) {
            last = last.down;
        }
        while (teamNode != null) {
            SkipNode<T> enumNode = teamNode.right;
            SkipNode<T> enumLast = last.right;
            System.out.printf("%-8s", "head->");
            while (enumLast != null && enumNode != null) {
                if (enumLast.key == enumNode.key) {
                    System.out.printf("%-5s", enumLast.key + "->");
                    enumLast = enumLast.right;
                    enumNode = enumNode.right;
                } else {
                    enumLast = enumLast.right;
                    System.out.printf("%-5s", "");
                }

            }
            teamNode = teamNode.down;
            index++;
            System.out.println();
        }
    }

}
