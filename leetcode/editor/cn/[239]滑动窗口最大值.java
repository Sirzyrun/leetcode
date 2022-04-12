//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1422 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class MyQueue {  //维护可能出现的最大值
    Deque<Integer> deque = new LinkedList<>();

    // 弹出元素 当队列不为空 并且窗口滑出元素 和出队元素相同时 出队
    void poll(int val) {
        if (!deque.isEmpty() && val == deque.peek()) {
            deque.poll();
        }
    }

    // 只需要保持队列 递减 维护可能的最大值
    void add(int val) {
        while (!deque.isEmpty() && deque.getLast() < val) {
            deque.removeLast();
        }
        deque.add(val);
    }

    // 取最大值
    int peek() {
        return deque.peek();
    }
}

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int num = 0;
        int[] ans = new int[n - k + 1];
        MyQueue myQueue = new MyQueue();
        for (int i = 0; i < k; i++) {
            myQueue.add(nums[i]);
        }
        ans[num++] = myQueue.peek();
        for (int i = k; i < n; i++) {
            myQueue.poll(nums[i - k]);
            myQueue.add(nums[i]);
            ans[num++] = myQueue.peek();
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
