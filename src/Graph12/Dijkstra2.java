package Graph12;

import Graph11.WeightedGraph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 用优先队列优化迪杰斯特拉算法
 * 时间复杂度：O(ElogE)
 */
public class Dijkstra2 {
    
    private WeightedGraph G;
    private int[] dis;
    private boolean[] confirm;
    
    private class Node implements Comparable<Node>{
        
        public int v, dist;

        @Override
        public int compareTo(Node another) {
            return dist - another.dist;
        }
    }
    
    public Dijkstra2(WeightedGraph G, int s) {
        
        this.G = G;
        G.validateVertex(s);

        dis = new int[G.V()];
        confirm = new boolean[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        pq.offer(new int[]{s, 0});
        
        while (!pq.isEmpty()) {

            int cur = pq.poll()[0];
            if (confirm[cur])
                continue;

            confirm[cur] = true;
            for (int w : G.adj(cur)) {
                if (confirm[w])
                    continue;
                
                int weight = G.getWeight(cur, w) + dis[cur];
                if (weight < dis[w]) {
                    dis[w] = weight;
                    pq.offer(new int[]{w, weight});
                }
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
        Dijkstra2 dijkstra = new Dijkstra2(g, 0);
        System.out.println(dijkstra.isConnected(4));
        for (int i = 0; i <= 4; i++) 
            System.out.println("0 到 " + i + "点的距离：" + dijkstra.distTo(i));
        
    }
}
