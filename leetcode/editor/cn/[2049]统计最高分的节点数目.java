//给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。同时给你一个下标从 0 开始的整数数组 parents 表示这棵
//树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。 
//
// 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若
//干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。 
//
// 请你返回有 最高得分 节点的 数目 。 
//
// 
//
// 示例 1: 
//
// 
//
// 输入：parents = [-1,2,0,2,0]
//输出：3
//解释：
//- 节点 0 的分数为：3 * 1 = 3
//- 节点 1 的分数为：4 = 4
//- 节点 2 的分数为：1 * 1 * 2 = 2
//- 节点 3 的分数为：4 = 4
//- 节点 4 的分数为：4 = 4
//最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
// 
//
// 示例 2： 
//
// 
//
// 输入：parents = [-1,2,0]
//输出：2
//解释：
//- 节点 0 的分数为：2 = 2
//- 节点 1 的分数为：2 = 2
//- 节点 2 的分数为：1 * 1 = 1
//最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
// 
//
// 
//
// 提示： 
//
// 
// n == parents.length 
// 2 <= n <= 10⁵ 
// parents[0] == -1 
// 对于 i != 0 ，有 0 <= parents[i] <= n - 1 
// parents 表示一棵二叉树。 
// 
// Related Topics 树 深度优先搜索 数组 二叉树 👍 36 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 思路：
    // dfs(node) 返回节点个数
    //score = dfs(child1) * dfs(child2)*。。。* (N - 1 - dfs(child1) - dfs(child2))
    // score = product(dfs(children))* (N- 1 - sum(dfs(children) ) )

    // 变量考虑
    // node -> children  节点的所有子节点
    // node -> dfs()  重复计算保存起来
    // score -> cnt  题目要score的数目 需要一个score -> cnt 的映射  在dfs的时候传进去

    public int countHighestScoreNodes(int[] parents) {
        // var  定义变量
        Map<Integer, Set<Integer>> nodeToChildren = getNodeToChildren(parents);
        Map<Integer, Integer> nodeToCnt = new HashMap<>();
        Map<Long, Integer> scoreToCnt = new HashMap<>();
        //dfs
        dfs(nodeToChildren, nodeToCnt, scoreToCnt, 0, parents.length);
        // ans
        long highestScore = getLargest(scoreToCnt.keySet());
        return scoreToCnt.get(highestScore);
    }

    private int dfs(Map<Integer, Set<Integer>> nodeToChildren,   //计算以parent为根的子树的所有节点数
                    Map<Integer, Integer> nodeToCnt,
                    Map<Long, Integer> scoreToCnt,
                    int parent,
                    int size) {
        //memo  利用nodeToCnt避免重复计算
     /*   if (nodeToCnt.containsKey(parent)) {
            return nodeToCnt.get(parent);
        }*/
        //recur 递归的调用子节点 把score计算出来
        long score = 1L;
        int cnt = 0;  // 表示当前parent的所有子节点
        for (int child : nodeToChildren.getOrDefault(parent, new HashSet<>())) {  // 返回parent的所有孩子 没有返回一个空的hashset
            int numChild = dfs(nodeToChildren, nodeToCnt, scoreToCnt, child, size);
            score *= (long) numChild;
            cnt += numChild;
        }
        if (size - 1 - cnt > 0) {
            score *= (long) (size - 1 - cnt);
        }
        // update nodeTocnt
        nodeToCnt.put(parent, cnt + 1);  // 以parent为根的子树的所有节点数  +1 代表加上parent自身
        // update scoreToCnt
        scoreToCnt.put(score, scoreToCnt.getOrDefault(score, 0) + 1);
        // return ans
        return cnt + 1;

    }

    private long getLargest(Set<Long> set) {
        long ans = 0L;
        for (long l : set) {
            ans = Math.max(l, ans);
        }
        return ans;
    }

    private Map<Integer, Set<Integer>> getNodeToChildren(int[] parents) {
        Map<Integer, Set<Integer>> ans = new HashMap<>();
        for (int i = 0; i < parents.length; i++) {
            ans.putIfAbsent(parents[i], new HashSet<>());
            ans.get(parents[i]).add(i);
        }
        return ans;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
