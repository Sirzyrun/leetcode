//给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。 
//
// 输入为三个整数：day、month 和 year，分别表示日、月、年。 
//
// 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", 
//"Friday", "Saturday"}。 
//
// 
//
// 示例 1： 
//
// 输入：day = 31, month = 8, year = 2019
//输出："Saturday"
// 
//
// 示例 2： 
//
// 输入：day = 18, month = 7, year = 1999
//输出："Sunday"
// 
//
// 示例 3： 
//
// 输入：day = 15, month = 8, year = 1993
//输出："Sunday"
// 
//
// 
//
// 提示： 
//
// 
// 给出的日期一定是在 1971 到 2100 年之间的有效日期。 
// 
// Related Topics 数学 👍 61 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 1971年最后一天是周四
    static String[] s = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    static int[] nums = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public String dayOfTheWeek(int day, int month, int year) {
        int answer = 4;
        for (int i = 1971; i < year; i++) {
            boolean isLeep = (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
            answer += isLeep ? 366 : 365;
        }
        for (int i = 1;i<month;i++){
            answer += nums[i-1];
            if(i == 2 &&((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)){
                answer++;
            }
        }
        answer += day;
        return s[answer%7];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
