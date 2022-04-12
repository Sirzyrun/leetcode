//给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 。 
//
// 但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式。你的表达式不应
//该含有冗余的括号。 
//
// 示例： 
//
// 
//输入: [1000,100,10,2]
//输出: "1000/(100/10/2)"
//解释:
//1000/(100/10/2) = 1000/((100/10)/2) = 200
//但是，以下加粗的括号 "1000/((100/10)/2)" 是冗余的，
//因为他们并不影响操作的优先级，所以你需要返回 "1000/(100/10/2)"。
//
//其他用例:
//1000/(100/10)/2 = 50
//1000/(100/(10/2)) = 50
//1000/100/10/2 = 0.5
//1000/100/(10/2) = 2
// 
//
// 说明: 
//
// 
// 输入数组的长度在 [1, 10] 之间。 
// 数组中每个元素的大小都在 [2, 1000] 之间。 
// 每个测试用例只有一个最优除法解。 
// 
// Related Topics 数组 数学 动态规划 👍 122 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class Result {
        double val;
        String str;

        public Result(double val, String str) {
            this.val = val;
            this.str = str;
        }
    }

    public String optimalDivision(int[] nums) {
        Map<Integer, Map<Integer, Result>> maxMemo = new HashMap<>();
        Map<Integer, Map<Integer, Result>> minMemo = new HashMap<>();
        return recur(nums, 0, nums.length - 1, true, maxMemo, minMemo).str;
    }

    public Result recur(int[] nums, int start, int end, boolean findMax,
                        Map<Integer, Map<Integer, Result>> maxMemo,
                        Map<Integer, Map<Integer, Result>> minMemo) {
        // exit
        if(start == end) {
            return new Result(nums[start], String.valueOf(nums[start]));
        }

        if(start + 1 == end) {
            return new Result(nums[start] * 1.0 / nums[end], String.valueOf(nums[start]) + "/" + String.valueOf(nums[end]));
        }

        if(findMax && maxMemo.containsKey(start) && maxMemo.get(start).containsKey(end)) {
            return maxMemo.get(start).get(end);
        }

        if(!findMax && minMemo.containsKey(start) && minMemo.get(start).containsKey(end)) {
            return minMemo.get(start).get(end);
        }

        // recur
        double val = findMax ? -1.0 : 1001.0;
        String str = "";
        for(int i = start ; i < end ; i++) {
            Result dividend = recur(nums, start, i, findMax, maxMemo, minMemo);
            Result divisor  = recur(nums, i + 1, end, !findMax, maxMemo, minMemo);
            if(isLocalOpt(dividend, divisor, findMax, val)) {
                val = dividend.val / divisor.val;
                str = dividend.str + "/" + (end - i >= 2 ? "(" + divisor.str + ")" : divisor.str);;
            }
        }

        Result ans = new Result(val, str);
        Map<Integer, Map<Integer, Result>> memo = findMax ? maxMemo : minMemo;
        memo.putIfAbsent(start, new HashMap<>());
        memo.get(start).put(end, ans);
        return ans;
    }

    private boolean isLocalOpt(Result dividend, Result divisor, boolean findMax, double val) {
        return  findMax && Double.compare(dividend.val / divisor.val, val) > 0
                || !findMax && Double.compare(dividend.val / divisor.val, val) < 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
