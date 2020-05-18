/*
 * @lc app=leetcode id=147 lang=java
 *
 * [147] Insertion Sort List
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
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        // 
        ListNode helper = new ListNode(0);
        // 当前要被插入的结点
        ListNode cur = head;
        // 插入位置在pre和pre.next之间
        ListNode pre = helper;
        // 下一个要插入的结点
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            // 找到正确插入的位置
            while (pre.next != null && cur.val >= pre.next.val) {
                pre = pre.next;
            }
            // 插入操作
            cur.next = pre.next;
            pre.next = cur;
            pre = helper;
            cur = next;
        }
        return helper.next;
    }
}
// @lc code=end

