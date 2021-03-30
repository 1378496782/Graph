package Graph12;

import Graph11.WeightedGraph;

import java.util.*;

/**
 * 求最短路径的长度并输出最短路径
 * 时间复杂度：O(ElogE)
 */
public class Dijkstra3 {
    
    private WeightedGraph G;
    private int[] dis;
    private boolean[] confirm;
    private int[] pre;
    private int s;
    
    private class Node implements Comparable<Node>{
        
        public int v, dist;

        @Override
        public int compareTo(Node another) {
            return dist - another.dist;
        }
    }
    
    public Dijkstra3(WeightedGraph G, int s) {
        
        this.G = G;
        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        confirm = new boolean[G.V()];
        pre = new int[G.V()];
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
                    pre[w] = cur;
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

    public Iterable<Integer> path(int t) {
        
        List<Integer> res = new ArrayList<>();
        if (!isConnected(t))
            return res;
        
        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        
        return res;
    }

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("E:\\笔记\\19.code\\玩转图论算法（源码）\\源码\\12-Shortest-Path\\04-Dijkstra-Algorithm-Optimized\\g.txt");
        System.out.println(g);
        Dijkstra3 dijkstra = new Dijkstra3(g, 0);
        System.out.println(dijkstra.isConnected(4));
        for (int i = 0; i <= 4; i++) {
            System.out.println("0 点到 " + i + " 点的代价：" + dijkstra.distTo(i));
            System.out.println("0 点到 " + i + " 点的路径：" + dijkstra.path(i));
        }
        
    }
}
