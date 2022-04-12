//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 二分查找 👍 1282 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {  //查找开始位置
            int middle = (left + right) / 2;
            if (nums[middle] >= target) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        if (nums[right] != target) {
            return new int[]{-1, -1};
        }
        int l = right;
        left = 0;
        right = nums.length - 1;
        while (left < right) { //查找结束位置
            int middle = (left + right + 1) / 2;
            if (nums[middle] <= target) {
                left = middle;
            }else{
                right = middle -1;
            }
        }
        return new int[]{l,right};
    }
}*/
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0){
            return new int[]{-1, -1};
        }
        int l = searchLeftBound(nums,target);
        int r = searchRightBound(nums,target);
        return new int[]{l,r};
    }
    // 搜索左边边界
    public int searchLeftBound(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right -left)/2;
            if (nums[middle] > target){
                right = middle - 1;
            }else if (nums[middle] < target){
                left = middle + 1;
            } else if (nums[middle] == target) {
                right = middle - 1; //不返回，锁定左边界
            }
        }
        if (left >= nums.length || nums[left] != target) {
            return  -1;
        }
        return left;
    }
    // 搜索右边边界
    public int searchRightBound(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right -left)/2;
            if (nums[middle] > target){
                right = middle - 1;
            }else if (nums[middle] < target){
                left = middle + 1;
            } else if (nums[middle] == target) {
                left = middle + 1;  //不返回，锁定右边界
            }
        }
        if (right < 0 || nums[right] != target) {
            return  -1;
        }
        return right;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
