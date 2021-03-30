package 代码;

import java.util.*;

public class SingleSourcePathBFS {

    private Graph G;
    private boolean[] visited;
    private int s;
    private int[] pre;

    public SingleSourcePathBFS(Graph G, int s) {

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];

        G.validateVertex(s);
        this.s = s;

        bfs(s);
    }

    private void bfs(int s) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        pre[s] = s;
        visited[s] = true;
        while (!queue.isEmpty()) {

            int v = queue.poll();

            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.offer(w);
                    visited[w] = true;
                    pre[w] = v;
                }
            }
        }
    }

    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
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
        SingleSourcePathBFS ssPathBFS = new SingleSourcePathBFS(g, 0);
        System.out.println("0 -> 6 : " + ssPathBFS.path(6));
    }
}
