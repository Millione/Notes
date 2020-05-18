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
        ListNode pre = head;
        ListNode next = head.next.next;
        while (pre != next) {
            if (next == null || next.next == null) {
                return false;
            }
            pre = pre.next;
            next = next.next.next;
        }
        return true;
    }
}
// @lc code=end

