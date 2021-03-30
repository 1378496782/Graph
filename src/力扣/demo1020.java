package 力扣;

// 未解决
public class demo1020 {
    
    private int[][] grid;
    private int row, col;
    private final int[] dx = new int[]{-1, 0, 1, 0};
    private final int[] dy = new int[]{0, 1, 0, -1};
    private boolean[][] visited;

    public int numEnclaves(int[][] grid) {
        
        row = grid.length;
        col = grid[0].length;
        this.grid = grid;
        visited = new boolean[row][col];
        int res = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && grid[i][j] == 1 && dfs(i, j))
                    res ++;
            }
        }
        
        return res;
    }

    private boolean dfs(int x, int y) {
        
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a > row - 1 || b < 0 || b > col - 1)
                continue;
            if (visited[a][b])
                continue;
            if (grid[a][b] == 0)
                continue;
            if (a == 0 || a == row - 1 || b == 0 || b == col - 1)
                return false;
            if (!dfs(a, b)) 
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println(new demo1020().numEnclaves(new int[][]{
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        }));    // 3
        
        System.out.println(new demo1020().numEnclaves(new int[][]{
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0}
        }));    // 0
    }
}
