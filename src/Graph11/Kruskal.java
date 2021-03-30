package Graph11;

import java.util.*;

public class Kruskal {

    private final WeightedGraph G;
    private final ArrayList<WeightedEdge> mst; // 最小生成树

    public Kruskal(WeightedGraph G) {
        
        this.G = G;
        mst = new ArrayList<>();


        CC cc = new CC(G);
        if (cc.count() > 1)
            return;

        List<WeightedEdge> edges = new ArrayList<>();
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v < w) 
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));
            }
        }

        edges.sort(new Comparator<WeightedEdge>() {
            @Override
            public int compare(WeightedEdge o1, WeightedEdge o2) {
                return o1.weight - o2.weight;
            }
        });
        
        // 使用并查集
        UF uf = new UF(G.V());
        for (WeightedEdge edge : edges) {
            int v = edge.getV();
            int w = edge.getW();
            if (!uf.isConnected(v, w)) {
                uf.unionElements(v, w);
                mst.add(edge);
            }
        }
    }
    
    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("D:\\百度网盘下载\\工作空间\\IDEA的工作空间\\Graph\\src\\g.txt");
        Kruskal kruskal = new Kruskal(g);
        System.out.println(kruskal.result());
    }
}
