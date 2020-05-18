# 链表

> 链表代码写得好坏，可以看出一个人写代码是否够细心，考虑问题是否全面，思维是否缜密。
>
> 不要吝啬使用指针记录。

我们可以用以下几个边界条件来检查链表代码是否正确：

1. 如果链表为空时，代码是否能正常工作？
2. 如果链表只包含一个结点时，代码是否能正常工作？
3. 如果链表只包含两个结点时，代码是否能正常工作？
4. 代码逻辑在处理头结点和尾结点的时候，是否能正常工作？

**先给出刷题调试用链表结构：**

```java
/**
 * 链表
 * https://coer.com
 *
 * @author LiuJ
 * @create 2020-02-25-1:55 PM
 */

public class ListNode {

    public int val;
    public ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    public ListNode(int[] arr){
        if(arr == null || arr.length == 0){
            throw new IllegalArgumentException("arr can not be empty");
        }
        this.val = arr[0];
        ListNode cur = this;
        for(int i = 1; i < arr.length; i++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public  String toString(){
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while(cur != null){
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
```

## 实现

### 1. 基本构造

```java
public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    // 获取链表中的元素个数
    public int getSize(){
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }
}
```

### 2. 增加元素

```java
	// 在链表的index(0-based)位置添加新的元素e
    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        Node prev = dummyHead;
        for(int i = 0 ; i < index ; i ++)
            prev = prev.next;

        prev.next = new Node(e, prev.next);
        size ++;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e){
        add(0, e);
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e){
        add(size, e);
    }
```

### 3. 删除元素

```java
	// 从链表中删除index(0-based)位置的元素, 返回删除的元素
    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        Node prev = dummyHead;
        for(int i = 0 ; i < index ; i ++)
            prev = prev.next;

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;

        return retNode.e;
    }

    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    // 从链表中删除元素e
    public void removeElement(E e){

        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }
```

### 4. 修改元素

```java
    // 修改链表的第index(0-based)个位置的元素为e
    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Illegal index.");

        Node cur = dummyHead.next;
        for(int i = 0 ; i < index ; i ++)
            cur = cur.next;
        cur.e = e;
    }
```

### 5. 查询元素

```java
    // 获得链表的第index(0-based)个位置的元素
    public E get(int index){

        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");

        Node cur = dummyHead.next;
        for(int i = 0 ; i < index ; i ++)
            cur = cur.next;
        return cur.e;
    }

    // 获得链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    // 查找链表中是否有元素e
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }
```

## LinkedList

### 1. peek

```java
public E peek()
```

- **Specified by:**

  `peek` in interface `Deque`

- **Specified by:**

  `peek` in interface `Queue`

- **Returns:**

  the head of this list, or `null` if this list is empty

### 2. poll

```java
public E poll()
```

- **Specified by:**

  `poll` in interface `Deque`

- **Specified by:**

  `poll` in interface `Queue`

- **Returns:**

  the head of this list, or `null` if this list is empty

### 3. offer

```java
public boolean offer(E e)
```

- **Specified by:**

  `offer` in interface `Deque`

- **Specified by:**

  `offer` in interface `Queue`

- **Parameters:**

  `e` - the element to add

