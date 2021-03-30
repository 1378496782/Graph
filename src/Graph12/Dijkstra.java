package Graph12;

import Graph11.WeightedGraph;

import java.util.Arrays;

/**
 * 时间复杂度：O(V*V)
 */
public class Dijkstra {
    
    private WeightedGraph G;
    private int[] dis;
    private boolean[] confirm;
    
    public Dijkstra(WeightedGraph G, int s) {
        
        this.G = G;
        G.validateVertex(s);

        dis = new int[G.V()];
        confirm = new boolean[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;

        while (true) {
            
            int curDist = Integer.MAX_VALUE, cur = -1;
            for (int v = 0 ; v < G.V() ; v ++) {
                if (!confirm[v] && dis[v] < curDist) {
                    curDist = dis[v];
                    cur = v;
                }
            }
            
            if (cur == -1)
                break;

            confirm[cur] = true;
            for (int w : G.adj(cur)) {
                if (confirm[w])
                    continue;
                
                int weight = G.getWeight(cur, w) + dis[cur];
                if (weight < dis[w])
                    dis[w] = weight;
            }
        }
    }
    
    public boolean isConnected(int v) {
        G.validateVertex(v);
        return confirm[v];
    }

    public int distTo(int v) {
        G.validateVertex(v);
        return dis[v];
    }

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("E:\\笔记\\19.code\\玩转图论算法（源码）\\源码\\12-Shortest-Path\\04-Dijkstra-Algorithm-Optimized\\g.txt");
        System.out.println(g);
        Dijkstra dijkstra = new Dijkstra(g, 0);
        System.out.println(dijkstra.isConnected(4));
        for (int i = 0; i <= 4; i++) 
            System.out.println("0 到 " + i + "点的距离：" + dijkstra.distTo(i));
        
    }
}
