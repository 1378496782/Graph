package 代码;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 时间复杂度：O(V + E)
 */
public class SingleSourcePath {   

    private Graph G;
    private boolean[] visited;
    private int s;
    private int[] pre;

    public SingleSourcePath(Graph g, int s) {
        G = g;
        G.validateVertex(s);
        this.s = s;
        pre = new int[g.V()];
        Arrays.fill(pre, -1);
        visited = new boolean[g.V()];
        dfs(s, s);
    }

    private void dfs(int v, int parent) {

        visited[v] = true;
        pre[v] = parent;
        for (int w : G.adj(v))
            if (!visited[w])
                dfs(w, v);
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
        SingleSourcePath ssPath = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6 : " + ssPath.path(6));
        System.out.println("0 -> 5 : " + ssPath.path(5));
    }
}
