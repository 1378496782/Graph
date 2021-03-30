package 代码;

import java.util.Arrays;

/**
 * 时间复杂度：O(V + E)
 */
public class BipartitionDetection {

    private Graph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBiPartition = true;

    public BipartitionDetection(Graph g) {
        G = g;
        visited = new boolean[g.V()];
        colors = new int[g.V()];
        Arrays.fill(colors, -1);
        for (int v = 0; v < g.V(); v++) {
            if (!visited[v])
                if (!dfs(v, 0)) {
                    isBiPartition = false;
                    break;
                }
        }
    }

    private boolean dfs(int v, int color) {

        visited[v] = true;
        colors[v] = color;
        for (int w : G.adj(v))
            if (!visited[w]) {
                if (!dfs(w, color ^ 1)) return false;
            } 
            else if (colors[w] == color) return false;
        return true;
    }

    public boolean isBiPartition() {
        return isBiPartition;
    }

    public static void main(String[] args) {

        Graph g = new AdjSet("g.txt");
        BipartitionDetection b = new BipartitionDetection(g);
        System.out.println(b.isBiPartition());

        Graph g2 = new AdjSet("g2.txt");
        BipartitionDetection b2 = new BipartitionDetection(g2);
        System.out.println(b2.isBiPartition());
    }
}
