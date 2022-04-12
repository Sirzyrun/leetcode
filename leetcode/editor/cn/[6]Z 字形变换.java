//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
// Related Topics 字符串 👍 1511 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convert(String s, int numRows) {
        int n = s.length();

        // init 2d arr
        char[][] chs = new char[numRows][n];  //最坏取n的情况为numRows为1
        int[] ps = new int[numRows];  // 一行用到了几个字符  因为最后要遍历嘛

        // mimic  模拟放的过程
        int row = 0, delta = 1;  //  delta为1表示往下着 （-1向上）  从0行开始找
        for (int i = 0; i < n; i++) {
            // fill in 2d arr   只要注意行的变化啊  列每填一次指针后移一位即可
            chs[row][ps[row]] = s.charAt(i);
            ps[row]++;

            // update row, delta  **
            int[] nextRowAndDelta = getNextRowAndDelta(row, delta, numRows);
            row = nextRowAndDelta[0];
            delta = nextRowAndDelta[1];
        }

        // 2d arr -> str
        return getAns(chs, ps);
    }

    private int[] getNextRowAndDelta(int currRow, int currDelta, int numRows) {
        if (numRows == 1) {
            return new int[]{currRow, 0};
        }

        if (currRow == 0) {
            return new int[]{currRow + 1, 1};
        }

        if (currRow == numRows - 1) {
            return new int[]{currRow - 1, -1};
        }
        //  delta 巧妙运用
        return new int[]{currRow + currDelta, currDelta};
    }

    private String getAns(char[][] chs, int[] ps) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chs.length; i++) {
            sb.append(chs[i], 0, ps[i]);
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
