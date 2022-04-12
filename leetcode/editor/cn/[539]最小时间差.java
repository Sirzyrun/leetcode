//给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：timePoints = ["23:59","00:00"]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：timePoints = ["00:00","23:59","00:00"]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 2 <= timePoints <= 2 * 10⁴ 
// timePoints[i] 格式为 "HH:MM" 
// 
// Related Topics 数组 数学 字符串 排序 👍 126 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*class Solution {     //时间复杂度太高 哈哈哈哈哈 测试用例多时 时间复杂度巨高
    public int findMinDifference(List<String> timePoints) {
        int num = timePoints.size();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < num; i++) {
            String s = timePoints.get(i);
            int h = Integer.parseInt(s.substring(0, 2));
            if (h == 0) {
                h = 24;
            }
            int m = Integer.parseInt(s.substring(3, 5));
            for (int j = i + 1; j < num; j++) {
                String ps = timePoints.get(j);
                int ph = Integer.parseInt(ps.substring(0, 2));
                if (ph == 0) {
                    ph = 24;
                }
                int pm = Integer.parseInt(ps.substring(3, 5));
                if (h > ph) {
                    int num1 = (h - ph) * 60 + m - pm;
                    res = num1 < res ? num1 : res;
                    int num2 = ((24 - h - 1)*60 +60 - m) + ph*60 +pm;
                    res = num2 < res ? num2 : res;
                }
                if (ph > h) {
                    int num1 = (ph - h) * 60 + pm - m;
                    res = num1 < res ? num1 : res;
                    int num2 = ((24 - ph - 1)*60 +60 - pm) + h*60 +m;
                    res = num2 < res ? num2 : res;
                }
                if (ph == h) {
                    int x = Math.abs(pm - m);
                    res = x < res ? x : res;
                }
            }
        }
        return res;
    }
}*/
class Solution {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        int t0Minutes = getMinutes(timePoints.get(0));
        int preMinutes = t0Minutes;
        for (int i = 1; i < timePoints.size(); ++i) {
            int minutes = getMinutes(timePoints.get(i));
            ans = Math.min(ans, minutes - preMinutes); // 相邻时间的时间差
            preMinutes = minutes;
        }
        ans = Math.min(ans, t0Minutes + 1440 - preMinutes); // 首尾时间的时间差
        // 24*60 = 1440
        return ans;
    }

    public int getMinutes(String t) {
        return ((t.charAt(0) - '0') * 10 + (t.charAt(1) - '0')) * 60 + (t.charAt(3) - '0') * 10 + (t.charAt(4) - '0');
        //  t.charAt(0) - '0' 是取t.charAt(0) 这个字符的unicode值
        // '1' - '0' 意思是1的Unicode值减去0的Unicode值  结果肯定也是1
        // 但是我们将'1'由字符型 转化为了  整型1
    }
}
//leetcode submit region end(Prohibit modification and deletion)
