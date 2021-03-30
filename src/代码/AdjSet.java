package 代码;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class AdjSet implements Graph{

    private int V;              // 点
    private int E;              // 边
    private TreeSet<Integer>[] adj;    // 邻接表

    // 验证一个点是否合法
    public void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("传入的边 " + v + " 不合法");
    }

    public AdjSet(String filename) {
        File file = new File(filename);
        try (Scanner input = new Scanner(file)){

            V = input.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("点不合法");
            E = input.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("边不合法");
            adj = new TreeSet[V];
            for (int i = 0; i < V; i++)
                adj[i] = new TreeSet<>();

            for (int i = 0; i < E; i++) {

                int v = input.nextInt();
                validateVertex(v);
                int w = input.nextInt();
                validateVertex(w);

                if (v == w)
                    throw new IllegalArgumentException("自环边！");
                if (adj[v].contains(w))
                    throw new IllegalArgumentException("平行边！");

                adj[v].add(w);
                adj[w].add(v);
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
        return adj[v].contains(w);
    }

    // 邻接边
    public Iterable<Integer> adj(int v) {

        validateVertex(v);
        return adj[v];
    }

    // 度
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(v).append(" : ");
            for (int x : adj[v])
                sb.append(x).append(" ");
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        AdjSet adjSet = new AdjSet("g.txt");
        System.out.println(adjSet);
    }
}
