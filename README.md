# play-with-graph
#  第一个问题关于图的表示
![photo](1.png)

# 图的表示
	
![photo](2.png)
## 社交网路
![photo](3.png)


![photo](4.png)

## 图的分类算法
无向无权图 
无向有权图 
有向无权图 
有向有权图


# 图的基本概念
## 	无向无权图

![photo](5.png)

![photo](6.png)

没有自环边，没有平行边， 称为简单图

![photo](7.png)

### 联通分量 
	一个图的所有节占不一定全部相连 
	一个图可能有多个联通分量


###  无环图 
	树是一种无环图。无环图不一定是树
	联通的无环图就是树


![photo](8.png)

## 包含所有顶占边数V·1， 一定是联通图的生成树吗？

![photo](9.png)


	不是！

###  一个图一定有生成树吗？

	没有

![photo](10.png)

### 一个顶点的度 

![photo](11.png)

##  邻接矩阵

![photo](12.png)

[实现code](https://github.com/HuichuanLI/play-with-graph-algorithme/blob/master/src/AdjMatrix.java)

复杂度

![photo](13.png)

	可以优化空间复杂度 O(V2)
	如果一个图有3000个节点
	空间：5999vs3000^2约1000万 
	求相邻顶点．degree(v)vs3000

## 稀疏图和稠密图

![photo](14.png)
![photo](15.png)
稠密图
![photo](16.png)
平均每个度比较最大的度
## 邻接表
![photo](17.png)
[实现code](https://github.com/HuichuanLI/play-with-graph-algorithme/blob/master/src/AdjList.java)

## 空间复杂度
	空间复杂度，O(V+E)
	如果是 树的话 O（2E+1）= O(E)
	如果是完全图的话 O（v*(V-1)/2 + E）= O(E)
	但是 不能写O(E) 如果不联通

## 时间复杂度：
建图：O(E*V)  和邻接矩阵 相差了v
	
看两点是否相邻 ：O(degree(v))  原来是O(1)

求一个点的相邻节点 : O(degree(v)) 只需要遍历所有相邻节点个数
	
快速查重 
	
快速查看两点是否相邻
	
不要使用链表 
	
hash 表 HashSet O(1)
	
红黑树 TreeSet O(logv)
	
因为红黑树 考虑到元素之间的顺序性，为了保证TreeSet，为了使得结果相对可视化。

红黑树相对于hash表内存空间相对于少。

时间复杂度虽然会增加 o(1) < o(logn) < o(n) 比如 100w 1< 20 < 1000000

算法优化 使用红黑树而对于java使用[TreeSet](https://github.com/HuichuanLI/play-with-graph-algorithme/blob/master/src/AdjSet.java)


## 算法复杂度
![photo](18.png)

我们可以看出来邻接表，在其他方面都是优于其他的表示方法。
所以我们接下来，都是使用邻接表。

	






