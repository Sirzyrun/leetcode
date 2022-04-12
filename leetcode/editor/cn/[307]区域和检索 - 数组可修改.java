//给你一个数组 nums ，请你完成两类查询。 
//
// 
// 其中一类查询要求 更新 数组 nums 下标对应的值 
// 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right 
// 
//
// 实现 NumArray 类： 
//
// 
// NumArray(int[] nums) 用整数数组 nums 初始化对象 
// void update(int index, int val) 将 nums[index] 的值 更新 为 val 
// int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元
//素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]） 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：
//["NumArray", "sumRange", "update", "sumRange"]
//[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
//输出：
//[null, 9, null, 8]
//
//解释：
//NumArray numArray = new NumArray([1, 3, 5]);
//numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
//numArray.update(1, 2);   // nums = [1,2,5]
//numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// -100 <= nums[i] <= 100 
// 0 <= index < nums.length 
// -100 <= val <= 100 
// 0 <= left <= right < nums.length 
// 调用 pdate 和 sumRange 方法次数不大于 3 * 10⁴ 
// 
// Related Topics 设计 树状数组 线段树 数组 👍 377 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class NumArray {
    // 累加和
    int[] sums;
    // 更新后数组
    int[] nums;

    public void NumArray(int[] nums) {
        // 原数组长度+1, +1的原因是计算lowbit时,使用下标0会进入死循环
        this.sums = new int[nums.length + 1];
        this.nums = nums;
        for (int i = 0; i < nums.length; i++) {
            // 初始化累加和数组
            insert(i, nums[i]);
        }
    }

    /**
     * 插入数字,初始化
     */
    private void insert(int index, int val) {
        // 下标+1
        int x = index + 1;
        while (x < sums.length) {
            sums[x] = sums[x] + val;
            x += lowBit(x);
        }
    }

    /**
     * 计算lowBit
     */
    private int lowBit(int x) {
        return x & (-x);
    }

    /**
     * 更新数组以及累加和
     */
    public void update(int index, int val) {
        int x = index + 1;
        while (x < sums.length) {
            // 减去之前nums[index]的值, 加上新的值
            sums[x] = sums[x] - nums[index] + val;
            x += lowBit(x);
        }
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return query(right + 1) - query(left);
    }

    /**
     * 查询树状数组
     */
    public int query(int x) {
        int s = 0;
        while (x != 0) {
            s += sums[x];
            x -= lowBit(x);
        }
        return s;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)
