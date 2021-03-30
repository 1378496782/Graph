package Graph11;

public class WeightedEdge {
    
    public int v, w, weight;

    public WeightedEdge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    
    public int getV() {
        return v;
    }
    
    public int getW() {
        return w;
    }

    @Override
    public String toString() {
        return String.format("%d-%d: %d", v, w, weight);
    }
}
