package Graph11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class WeightedGraph{

    private int V;              // 点
    private int E;              // 边
    private TreeMap<Integer, Integer>[] adj;    // 邻接表

    // 验证一个点是否合法
    public void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("传入的边 " + v + " 不合法");
    }

    public WeightedGraph(String filename) {
        File file = new File(filename);
        try (Scanner input = new Scanner(file)){

            V = input.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("点不合法");
            E = input.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("边不合法");
            adj = new TreeMap[V];
            for (int i = 0; i < V; i++)
                adj[i] = new TreeMap<>();

            for (int i = 0; i < E; i++) {

                int v = input.nextInt();
                validateVertex(v);
                int w = input.nextInt();
                validateVertex(w);
                int weight = input.nextInt();
                
                if (v == w)
                    throw new IllegalArgumentException("自环边！");
                if (adj[v].containsKey(w))
                    throw new IllegalArgumentException("平行边！");

                adj[v].put(w, weight);
                adj[w].put(v, weight);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 点数
    public int V() {
        return V;
    }

    // 边数
    public int E() {
        return E;
    }

    // 两点是否连通
    public boolean hasEdge(int v, int w) {

        validateVertex(v);
        validateVertex(w);
        return adj[v].containsKey(w);
    }

    // 邻接边
    public Iterable<Integer> adj(int v) {

        validateVertex(v);
        return adj[v].keySet();
    }

    // 度
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public int getWeight(int v, int w) {
        if (hasEdge(v, w))
            return adj[v].get(w);
        throw new IllegalArgumentException(String.format("%d-%d 的边不存在...", v, w));
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(v).append(" : ");
            for (int x : adj[v].keySet())
                sb.append(String.format("%d(%d) ", x, adj[v].get(x)));
            sb.append("\n");
        }

        return sb.toString();
    }
    
    public static void main(String[] args) {

        WeightedGraph weightedGraph = new WeightedGraph("D:\\百度网盘下载\\工作空间\\IDEA的工作空间\\Graph\\src\\g.txt");
        System.out.println(weightedGraph);
    }
}
