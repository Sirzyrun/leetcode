//有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，这些苹果将会在 days[i] 天后（也就
//是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] =
//= 0 表示。 
//
// 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。 
//
// 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。 
//
// 
//
// 示例 1： 
//
// 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
//输出：7
//解释：你可以吃掉 7 个苹果：
//- 第一天，你吃掉第一天长出来的苹果。
//- 第二天，你吃掉一个第二天长出来的苹果。
//- 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
//- 第四天到第七天，你吃的都是第四天长出来的苹果。
// 
//
// 示例 2： 
//
// 输入：apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
//输出：5
//解释：你可以吃掉 5 个苹果：
//- 第一天到第三天，你吃的都是第一天长出来的苹果。
//- 第四天和第五天不吃苹果。
//- 第六天和第七天，你吃的都是第六天长出来的苹果。
// 
//
// 
//
// 提示： 
//
// 
// apples.length == n 
// days.length == n 
// 1 <= n <= 2 * 10⁴ 
// 0 <= apples[i], days[i] <= 2 * 10⁴ 
// 只有在 apples[i] = 0 时，days[i] = 0 才成立 
// 
// Related Topics 贪心 数组 堆（优先队列） 👍 71 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int eatenApples(int[] apples, int[] days) {
        //     优先权队列  每天新产生的苹果入队 优先吃快腐烂的苹果
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);    // 按照 a[0] - b[0]  从小到达排列在优先权队列 较小的会先出队
        int n = apples.length, time = 0, ans = 0; // n为数组长度（n天之内会生成苹果） time为当前天数，ans为吃的苹果数
        while (time < n || !q.isEmpty()) { //当前还有苹果生成或者堆不为空 则可以继续吃苹果解 （time>=n 时虽然不生成新的苹果，但是依然有苹果未腐烂就可以吃）
            if (time < n && apples[time] > 0) { //过滤掉当天生成苹果数目为0的情况
                q.add(new int[]{time + days[time] - 1, apples[time]});//当日有新的苹果生成将其以二元组(最后可使用日期，当日产生的苹果数目)的形式加入小根堆中
            }
            while (!q.isEmpty() && q.peek()[0] < time) {    //peek 检索但不删除
                q.poll(); //从堆中取出最后使用日期最用可使用的苹果cur，如果堆顶元素过期则重复抛弃，知道找到可使用的cur
            }
            if (!q.isEmpty()) {
                int[] cur = q.poll();     //cur为最早可使用的出堆元素  pool检索并删除
                if (--cur[1] > 0 && cur[0] > time) {    // 吃掉一个cur苹果后 仍然有苹果剩余 并且今天过后仍然没有过期 则将cur重新入堆
                    q.add(cur);
                }
                ans++;
            }
            time++; //到下一天了
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
