package com.code.review.aaa.google;

import java.util.Arrays;

/**
 * 题意：给出一棵树，求出树上每个节点到其他节点的距离总和。

题解：每个节点保存两个值，一个是其子树的节点个数（包括自身节点也要计数）nodesum[ ]，一个是其子树各点到它的距离 dp[ ]，
那么我们假设根节点为 u ，其仅有一个儿子 v ， u 到 v 的距离为 1 ，而 v 有若干儿子节点，那么 dp[v] 表示 v 的子树各点到 v 
的距离和，那么各个节点到达 u 的距离便可以这样计算： dp[u] = dp[v] + nodesum[ v ] *1; （式子的理解，v 的一个儿子节点为 f，
那么 f 到达 u 的距离为  (sum[ f ->v] + sum [v- > u])*1 ，dp[v] 包含了 sum[f->v]*1，所以也就是式子的分配式推广到
各个子节点计算出来的和）。我们已经知道了各个节点到达根节点的距离和，那么从根节点开始递推下来可以得到各个点的距离和。
另开一个数组表示每个节点的到其他节点的距离和，那么对于根节点u来说， dissum[u] = dp[u]。以 u 的儿子 v 为例， v 
的子节点到 v 不必经过 v->u 这条路径，因此 dissum[u] 多了 nodesum[v] * 1，但是对于不是 v 的子节点的节点，只到达了 u ，
因此要到达 v 必须多走 u->v 这条路径，因此 dissum[u] 少了 ( N - nodesum[v] ) * 1) ,
所以 dissum[v] = dissum[u] - nodesum[v] * 1 + (N - nodesum[v] ) * 1，按照这个方法递推下去就可以得到各个点的距离和。


The meaning of the question: give a tree and find the sum of the distance from each node on the tree to other nodes.

Problem: Each node holds two values, one is the number of nodes of its subtree (including its own node also counts)
 nodesum[ ], one is the distance from its subtree to its point dp[ ], then we assume the root The node is u, which
  has only one son v, the distance from u to v is 1, and v has several son nodes, then dp[v] represents the 
  distance from each point of v to the v of the subtree, then each node reaches u The distance can be calculated 
  as follows: dp[u] = dp[v] + nodesum[ v ] *1; (For the understanding of the expression, a son node of v is f,
   then the distance of f to u is (sum[ f -> v] + sum [v- > u])*1 , dp[v] contains sum[f->v]*1, so that the 
   assignment of the expression is generalized to the sum calculated by each sub-node). We already know the 
   distance between each node and the root node, then we can get the distance sum of each point by recursing 
   from the root node. Open another array to represent the distance and distance of each node to other nodes, 
   then for the root node u, dissum[u] = dp[u]. Taking u's son v as an example, the child nodes of v do not have 
   to go through the v->u path, so dissum[u] has more nodesum[v] * 1, but for nodes that are not child nodes of v, 
   only reach u, so to reach v you must take u->v this path, 
   so dissum[u] is less (N - nodesum[v] ) * 1) , so dissum[v] = dissum[u] - nodesum[ v] * 1 + (N - nodesum[v] ) * 1,
    according to this method, you can get the dist

 *  
 *
 */
public class LC834SumOfDistanceInTree {
  
    private int tot = 0;
     private Edge[] edge;
     private int[] head;
  private int[] dp;
   private int[] nodesum;
    private int[] dissum;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
       edge = new Edge[2 * N + 5];
       head = new int[N + 5];
      dp = new int[N + 5];
       nodesum = new int[N  + 5];
       dissum = new int[N];
       Arrays.fill(head,-1);
       for (int i = 0;i < edges.length;i++){
          int u = edges[i][0];
           int v = edges[i][1];
           addedge(u,v);
           addedge(v,u);
      }
       dfs1(0,0);
       dissum[0] = dp[0];
       dfs2(0,0,N);
       return dissum;
   }

  public void addedge(int u,int v){
       edge[tot] = new Edge();
      edge[tot].u = u;
       edge[tot].v = v;
       edge[tot].next = head[u];
      head[u] = tot++;
   }

  public void dfs1(int u,int fa){
      dp[u] = 0;
       nodesum[u] = 1;
       for (int i = head[u];i != -1;i = edge[i].next){
           int v = edge[i].v;
           if (v == fa)    continue;
           dfs1(v,u);
           dp[u] += dp[v] + nodesum[v];
           nodesum[u] += nodesum[v];
       }
   }

   public void dfs2(int u,int fa,int sum){
       for (int i = head[u];i != -1;i = edge[i].next){
           int v = edge[i].v;
           if (v == fa)    continue;
          dissum[v] = dissum[u] - nodesum[v] + sum - nodesum[v];
           dfs2(v,u,sum);
      }
   }
   class Edge{
      int u,v,next;
   }
}
 
