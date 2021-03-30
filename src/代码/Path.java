package 代码;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 时间复杂度：O(V + E)
 */
public class Path {  

    private Graph G;
    private boolean[] visited;
    private int s, t;
    private int[] pre;

    public Path(Graph g, int s, int t) {
        G = g;
        G.validateVertex(s);
        G.validateVertex(t);
        this.s = s;
        this.t = t;
        pre = new int[g.V()];
        Arrays.fill(pre, -1);
        visited = new boolean[g.V()];
        dfs(s, s);
        for (boolean b : visited)
            System.out.print(b + " ");
        System.out.println();
    }

    private boolean dfs(int v, int parent) {

        visited[v] = true;
        pre[v] = parent;
        if (v == t) return true;
        for (int w : G.adj(v))
            if (!visited[w])
                if (dfs(w, v)) return true;
                
        return false;
    }

    public boolean isConnected() {
        return visited[t];
    }

    public Iterable<Integer> path() {

        List<Integer> res = new ArrayList<>();
        if (!isConnected()) return res;

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
        Path path = new Path(g, 0, 6);
        System.out.println("0 -> 6 : " + path.path());
        
        Path path2 = new Path(g, 0, 5);
        System.out.println("0 -> 5 : " + path2.path());
        
        Path path3 = new Path(g, 0, 1);
        System.out.println("0 -> 1 : " + path3.path());
    }
}
