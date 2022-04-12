//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 矩阵 模拟 👍 971 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        int r = matrix.length; //数组行数
        int l = matrix[0].length; //数组列数
        boolean[][] visited = new boolean[r][l];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < l; j++) {
                visited[i][j] = false;
            }
        }
        int startX = 0; //模拟开始横纵坐标
        int startY = 0;
        int offset = 1;
        // 首先明确的是循环的次数是由 行和列 中较小的来决定
        int rloop = r / 2;
        int lloop = l / 2;
        int loop = Math.min(rloop, lloop);
        int x = loop;
        while (loop > 0) {
            int i = startX;
            int j = startY;
            for (; j < startY + l - offset; j++) {
                list.add(matrix[i][j]);
                visited[i][j] = true;
            }
            for (; i < startX + r - offset; i++) {
                list.add(matrix[i][j]);
                visited[i][j] = true;
            }
            for (; j > startY; j--) {
                list.add(matrix[i][j]);
                visited[i][j] = true;
            }
            for (; i > startX; i--) {
                list.add(matrix[i][j]);
                visited[i][j] = true;
            }
            startX++;
            startY++;
            offset += 2;
            loop--;
        }
        if (x == rloop && r != l) { //行小列大
            for (int j = startY; j < startY + l - x * 2; j++) {
                if (!visited[startX][j]) {
                    list.add(matrix[startX][j]);
                }
            }
        } else if (x == lloop && r != l) { //列小行大
            for (int i = startX; i < startX + r - x * 2; i++) {
                if (!visited[i][startY]) {
                    list.add(matrix[i][startY]);
                }
            }
        } else if (r == l && r % 2 == 1) {
            list.add(matrix[startX][startY]);
        }
        return list;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
