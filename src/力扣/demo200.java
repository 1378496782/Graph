package 力扣;

public class demo200 {

    private char[][] grid;
    private int row, col;
    private final int[] dx = new int[]{-1, 0, 1, 0};
    private final int[] dy = new int[]{0, 1, 0, -1};
    private boolean[][] visited;

    public int numIslands(char[][] grid) {

        row = grid.length;
        col = grid[0].length;
        this.grid = grid;
        visited = new boolean[row][col];
        int res = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] || grid[i][j] == '0')
                    continue;
                dfs(i, j);
                res ++;
            }
        }

        return res;
    }

    private void dfs(int x, int y) {

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= row || b < 0 || b >= col)
                continue;
            if (visited[a][b])
                continue;
            if (grid[a][b] == '0')
                continue;
            dfs(a, b);
        }
    }

    public static void main(String[] args) {

        System.out.println(new demo200().numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        }));    // 1
        
        System.out.println(new demo200().numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        }));    // 3
    }
}
