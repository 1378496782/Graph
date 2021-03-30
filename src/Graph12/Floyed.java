package Graph12;

import Graph11.WeightedGraph;

import java.util.Arrays;

public class Floyed {
    
    private WeightedGraph G;
    private int[][] dis;
    private int V;
    private boolean hasNegativeCycle;   // 图中是否有负权环
    
    public Floyed(WeightedGraph G) {
        this.G = G;
        V = G.V();
        dis = new int[V][V];
        
        /* 初始化 */
        for (int i = 0 ; i < V ; i ++)
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        
        for (int v = 0 ; v < V ; v ++) {
            dis[v][v] = 0;
            for (int w : G.adj(v))
                dis[v][w] = G.getWeight(v, w);
        }
            
        /* 核心代码 */
        for (int t = 0 ; t < V ; t ++) 
            for (int v = 0; v < V; v++)
                for (int w = 0; w < V; w++)
                    if (dis[v][t] != Integer.MAX_VALUE && dis[t][w] != Integer.MAX_VALUE && dis[v][t] + dis[t][w] < dis[v][w])
                        dis[v][w] = dis[v][t] + dis[t][w];
                    
        for (int v = 0 ; v < V ; v ++)
            if (dis[v][v] < 0) {
                hasNegativeCycle = true;
                break;
            }
    }
    
    // 判断图中是否有负权环
    public boolean hasNegCycle() {
        return hasNegativeCycle;
    } 
    
    public boolean isConnectedTo(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return dis[v][w] != Integer.MAX_VALUE;
    }

    // 获得两点间的最短路径 
    public int distTo(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return dis[v][w];
    }

    @Override
    public String toString() {
        if (!hasNegativeCycle) {
            StringBuilder sb = new StringBuilder();
            for (int v = 0 ; v < V ; v ++) {
                for (int w = 0 ; w < V ; w ++)
                    sb.append(v + " 点到 " + w + " 点的最短距离为：" + distTo(v, w) + "\n");
                sb.append("\n");
            }
            return sb.toString();
        } else {
            return "Exist negative cycle.";
        } 
    }

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("E:\\笔记\\19.code\\玩转图论算法（源码）\\源码\\12-Shortest-Path\\11-Floyed-Algorithm\\g.txt");
        Floyed floyed = new Floyed(g);
        System.out.println(floyed);
    }
}
