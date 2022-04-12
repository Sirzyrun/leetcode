//给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。 
//
// “最近的”定义为两个整数差的绝对值最小。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = "123"
//输出: "121"
// 
//
// 示例 2: 
//
// 
//输入: n = "1"
//输出: "0"
//解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= n.length <= 18 
// n 只由数字组成 
// n 不含前导 0 
// n 代表在 [1, 10¹⁸ - 1] 范围内的整数 
// 
// Related Topics 数学 字符串 👍 153 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 12345 -> 12321    123456 -> 123321
    //  99800 -> 99799
    // 10101 -> 10001    11011 -> 11111
    // 10 -> 9
    // 99 -> 101
    public String nearestPalindromic(String n) {
        // 1 穷举所有可能答案 放进集合
        List<Long> cands = getCands(n);
        // 2 对集合按照排序
        Collections.sort(cands, (l1, l2) -> {
            long origin = Long.parseLong(n);
            long diff1 = Math.abs(l1 - origin);
            long diff2 = Math.abs(l2 - origin);
            return Long.compare(diff1, diff2) == 0
                    ? Long.compare(l1, l2)
                    : Long.compare(diff1, diff2);
        });
        // 3 返回最小值
        return String.valueOf(cands.get(0));
    }

    private List<Long> getCands(String n) {
        int len = n.length();
        List<Long> ans = new ArrayList<>();
        // 获取前一半 12345->12  123456->12
        String firstHalf = len % 2 == 1 ? n.substring(0, len / 2) : n.substring(0, len / 2 - 1);
        // 12345->3    123456->3
        char minChar = len % 2 == 1 ? n.charAt(len / 2) : n.charAt(len / 2 - 1);
        ans.add(getPalin(firstHalf, inc(minChar), len));
        ans.add(getPalin(firstHalf, dec(minChar), len)); // 99800->99799
        if (!isPalin(n)) {
            ans.add(getPalin(firstHalf, minChar, len));  // 12315 ->12321
        }
        ans.add(minusOne(len - 1)); // 100 -> 99 减一位
        ans.add(plusOne(len));  //    99 -> 101 加一位
        return ans;
    }

    private long getPalin(String firstHalf, char midChar, int len) {
        if (firstHalf.length() == 0) {
            return (midChar - '0') * (len % 2 == 0 ? 11L : 1L);
        }

        // add firstHalf
        long ans = Long.parseLong(firstHalf);

        // midChar (twice if len % 2 == 0)
        ans *= 10;
        ans += midChar - '0';

        if (len % 2 == 0) {
            ans *= 10;
            ans += midChar - '0';
        }

        // add reversed firstHalf
        for (int i = 0; i < firstHalf.length(); i++) {
            ans *= 10;
        }
        ans += Long.parseLong(new StringBuilder(firstHalf).reverse().toString());

        return ans;
    }

    private boolean isPalin(String str) {
        String revedStr = new StringBuilder(str).reverse().toString();
        return revedStr.equals(str);
    }

    private char inc(char ch) {
        if (ch == '9') {
            return '0';
        }

        return (char) (ch + 1);
    }

    private char dec(char ch) {
        if (ch == '0') {
            return '9';
        }

        return (char) (ch - 1);
    }

    private long minusOne(int len) {  // 100 -> 99
        return (long) (Math.pow(10, len) - 1);  //10^2 -1
    }

    private long plusOne(int len) {  // 99 -> 101
        return (long) (Math.pow(10, len) + 1); //10^2 + 1
    }
}
//leetcode submit region end(Prohibit modification and deletion)
