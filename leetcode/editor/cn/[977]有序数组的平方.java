//给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-4,-1,0,3,10]
//输出：[0,1,9,16,100]
//解释：平方后，数组变为 [16,1,0,9,100]
//排序后，数组变为 [0,1,9,16,100] 
//
// 示例 2： 
//
// 
//输入：nums = [-7,-3,2,3,11]
//输出：[4,9,9,49,121]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 已按 非递减顺序 排序 
// 
//
// 
//
// 进阶： 
//
// 
// 请你设计时间复杂度为 O(n) 的算法解决本问题 
// 
// Related Topics 数组 双指针 排序 👍 344 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//方法一 数组排序方法
//class Solution {
//    public int[] sortedSquares(int[] nums) {
//        int[] newSquares = new int[nums.length];
//        for (int i = 0; i < nums.length; i++){
//            newSquares[i] = nums[i] * nums[i];
//        }
//        Arrays.sort(newSquares);
//        return newSquares;
//    }
//}

// 方法二 双指针法
class Solution {
    public int[] sortedSquares(int[] nums) {
        int left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                left = i ;
            }else{
                break;
            }
        }
        right = left + 1;
        int[] result = new int[nums.length];
        int j = 0;
        while (left >= 0 || right < nums.length) {
            if (left < 0) {
                result[j++] = nums[right] * nums[right];
                right++;
            } else if (right == nums.length) {
                result[j++] = nums[left] * nums[left];
                left--;
            } else if (nums[left] * nums[left] < nums[right] * nums[right]) {
                result[j++] = nums[left] * nums[left];
                left--;
            } else {
                result[j++] = nums[right] * nums[right];
                right++;
            }

        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
