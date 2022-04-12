//给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abcdefg", k = 2
//输出："bacdfeg"
// 
//
// 示例 2： 
//
// 
//输入：s = "abcd", k = 2
//输出："bacd"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由小写英文组成 
// 1 <= k <= 10⁴ 
// 
// Related Topics 双指针 字符串 👍 252 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int left = 0;
        int right = left + 2 * k - 1;
        while (right < n) {
            reverseString(arr, left, left + k - 1);
            left += 2 * k;
            right += 2 * k;
        }
        int last = n - 1 - left + 1;
        if (last < k) {
            reverseString(arr, left, n - 1);
        }
        if (last >= k && last < 2 * k) {
            reverseString(arr, left, left + k - 1);
        }

        return new String(arr);
    }

    public void reverseString(char[] s, int left, int right) {
        while (left < right) {
            swap(s, left, right);
            left++;
            right--;
        }
    }

    public void swap(char[] s, int i, int j) {
        char c = s[i];
        s[i] = s[j];
        s[j] = c;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
