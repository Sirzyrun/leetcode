//给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i 
//+ 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [3,1,3,6]
//输出：false
// 
//
// 示例 2： 
//
// 
//输入：arr = [2,1,2,6]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：arr = [4,-2,2,-4]
//输出：true
//解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= arr.length <= 3 * 10⁴ 
// arr.length 是偶数 
// -10⁵ <= arr[i] <= 10⁵ 
// 
// Related Topics 贪心 数组 哈希表 排序 👍 67 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        // -8 -4 -4 -2 0 0 1 1 2 2 3 6
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (i < 0) {
                if (map.containsKey(2 * i) && map.get(2 * i) != 0) {
                    map.put(2 * i, map.get(2 * i) - 1);
                } else {
                    map.put(i, map.getOrDefault(i, 0) + 1);
                }
            } else {
                if (i % 2 != 0) {
                    map.put(i, map.getOrDefault(i, 0) + 1);
                    continue;
                }
                if (map.containsKey(i / 2) && map.get(i / 2) != 0) {
                    map.put(i / 2, map.get(i / 2) - 1);
                } else {
                    map.put(i, map.getOrDefault(i, 0) + 1);
                }
            }
        }
        for (int i : map.values()) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
