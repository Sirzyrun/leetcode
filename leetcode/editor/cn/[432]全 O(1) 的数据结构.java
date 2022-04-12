//请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。 
//
// 实现 AllOne 类： 
//
// 
// AllOne() 初始化数据结构的对象。 
// inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。 
// dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例
//保证：在减少计数前，key 存在于数据结构中。 
// getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。 
// getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", 
//"getMinKey"]
//[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
//输出
//[null, null, null, "hello", "hello", null, "hello", "leet"]
//
//解释
//AllOne allOne = new AllOne();
//allOne.inc("hello");
//allOne.inc("hello");
//allOne.getMaxKey(); // 返回 "hello"
//allOne.getMinKey(); // 返回 "hello"
//allOne.inc("leet");
//allOne.getMaxKey(); // 返回 "hello"
//allOne.getMinKey(); // 返回 "leet"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= key.length <= 10 
// key 由小写英文字母组成 
// 测试用例保证：在每次调用 dec 时，数据结构中总存在 key 
// 最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 10⁴ 次 
// 
// Related Topics 设计 哈希表 链表 双向链表 👍 181 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 哈希（题目要求O（1））  +  双向链表（求最大最小 排序 最大放后面  最小放前面实现快速查找）
//  （排序需要把哈希和key连再一起 就需要用到双向链表）
class Node {
    Node prev;
    Node next;
    int val;   //  key对应的计数
    Set<String> keys;   // 相同的val可能有多个key

    public Node() {
        this.prev = null;
        this.next = null;
        this.val = 0;
        this.keys = new HashSet<>();
    }

    public Node(int val) {
        this.prev = null;
        this.next = null;
        this.val = val;
        this.keys = new HashSet<>();
    }

    public Node(String key, int val) {
        this.prev = null;
        this.next = null;
        this.val = val;
        this.keys = new HashSet<>();
        this.keys.add(key);
    }
}

class AllOne {
    // data
    Node head;
    Node tail;
    Map<String, Node> keyToNode;

    public AllOne() {
        this.head = new Node(Integer.MIN_VALUE);
        this.tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
        keyToNode = new HashMap<>();
    }

    public void inc(String key) {
        if (!keyToNode.containsKey(key)) {
            insertWith0(key);
        }
        incBy1(key);
    }

    public void incBy1(String key) {
        Node prevNode = keyToNode.get(key);
        int newVal = prevNode.val + 1;

        if (prevNode.next.val == newVal) {
            prevNode.next.keys.add(key);
            prevNode.keys.remove(key);
            keyToNode.put(key, prevNode.next);
        } else {
            Node newNode = new Node(key, newVal);
            prevNode.keys.remove(key);
            keyToNode.put(key, newNode);
            //insert
            newNode.next = prevNode.next;
            prevNode.next.prev = newNode;
            prevNode.next = newNode;
            newNode.prev = prevNode;
        }
        checkEmpty(prevNode);
    }

    private void checkEmpty(Node node) {
        if (node == head || node == tail || node.keys.size() != 0) {
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        //回收空间
        node.prev = null;
        node.next = null;
    }

    public void insertWith0(String key) {
        Node newNode = new Node(key, 0);
        // insert
        head.next.prev = newNode;
        newNode.next = head.next;
        newNode.prev = head;
        head.next = newNode;
    /*    newNode.next = head.next;
        head.next.prev = newNode;
        newNode.prev = head;
        head.next = newNode;*/
        // update keyToNode
        keyToNode.put(key, newNode);
    }

    public void dec(String key) {
        Node prevNode = keyToNode.get(key);
        int newVal = prevNode.val - 1;

        if (newVal == 0) {
            prevNode.keys.remove(key);
            keyToNode.remove(key);
        } else if (prevNode.prev.val == newVal) {
            prevNode.prev.keys.add(key);
            prevNode.keys.remove(key);
            keyToNode.put(key, prevNode.prev);
        } else {
            Node newNode = new Node(key, newVal);
            prevNode.keys.remove(key);
            keyToNode.put(key, newNode);
            // insert
            newNode.prev = prevNode.prev;
            prevNode.prev.next = newNode;
            newNode.next = prevNode;
            prevNode.prev = newNode;
        }
        checkEmpty(prevNode);
    }

    public String getMaxKey() {
        return tail.prev.keys.isEmpty() ? "" : tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        return head.next.keys.isEmpty() ? "" : head.next.keys.iterator().next();

    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
//leetcode submit region end(Prohibit modification and deletion)
