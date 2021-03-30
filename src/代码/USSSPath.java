package 代码;

import java.util.*;

/**
 * Unweighted Single Source Shortest Path
 * 无权图的单源最短路径
 */
public class USSSPath {

    private Graph G;
    private boolean[] visited;
    private int s;
    private int[] pre;
    private int[] dis;
    
    public USSSPath(Graph G, int s) {

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        dis = new int[G.V()];
        Arrays.fill(pre, -1);
        Arrays.fill(dis, -1);
        
        G.validateVertex(s);
        this.s = s;

        bfs(s);
        for (int x : dis)
            System.out.print(x + " ");
        System.out.println();
    }

    private void bfs(int s) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        pre[s] = s;
        visited[s] = true;
        dis[0] = 0;
        while (!queue.isEmpty()) {

            int v = queue.poll();

            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.offer(w);
                    visited[w] = true;
                    pre[w] = v;
                    dis[w] = dis[v] + 1;
                }
            }
        }
    }

    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    public int dis(int t) {
        G.validateVertex(t);
        return dis[t];
    }

    public Iterable<Integer> path(int t) {

        List<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) return res;

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

        Graph g = new AdjSet("g.txt");
        USSSPath usssPath = new USSSPath(g, 0);
        System.out.println("0 -> 6 : " + usssPath.path(6));
        System.out.println(usssPath.dis(6));
    }
}
