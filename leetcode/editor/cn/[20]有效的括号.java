//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 👍 2875 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//确定解题思路：遇到右括号，就比较它和前一个元素是否是一对儿，前一个元素没有或者不是一对儿，则return false。
//        选择数据结构：按照第一步确定的解题思路，需要将元素一个一个的塞进去，碰到右括号就拿出来前一个，如果是一对儿就移除这一对儿，栈的数据结构符合这个问题的需求。
//        编码实现：注意考虑边界值，本题中是指最终剩下来的全部元素都是左括号。
class Solution {
    public boolean isValid(String s) {
        int length = s.length();
        if (length % 2 != 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<Character, Character>(3);
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                if (stack.isEmpty() || !map.get(c).equals(stack.pop())) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
    return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
