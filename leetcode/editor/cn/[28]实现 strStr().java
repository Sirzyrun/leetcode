//实现 strStr() 函数。 
//
// 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如
//果不存在，则返回 -1 。 
//
// 
//
// 说明： 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。 
//
// 
//
// 示例 1： 
//
// 
//输入：haystack = "hello", needle = "ll"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：haystack = "aaaaa", needle = "bba"
//输出：-1
// 
//
// 示例 3： 
//
// 
//输入：haystack = "", needle = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= haystack.length, needle.length <= 5 * 10⁴ 
// haystack 和 needle 仅由小写英文字符组成 
// 
// Related Topics 双指针 字符串 字符串匹配 👍 1168 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*class Solution {   // 暴力求解  时间复杂度O(m*n)
    public int strStr(String haystack, String needle) {
        char[] s = haystack.toCharArray();
        char[] p = needle.toCharArray();
        int n = haystack.length();
        int m = needle.length();
        for (int i = 0; i <= n - m; i++) {
            int a = i;
            int b = 0;
            while (b < m && s[a]==p[b]) {
                a++;
                b++;
            }
            if(b==m){
                return i;
            }
        }
        return -1;
    }
}*/
class Solution {  //kmp
    // 直接用前缀表当作next数组 遇到不匹配找前一位数组下标对应的next数组中的数值 （若前缀表整体减一 直接找不匹配时对应下标的next数组中的数值即可）实现方式不同而已
    public int strStr(String haystack, String needle) {
        if (needle.length()==0){
            return 0;
        }
        char[] s =haystack.toCharArray();
        char[] p =needle.toCharArray();
        int[] next = new int[p.length];
        getNext(next,needle);
        int j = 0;
        for (int i = 0;i < s.length;i++){
            while(j>0&&s[i]!=p[j]){
                j = next[j-1];
            }
            if(s[i]==p[j]){
                j++;
            }
            if(j == p.length){
                return (i - p.length +1);
            }
        }
        return -1;
    }
    public static void getNext(int[] next, String s){ //构造Next数组四步
        char[] ss = s.toCharArray();
        int j = 0;  // j 指向前缀起始位置
        next[0] = 0;         // 1 初始化
        for (int i = 1;i < s.length();i++){
            //i 指向后缀起始位置
            while (j > 0 && ss[j] != ss[i]) {   // 2 前后缀不相等
                j = next[j-1];
            }
            if (ss[j] == ss[i]){    // 3 前后缀相等
                j++;
            }
            next[i] = j;    // 更新 next数组
        }
    }
}
/*class Solution {  //String indexOf()方法
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}*/
//leetcode submit region end(Prohibit modification and deletion)
