//复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件： 
//
// 
// 实部 是一个整数，取值范围是 [-100, 100] 
// 虚部 也是一个整数，取值范围是 [-100, 100] 
// i² == -1 
// 
//
// 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：num1 = "1+1i", num2 = "1+1i"
//输出："0+2i"
//解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
// 
//
// 示例 2： 
//
// 
//输入：num1 = "1+-1i", num2 = "1+-1i"
//输出："0+-2i"
//解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。 
// 
//
// 
//
// 提示： 
//
// 
// num1 和 num2 都是有效的复数表示。 
// 
// Related Topics 数学 字符串 模拟 👍 89 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 方法一
    /*public String complexNumberMultiply(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();
        int num1AddIndex = 0, num2AddIndex = 0;
        int ansReal = 0, ansVirtual = 0;
        String ans;
        for (int i = 0; i < n1; i++) {
            if (num1.charAt(i) == '+') {
                num1AddIndex = i;
                break;
            }
        }
        for (int i = 0; i < n2; i++) {
            if (num2.charAt(i) == '+') {
                num2AddIndex = i;
                break;
            }
        }
        int num1Real = Integer.parseInt(num1.substring(0, num1AddIndex));
        int num1Virtual = Integer.parseInt(num1.substring(num1AddIndex + 1, n1-1));
        int num2Real = Integer.parseInt(num2.substring(0, num2AddIndex));
        int num2Virtual = Integer.parseInt(num2.substring(num2AddIndex + 1, n2-1));
        ansReal = num1Real * num2Real - num1Virtual * num2Virtual;
        ansVirtual = num1Real * num2Virtual + num1Virtual * num2Real;
        ans = ansReal + "+" + ansVirtual + "i";
        return ans;
    }*/
    //方法二
    public String complexNumberMultiply(String num1, String num2) {
        int r1 = Integer.parseInt(num1.split("\\+|i")[0]);  //num1 实部
        int i1 = Integer.parseInt(num1.split("\\+|i")[1]);  //num1 虚部
        int r2 = Integer.parseInt(num2.split("\\+|i")[0]);  //num2 实部
        int i2 = Integer.parseInt(num2.split("\\+|i")[1]);  //num2 虚部
        int ansReal = r1*r2 - i1*i2;
        int ansVirtual = r1*i2 + i1*r2;
        return Integer.toString(ansReal)+"+"+Integer.toString(ansVirtual)+"i";
    }
}
//leetcode submit region end(Prohibit modification and deletion)
