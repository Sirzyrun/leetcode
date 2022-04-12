//你有 k 个服务器，编号为 0 到 k-1 ，它们可以同时处理多个请求组。每个服务器有无穷的计算能力但是 不能同时处理超过一个请求 。请求分配到服务器的规则
//如下： 
//
// 
// 第 i （序号从 0 开始）个请求到达。 
// 如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。 
// 如果第 (i % k) 个服务器空闲，那么对应服务器会处理该请求。 
// 否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。比方说，如果第 i 个服务器在忙，那
//么会查看第 (i+1) 个服务器，第 (i+2) 个服务器等等。 
// 
//
// 给你一个 严格递增 的正整数数组 arrival ，表示第 i 个任务的到达时间，和另一个数组 load ，其中 load[i] 表示第 i 个请求的工作
//量（也就是服务器完成它所需要的时间）。你的任务是找到 最繁忙的服务器 。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。 
//
// 请你返回包含所有 最繁忙服务器 序号的列表，你可以以任意顺序返回这个列表。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3] 
//输出：[1] 
//解释：
//所有服务器一开始都是空闲的。
//前 3 个请求分别由前 3 台服务器依次处理。
//请求 3 进来的时候，服务器 0 被占据，所以它被安排到下一台空闲的服务器，也就是服务器 1 。
//请求 4 进来的时候，由于所有服务器都被占据，该请求被舍弃。
//服务器 0 和 2 分别都处理了一个请求，服务器 1 处理了两个请求。所以服务器 1 是最忙的服务器。
// 
//
// 示例 2： 
//
// 
//输入：k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
//输出：[0]
//解释：
//前 3 个请求分别被前 3 个服务器处理。
//请求 3 进来，由于服务器 0 空闲，它被服务器 0 处理。
//服务器 0 处理了两个请求，服务器 1 和 2 分别处理了一个请求。所以服务器 0 是最忙的服务器。
// 
//
// 示例 3： 
//
// 
//输入：k = 3, arrival = [1,2,3], load = [10,12,11]
//输出：[0,1,2]
//解释：每个服务器分别处理了一个请求，所以它们都是最忙的服务器。
// 
//
// 示例 4： 
//
// 
//输入：k = 3, arrival = [1,2,3,4,8,9,10], load = [5,2,10,3,1,2,2]
//输出：[1]
// 
//
// 示例 5： 
//
// 
//输入：k = 1, arrival = [1], load = [1]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= 10⁵ 
// 1 <= arrival.length, load.length <= 10⁵ 
// arrival.length == load.length 
// 1 <= arrival[i], load[i] <= 10⁹ 
// arrival 保证 严格递增 。 
// 
// Related Topics 贪心 数组 有序集合 堆（优先队列） 👍 100 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // ******   data struct  **********
        int[] count = new int[k];
        TreeSet<Integer> available = initAvailable(k);    // 空余的服务器
        //  busy 第一个元素int[0]结束工作的时间   第二个元素int[1]服务器编号  从小到大排列
        PriorityQueue<int[]> busy = new PriorityQueue<>((o1,o2) -> o1[0]-o2[0] );
        // process
        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            // maintain busy (-> available)
            while (!busy.isEmpty() && busy.peek()[0] <= currTime) {
                available.add(busy.poll()[1]);
            }
            // find server (maintain busy, available, count)
            if (available.isEmpty()) {
                continue;
            }
                  //  ceiling(E e) 返回此集合中最小元素大于或等于给定元素，如果没有此元素，则 null 。
            Integer server = available.ceiling(i % k);
            if (server == null) {
                server = available.first();
            }
            available.remove(server);
            busy.offer(new int[]{currTime + load[i], server});
            count[server]++;
        }
        return findAns(count);

    }

    private List<Integer> findAns(int[] count) {
        List<Integer> ans = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < count.length; i++) {
            if (max == count[i]) {
                ans.add(i);
            } else if (max < count[i]) {
                max = count[i];
                ans = new ArrayList<>(Arrays.asList(i));
            }
        }
        return ans;
    }

    private TreeSet<Integer> initAvailable(int k) {
        TreeSet<Integer> ans = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            ans.add(i);
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
