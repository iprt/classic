# Graph

基于Java语言临接矩阵描述一张图

## 个人思考

一般对图的描述使用一个二维矩阵`int[][]`或者邻接表 `Map<Integer,List<Integer>>` 表示一下就可以了

真正描述图的时候只要把图的边描述好了就ok

**二维矩阵**

无权图

```text
    0      1      2      3      4      5
0   false  false  true   false  true   false

1   false  false  true   false  true   false

2   true   false  false  false  true   false

3   true   false  true   false  true   false

4   false  false  true   false  false  false

5   true   false  true   false  true   false

```

带权图

```text
    0      1      2      3      4      5
0   0.0    2.0    3.0    4.0    5.0    6.0

1   1.0    0.0    3.0    4.0    5.0    6.0

2   2.0    2.0    0.0    4.0    5.0    6.0

3   3.0    2.0    3.0    0.0    5.0    6.0

4   4.0    2.0    3.0    4.0    0.0    6.0

5   5.0    2.0    3.0    4.0    5.0    0.0

```

**邻接表**

无权图

```text
1： 2 3 4
2： 1 2
3： 1
```

带权图

```text
1： (2,1.0) (3,2.0) (4,0.5)
2： (1,2.1) 
3： (1,3.4)
```

但是问题也突出，现实中很多的图并不是用 `0 1 2 3 4 ...` 去表示点位的

可能就是描述一下 `A点` `B点` `xxx位置`

所以我对图的描述引入了点， `Point<T>`

基于点的描述，引入了边 `Edge<T,W>`

---

## 点和边的说明

### 1. `Vertex<T>` 的说明

**属性说明：**

- `sign` : 一个特殊的标识，标识这个点的唯一性
- `<T>`: 点为存储的信息，泛型标识
- `namespace` : 命名空间，我个人认为命名空间的作用是用来隔离不用的图，目前是这么设计的，具体的使用场景还没有用到

**方法说明:**

- getter / setter
- <font color=red>hashCode()</font>
- <font color=red>equals()</font>

**为什么要重写hashCode 和 equals ？**

利用好 Java 自生的数据类型 Map

**hashCode 的简单说明**

如果没有重写hashCode，对于一个对象而言，它的hash是存放在堆内存中的地址

重写hashCode的时候也要考虑到避免hash的冲突，可以参考 String 的 hashCode 的生成

### 2. `Edge<T,W>` 的说明

其中T，是复用了Point<T> 中的内容

**属性说明：**

- `from` 一条边的开始
- `to`   一条边的结束
- `namespace`: 命名空间，图的隔离
- `W` 权重的表示，权重之前要能做一些计算的操作

**方法说明:**

- getter / setter
- 构造方法
    - 基于 `from` `to` `W` `namespace` 构造出一条边
- `other(p):Point`: 一条边（除了自环边）一定又两个点，通过一条边的一个点，可以获取到这条边的另外一个点，
    - 这个方法很重要，保证了点边操作的灵活性
    - 因为要获取到另外一条边，所以对 from 和 to 有个对比的过程，反推一下，对于点的实现要重写hashCode和 equals
- <font color=red>hashCode() & equals()</font>

---

## 图的描述

一般情况下，我们都用邻接矩阵去描述图，那么思考的问题就是，怎么去定义一个邻接矩阵

简单的思考一下，如果是没有权重的图，就简单了

```text
Map<Point,Set<Point> g = new HashMap<>();
```

每个点对应点的集合就可以描述出来了

但是如果针对有权重的图，我一开始想到的是 `Map<Point,Set<PointAndWeight>>` 这样的 Map

- 其中 PointAndWeight 是 vertex + weight

似乎也是可以的，但是再想一下，vertex + weight 如果再加个point的话，不就是边的描述了么；虽然很多情况下，用的时候，只需要 vertex
+ weight，但是把边描述进去，就更优雅了
> 注意： vertex + weight 也是一种描述的方式

### 图中的方法的个人思考

描述有向图或者无向图，所以要表示一个 `direct` 属性

一个图，细化下来其实是树的一种扩展

树里面很重要的一点就是树的一个点拿出来其实还是一颗树，这里面的思想个人认为就是递归的思想了

要保证图在各种操作的时候可以递归，那么基于每个点就要能拿到周边的其他的点

我在描述邻接表的时候，每个点周边的其他的点没有用 `vertex + weight` ，用的是 `edge`，所以对于图中的存在的点，<font color=red>
很重要的一个方法就是获取这个点的相邻的点或者边</font>

- 这个方法就是邻边迭代器 `adj(Point):Set<Edge>`,基于点获取边的集合
- 有了邻边迭代器，才能舒舒服服的对图进行分析

其余的一些方法，就很简单了

- 所有的边 `allEdges()`
- 所有的点 `allPoints()`

还有一个就是怎么构建这样图 <font color=red>`connect(from,to,weight)`</font>

- 引申出来的就是 `addEdge(edge)`

### 图

**属性说明**

- `Map<Point<T>,Set<Edge<T,W> g`：邻接矩阵
- `direct` : 是否有向图
- `edges` : 所有的边
- `vertexs` : 所有的点
- `namespace` ： 命名空间

**方法说明**

- `namespace()`： 获取命名空间，给电和边用
- `show()`： 展示这张图
- `connect(p,p,w)`： 连接两个点
- `addEdge(e)`： 添加一条边
- `adj(p):Iterator<Edge>`： <font color=red>邻边迭代器</font>
