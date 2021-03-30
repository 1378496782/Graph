package 力扣;

import java.util.Arrays;

public class demo785 {

    private int V;
    private boolean[] visited;
    private int[] colors;
    private int[][] graph;

    public boolean isBipartite(int[][] graph) {

        this.graph = graph;
        V = graph.length;   // 顶点个数
        visited = new boolean[V];
        colors = new int[V];
        Arrays.fill(colors, -1);

        for (int v = 0; v < V; v++) {
            if (!visited[v])
                if (!dfs(v, 0))
                    return false;
        }

        return true;
    }

    private boolean dfs(int s, int color) {

        visited[s] = true;
        colors[s] = color;
        for (int w : graph[s]) {
            if (!visited[w]) {
                if (!dfs(w, 1 ^ color))
                    return false;
            } else if (colors[w] == color) return false;
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println(new demo785().isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));//false
        System.out.println(new demo785().isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));//true
    }
}
