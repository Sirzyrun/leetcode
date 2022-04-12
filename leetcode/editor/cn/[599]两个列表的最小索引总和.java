//假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。 
//
// 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。 
//
// 
//
// 示例 1: 
//
// 
//输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = [
//"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
//输出: ["Shogun"]
//解释: 他们唯一共同喜爱的餐厅是“Shogun”。
// 
//
// 示例 2: 
//
// 
//输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC",
// "Shogun", "Burger King"]
//输出: ["Shogun"]
//解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= list1.length, list2.length <= 1000 
// 1 <= list1[i].length, list2[i].length <= 30 
// list1[i] 和 list2[i] 由空格 ' ' 和英文字母组成。 
// list1 的所有字符串都是 唯一 的。 
// list2 中的所有字符串都是 唯一 的。 
// 
// Related Topics 数组 哈希表 字符串 👍 160 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> hashMap1 = new HashMap<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        // list1 -> hashmap
        hashMap1 = getPlaceOfAndy(list1);
        // list1,list2 -> hashmap
        hashMap = getSamePlace(hashMap1, list2);
        // 遍历餐厅得到最小indexSum
        ArrayList<String> ansList = getMinIndexSum(hashMap);
        String[] ans = new String[ansList.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    public ArrayList<String> getMinIndexSum(HashMap<String, Integer> hashMap) {
        int min = Integer.MAX_VALUE;
        ArrayList<String> res = new ArrayList<>();
        for (String s : hashMap.keySet()) {
            if (hashMap.get(s) < min) {
                min = hashMap.get(s);
                res.clear();
                res.add(s);
            } else if (hashMap.get(s) == min) {
                res.add(s);
            }
        }
        return res;
    }

    public HashMap<String, Integer> getSamePlace(HashMap<String, Integer> hashMap, String[] list) {
        HashMap<String, Integer> res = new HashMap<>();
        int n = list.length;
        for (int i = 0; i < n; i++) {
            if (hashMap.containsKey(list[i])) {
                res.put(list[i], hashMap.get(list[i]) + i);
            }
        }
        return res;
    }

    public HashMap<String, Integer> getPlaceOfAndy(String[] list) {
        HashMap<String, Integer> res = new HashMap<>();
        int n = list.length;
        for (int i = 0; i < n; i++) {
            res.put(list[i], i);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