- **Returns:**

  `true` (as specified by [`Queue.offer(E)`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html#offer(E)))

### 4. offerFirst

```java
public boolean offerFirst(E e)
```

- **Specified by:**

  `offerFirst` in interface `Deque`

- **Parameters:**

  `e` - the element to insert

- **Returns:**

  `true` (as specified by [`Deque.offerFirst(E)`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Deque.html#offerFirst(E)))

### 5. offerLast

```java
public boolean offerLast(E e)
```

- **Specified by:**

  `offerLast` in interface `Deque`

- **Parameters:**

  `e` - the element to insert

- **Returns:**

  `true` (as specified by [`Deque.offerLast(E)`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Deque.html#offerLast(E)))

### 6. peekFirst

```java
public E peekFirst()
```

- **Specified by:**

  `peekFirst` in interface `Deque`

- **Returns:**

  the first element of this list, or `null` if this list is empty

### 7. peekLast

```java
public E peekLast()
```

- **Specified by:**

  `peekLast` in interface `Deque`

- **Returns:**

  the last element of this list, or `null` if this list is empty

### 8. pollFirst

```java
public E pollFirst()
```

- **Specified by:**

  `pollFirst` in interface `Deque`

- **Returns:**

  the first element of this list, or `null` if this list is empty

### 9. pollLast

```java
public E pollLast()
```

- **Specified by:**

  `pollLast` in interface `Deque`

- **Returns:**

  the last element of this list, or `null` if this list is empty

### 10. push

```java
public void push(E e)
```

- **Specified by:**

  `push` in interface `Deque`

- **Parameters:**

  `e` - the element to push

### 11. pop

```java
public E pop()
```

- **Specified by:**

  `pop` in interface `Deque`

- **Returns:**

  the element at the front of this list (which is the top of the stack represented by this list)

- **Throws:**

  `NoSuchElementException` - if this list is empty

## 题目

### 707. design-linked-list

------

**Description:**

Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: `val` and `next`. `val` is the value of the current node, and `next` is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute `prev` to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:

- `get(index)` : Get the value of the `index`-th node in the linked list. If the index is invalid, return `-1`.
- `addAtHead(val)` : Add a node of value `val` before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
- `addAtTail(val)` : Append a node of value `val` to the last element of the linked list.
- `addAtIndex(index, val)` : Add a node of value `val` before the `index`-th node in the linked list. If `index` equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
- `deleteAtIndex(index)` : Delete the `index`-th node in the linked list, if the index is valid.

**Example:**

```
Input: 
["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
[[],[1],[3],[1,2],[1],[1],[1]]
Output:  
[null,null,null,null,2,null,3]

Explanation:
MyLinkedList linkedList = new MyLinkedList(); // Initialize empty LinkedList
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3
```

**Constraints:**

- `0 <= index,val <= 1000`
- Please do not use the built-in LinkedList library.
- At most `2000` calls will be made to `get`, `addAtHead`, `addAtTail`, `addAtIndex` and `deleteAtIndex`.

------

[Discussion](https://leetcode.com/problems/design-linked-list/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/design-linked-list/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=707 lang=java
 *
 * [707] Design Linked List
 */

// @lc code=start
class MyLinkedList {
    
    class Node {
        int val;
        Node next;
        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
        public Node(int val) {
            this(val, null);
        }
        public Node() {
            this(-1, null);
        }
    }
    
    private Node dummy;
    private int size;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        dummy = new Node();
        size = 0;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node cur = dummy.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        Node pre = dummy;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = new Node(val, pre.next);
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        Node pre = dummy;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node retNode = pre.next;
        pre.next = retNode.next;
        retNode.next = null;
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
// @lc code=end
```

### 141. linked-list-cycle

------

**Description:**

Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer `pos` which represents the position (0-indexed) in the linked list where tail connects to. If `pos` is `-1`, then there is no cycle in the linked list.

**Example 1:**

```
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

**Example 2:**

```
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

**Example 3:**

```
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

**Follow up:**

Can you solve it using *O(1)* (i.e. constant) memory?

[Discussion](https://leetcode.com/problems/linked-list-cycle/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/linked-list-cycle/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=141 lang=java
 *
 * [141] Linked List Cycle
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode pre = head, cur = head.next.next;
        while (pre != cur) {
            if (cur == null || cur.next == null) {
                return false;
            }
            pre = pre.next;
            cur = cur.next.next;
        }
        return true;
    }
}
// @lc code=end
```

### 142. linked-list-cycle-ii

------

**Description:**

Given a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

To represent a cycle in the given linked list, we use an integer `pos` which represents the position (0-indexed) in the linked list where tail connects to. If `pos` is `-1`, then there is no cycle in the linked list.

**Note:** Do not modify the linked list.

**Example 1:**

```
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

**Example 2:**

```
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

**Example 3:**

```
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

**Follow-up**:
Can you solve it without using extra space?

[Discussion](https://leetcode.com/problems/linked-list-cycle-ii/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/linked-list-cycle-ii/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=142 lang=java
 *
 * [142] Linked List Cycle II
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode pre = head.next;
        ListNode cur = head.next.next;
        while (pre != cur) {
            if (cur == null || cur.next == null) {
                return null;
            }
            pre = pre.next;
            cur = cur.next.next;
        }
        while (head != cur) {
            head = head.next;
            cur = cur.next;
        }
        return head;
    }
}
// @lc code=end
```

### 234. palindrome-linked-list

------

**Description:**

Given a singly linked list, determine if it is a palindrome.

**Example 1:**

```
Input: 1->2
Output: false
```

**Example 2:**

```
Input: 1->2->2->1
Output: true
```

**Follow up:**
Could you do it in O(n) time and O(1) space?

[Discussion](https://leetcode.com/problems/palindrome-linked-list/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/palindrome-linked-list/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=234 lang=java
 *
 * [234] Palindrome Linked List
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null){
            slow = slow.next;
        }
        ListNode reHead = reverse(slow);
        while(reHead != null){
            if(head.val != reHead.val){
                return false;
            }
            head = head.next;
            reHead = reHead.next;
        }
        return true;
    }
    private ListNode reverse(ListNode head){
        ListNode pre = null;
        while(head != null){
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }
}
// @lc code=end
```

## 参考

[LinkedList JDK11](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html)

[LeetCode](https://leetcode.com/explore/learn/card/linked-list/)