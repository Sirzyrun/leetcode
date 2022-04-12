//n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。 
//
// 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。 
//
// 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。 
//
// 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。 
//
// 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中： 
//
// 
// dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧， 
// dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧， 
// dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。 
// 
//
// 返回表示最终状态的字符串。 
// 
//
// 示例 1： 
//
// 
//输入：dominoes = "RR.L"
//输出："RR.L"
//解释：第一张多米诺骨牌没有给第二张施加额外的力。
// 
//
// 示例 2： 
//
// 
//输入：dominoes = ".L.R...LR..L.."
//输出："LL.RR.LLRRLL.."
// 
//
// 
//
// 提示： 
//
// 
// n == dominoes.length 
// 1 <= n <= 10⁵ 
// dominoes[i] 为 'L'、'R' 或 '.' 
// 
// Related Topics 双指针 字符串 动态规划 👍 169 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //queue 存储上一秒倒下的domino
    //enqueue
    //dequeue 判断会不会推倒新的domino
    public String pushDominoes(String dominoes) {
        int len = dominoes.length();
        char[] ans = dominoes.toCharArray();

        //queue
        Queue<Integer> queue = new ArrayDeque<>(); //将上一秒倒下的domino入队其idx 因为可能影响其左右的domino

        // init
        for (int i = 0; i < len; i++) {
            if (dominoes.charAt(i) != '.') {
                queue.offer(i);
            }
        }

        //BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            Map<Integer, Integer> idxToForce = new HashMap<>(); // force > 0 , R

            while (size-- > 0) {  //一遍出队
                int idx = queue.poll();
                if (ans[idx] == '.') {
                    continue;
                }
                if (ans[idx] == 'L' && idx - 1 >= 0 && ans[idx - 1] == '.') {
                    idxToForce.putIfAbsent(idx - 1, 0);
                    idxToForce.put(idx - 1, idxToForce.get(idx - 1) - 1);
                }
                if (ans[idx] == 'R' && idx + 1 < len && ans[idx + 1] == '.') {
                    idxToForce.putIfAbsent(idx + 1, 0);
                    idxToForce.put(idx + 1, idxToForce.get(idx + 1) + 1);
                }
            }

            // 入队
            for (int idx : idxToForce.keySet()) {
                int force = idxToForce.get(idx);
                if (force != 0) {
                    ans[idx] = force > 0 ? 'R' : 'L';
                    queue.offer(idx);
                }
            }
        }

        return new String(ans);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
