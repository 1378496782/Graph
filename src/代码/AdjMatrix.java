package 代码;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjMatrix implements Graph{
    
    private int V;              // 点
    private int E;              // 边
    private int[][] adj;        // 邻接矩阵

    // 验证一个点是否合法
    public void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("传入的边 " + v + " 不合法");
    }
    
    public AdjMatrix(String filename) {
        File file = new File(filename);
        try (Scanner input = new Scanner(file)){

            V = input.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("点不合法");
            E = input.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("边不合法");
            adj = new int[V][V];

            for (int i = 0; i < E; i++) {
                
                int v = input.nextInt();
                validateVertex(v);
                int w = input.nextInt();
                validateVertex(w);
                
                if (v == w)
                    throw new IllegalArgumentException("自环边！");
                if (adj[v][w] == 1)
                    throw new IllegalArgumentException("平行边！");
                
                adj[v][w] = 1;
                adj[w][v] = 1;
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
        return adj[v][w] == 1;
    }
    
    // 邻接边
    public ArrayList<Integer> adj(int v) {
        
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for (int w : adj[v])
            if (w != 0)
                res.add(w);

        return res;
    }

    // 度
    public int degree(int v) {
        return adj(v).size();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) 
                sb.append(adj[i][j]).append(" ");
            sb.append("\n");
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {

        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix);
    }
}
