//给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。 
//
// 在 S 上反复执行重复项删除操作，直到无法继续删除。 
//
// 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。 
//
// 
//
// 示例： 
//
// 输入："abbaca"
//输出："ca"
//解释：
//例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又
//只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= S.length <= 20000 
// S 仅由小写英文字母组成。 
// 
// Related Topics 栈 字符串 👍 334 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*   public String removeDuplicates(String s) {
           Stack<Character> stack = new Stack<>();
           StringBuilder sb = new StringBuilder();
           int n = s.length();
           char[] c = s.toCharArray();
           for (int i = 0; i < n; i++) {
               if (stack.isEmpty()) {
                   stack.push(c[i]);
                   continue;
               }
               if (stack.peek() == c[i]) {
                   stack.pop();
                   continue;
               }
               stack.push(c[i]);
           }
           while (!stack.isEmpty()) {
               sb.append(stack.pop());
           }
           String resReverse = new String(sb);
           String ans = Reverse(resReverse);
           return ans;
       }

       public String Reverse(String s) {
           int n = s.length();
           char[] c = s.toCharArray();
           int l = 0, r = n - 1;
           while (l < r) {
               Character temp = c[r];
               c[r] = c[l];
               c[l] = temp;
               l++;
               r--;
           }
           return new String(c);
       }*/
    public String removeDuplicates(String s) {
        Deque<Character> deque = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (deque.isEmpty() || deque.peekLast() != s.charAt(i)) {
                deque.offerLast(s.charAt(i));  //压入栈中
            } else {  // 当前字符与栈顶字符相同
                deque.pollLast(); //弹出
            }
        }
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
