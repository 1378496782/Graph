package 代码;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 时间复杂度：O(V + E)
 */
public class CC {   // 连通分量

    private Graph G;
    private int[] visited;
    private int cccount = 0;

    public CC(Graph g) {
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

    public static void main(String[] args) {

        Graph g = new AdjSet("g.txt");
        CC cc = new CC(g);
        System.out.println(cc.count());                    // 2
        System.out.println(cc.isConnected(2, 3));   // true
        System.out.println(cc.isConnected(2, 5));   // false
        System.out.println(Arrays.toString(cc.components()));
    }
}
