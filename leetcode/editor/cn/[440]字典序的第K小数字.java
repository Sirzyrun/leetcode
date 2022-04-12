//给定整数 n 和 k，返回 [1, n] 中字典序第 k 小的数字。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = 13, k = 2
//输出: 10
//解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
// 
//
// 示例 2: 
//
// 
//输入: n = 1, k = 1
//输出: 1
// 
//
// 
//
// 提示: 
//
// 
// 1 <= k <= n <= 10⁹ 
// 
// Related Topics 字典树 👍 351 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //                1                     2    3    4
    //     10        11       12     13 ...
    //  100..109  110..119  120..129
    public int findKthNumber(int n, int k) {
        int ans = 1;
        while (k > 1) {
            int count = helpCount(ans, ans + 1, n);
            if (count < k) {
                ans++;
                k -= count;
            } else {
                ans *= 10;
                k--;
            }
        }
        return ans;
    }

    private int helpCount(long n1, long n2, long n) {
        int res = 0;
        while (n1 <= n) {
            res += Math.min(n2, n+1) - n1;
            n1 *= 10;
            n2 *= 10;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
