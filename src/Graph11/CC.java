package Graph11;

import 代码.AdjSet;
import 代码.Graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 时间复杂度：O(V + E)
 * 用于计算一个图中联通分量的数量
 */
public class CC {   // 连通分量

    private WeightedGraph G;
    private int[] visited;
    private int cccount = 0;

    public CC(WeightedGraph g) {
        G = g;
        visited = new int[g.V()];
        Arrays.fill(visited, -1);
        for (int v = 0; v < g.V(); v++) {
            if (visited[v] == -1) {
                dfs(v, cccount);
                cccount ++;
            }
        }
    }

    public ArrayList<Integer>[] components() {

        ArrayList<Integer>[] res = new ArrayList[cccount];
        for (int i = 0; i < cccount; i++) 
            res[i] = new ArrayList<>();

        for (int v = 0; v < G.V(); v++) 
            res[visited[v]].add(v);
        
        return res;
    }
    
    public boolean isConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    public int count() {
        /*for (int e : visited)
            System.out.print(e + " ");
        System.out.println();*/
        return cccount;
    }

    private void dfs(int v, int ccid) {

        visited[v] = ccid;
        for (int w : G.adj(v))
            if (visited[w] == -1)
                dfs(w, ccid);
    }
}
