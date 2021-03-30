package 代码;

import java.util.ArrayList;

/**
 * 时间复杂度：O(V + E)
 */
public class GraphDFS {

    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();
    
    public GraphDFS(Graph g) {
        G = g;
        visited = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!visited[v])
                dfs(v);
        }
    }

    private void dfs(int v) {

        visited[v] = true;
        pre.add(v);   // 前序遍历
        for (int w : G.adj(v))
            if (!visited[w])
                dfs(w);
        post.add(v);   // 后序遍历
    }
    
    public Iterable<Integer> pre() {
        return pre;
    }
    
    public Iterable<Integer> post() {
        return post;
    }

    public static void main(String[] args) {

        Graph g = new AdjSet("g.txt");
        GraphDFS graphDFS = new GraphDFS(g);    // 使用多态性，g 可以传入 GraphDFS
        System.out.println("DFS preorder : " + graphDFS.pre());
        System.out.println("DFS postorder : " + graphDFS.post());
    }
}
