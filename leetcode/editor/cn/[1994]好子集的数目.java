//给你一个整数数组 nums 。如果 nums 的一个子集中，所有元素的乘积可以表示为一个或多个 互不相同的质数 的乘积，那么我们称它为 好子集 。 
//
// 
// 比方说，如果 nums = [1, 2, 3, 4] ：
//
// 
// [2, 3] ，[1, 2, 3] 和 [1, 3] 是 好 子集，乘积分别为 6 = 2*3 ，6 = 2*3 和 3 = 3 。 
// [1, 4] 和 [4] 不是 好 子集，因为乘积分别为 4 = 2*2 和 4 = 2*2 。 
// 
// 
// 
//
// 请你返回 nums 中不同的 好 子集的数目对 10⁹ + 7 取余 的结果。 
//
// nums 中的 子集 是通过删除 nums 中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视
//为不同的子集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,4]
//输出：6
//解释：好子集为：
//- [1,2]：乘积为 2 ，可以表示为质数 2 的乘积。
//- [1,2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
//- [1,3]：乘积为 3 ，可以表示为质数 3 的乘积。
//- [2]：乘积为 2 ，可以表示为质数 2 的乘积。
//- [2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
//- [3]：乘积为 3 ，可以表示为质数 3 的乘积。
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,2,3,15]
//输出：5
//解释：好子集为：
//- [2]：乘积为 2 ，可以表示为质数 2 的乘积。
//- [2,3]：乘积为 6 ，可以表示为互不相同质数 2 和 3 的乘积。
//- [2,15]：乘积为 30 ，可以表示为互不相同质数 2，3 和 5 的乘积。
//- [3]：乘积为 3 ，可以表示为质数 3 的乘积。
//- [15]：乘积为 15 ，可以表示为互不相同质数 3 和 5 的乘积。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 30 
// 
// Related Topics 位运算 数组 数学 动态规划 状态压缩 👍 36 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 从nums中选一个子集 把子集的数做乘积 把乘积做质因数分解 如果所有的质因数只出现一次 则为一个好子集
class Solution {
    int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    int[] bads = new int[]{4, 9, 25};
    int MOD = 1000000007;
    // dp
    // 状态 dp[i]，i对应互不相同的质数 的乘积，dp[i]好子集的个数
    // 转移 考虑num, num所有质因数还没有被用过 （getMask(num) & prevMask == 0），更新bitmask和dp (dp[getMask(num) | prevMask] += dp[prevMask] * cnt(num) )
    //                         用过，跳过
    // 初始化，dp[0] = 1、、
    // 结果 sum(dp[i]), 1 <= i <= (1 << 10)
    //     一个好子集  n个1, => 2^n
    //     sum(dp[i]), 1 <= i <= (1 << 10) * 2^cnt(1)

    // bitmask
    // 10bits 0/1 用来表示质因数有没有被用过 0表示未被用过 1表示已经用过
    // num1， num2， <-      getMask(num1) & getMask(num2)  若 > 0, 有相同的质数； 若== 0 ，没有
    public int numberOfGoodSubsets(int[] nums) {
        // cnt
        int[] cnt = count(nums);   //  用一个哈希表表示数组nums中每一个num的数目有多少个

        // dp
        long[] dp = new long[1 + (1 << 10)];  //质数一共有10个 数组1加一个 共有2^10+1种 质数乘积

        // 初始化
        dp[0] = 1L;  // dp[0] 0表示一个数都没有选

        // 转移
        for (int num = 2; num <= 30; num++) {
            if (cnt[num] == 0 || isBadNum(num)) {
                continue;
            }

            // get mask
            int numMask = getMask(num);

            for (int prevMask = 0; prevMask < (1 << 10); prevMask++) {
                if ((prevMask & numMask) > 0) {  // 有相同的质数 跳过
                    continue;
                }

                // update dp
                dp[prevMask | numMask] += dp[prevMask] * cnt[num];
                dp[prevMask | numMask] %= MOD;
            }
        }

        // 结果
        return getAns(dp, cnt);
    }

    private int getAns(long[] dp, int[] cnt) {
        long ans = 0;

        for (int i = 1; i <= (1 << 10); i++) {
            ans += dp[i];
            ans %= MOD;
        }

        ans *= pow(2L, cnt[1]);
        ans %= MOD;

        return (int) ans;
    }

    private long pow(long base, int k) {
        long ans = 1L;

        while (k > 0) {
            if (k % 2 == 1) {
                ans = (ans * base) % MOD;
            }
            base = (base * base) % MOD;
            k >>= 1;
        }

        return ans;
    }


    private int getMask(int num) {
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            if (num % primes[i] == 0) {
                ans += (1 << i);
            }
        }
        return ans;
    }

    private boolean isBadNum(int num) {
        for (int b : bads) {
            if (num % b == 0) {
                return true;
            }
        }
        return false;
    }

    private int[] count(int[] nums) {
        int[] ans = new int[31];
        for (int n : nums) {
            ans[n]++;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
