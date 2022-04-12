//句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。 
//
// 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。 
//
// 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：s1 = "this apple is sweet", s2 = "this apple is sour"
//输出：["sweet","sour"]
// 
//
// 示例 2： 
//
// 
//输入：s1 = "apple apple", s2 = "banana"
//输出：["banana"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 200 
// s1 和 s2 由小写英文字母和空格组成 
// s1 和 s2 都不含前导或尾随空格 
// s1 和 s2 中的所有单词间均由单个空格分隔 
// 
// Related Topics 哈希表 字符串 👍 120 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        Set<String> set = new HashSet<String>(); //记录字符串并集
        Set<String> setEqual = new HashSet<String>();  // 记录下相同的字符串
        // 那么并集 减去 交集 就是 不常见的单词
        Set<String> setRes = new HashSet<String>();
        int n1 = s1.length();
        int n2 = s2.length();
        int k = 0;
        for (int i = 0; i < n1; i++) {
            int j = i;
            while (j < n1 && s1.charAt(j) != ' ') {
                j++;
            }
            if (set.contains(s1.substring(i,j))){
                setEqual.add(s1.substring(i, j));  //防止在s1中单词就已经不是常见的了
            }else {
                set.add(s1.substring(i, j));
            }
            i = j;
        }
        for (int i = 0; i < n2; i++) {
            int j = i;
            int x = set.size();
            while (j < n2 && s2.charAt(j) != ' ') {
                j++;
            }
            set.add(s2.substring(i, j));
            if (set.size() != x + 1) {
                setEqual.add(s2.substring(i, j));
            }
            i = j;
        }
        for (String s : set) {
            if (!setEqual.contains(s)) {
                setRes.add(s);
            }
        }
        final String[] res = convert(setRes);
        return res;
    }


    // 将 字符串集（Set）转换为字符串数组（String[]）的函数
    public static String[] convert(Set<String> setOfString) {
        // 创建和字符串集大小相同的一个空字符串数组
        String[] arrayOfString = new String[setOfString.size()];
        // 使用高级for循环，将字符串集合里的每个元素复制到字符串数组中。
        int index = 0;
        for (String str : setOfString)
            arrayOfString[index++] = str;
        // 返回字符串数组
        return arrayOfString;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
