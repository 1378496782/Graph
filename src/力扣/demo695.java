package 力扣;

public class demo695 {

    private int N = 55;
    private boolean[][] visited = new boolean[N][N];
    private final int[] dx = new int[]{-1, 0, 1, 0};
    private final int[] dy = new int[]{0, 1, 0, -1};
    private int[][] g = new int[N][N];
    private int res = 0;
    private int row, col;

    private int dfs(int x, int y) {
        visited[x][y] = true;
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= row || b < 0 || b >= col)
                continue;
            if (visited[a][b])
                continue;
            if (g[a][b] == 0)
                continue;
            sum += dfs(a, b);
        }
        return Math.max(1, sum + 1);
    }

    public int maxAreaOfIsland(int[][] grid) {

        row = grid.length;
        if (row < 1)
            return 0;

        col = grid[0].length;
        System.arraycopy(grid, 0, g, 0, row);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] || g[i][j] == 0)
                    continue;
                res = Math.max(res, dfs(i, j));
            }
        }

        return res;
    }

    public static void main(String[] args) {

        System.out.println(new demo695().maxAreaOfIsland(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        }));    // 6

        System.out.println(new demo695().maxAreaOfIsland(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0}
        }));    // 0
    }
}
