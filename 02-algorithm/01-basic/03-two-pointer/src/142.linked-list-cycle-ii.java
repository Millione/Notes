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
        ListNode next = head.next.next;
        while (pre != next) {
            if (next == null || next.next == null) {
                return null;
            }
            pre = pre.next;
            next = next.next.next;
        }
        // x = L + S  2x  L + S = kn 再走L步回到起点
        while (head != next) {
            head = head.next;
            next = next.next;
        }
        return head;
    }
}
// @lc code=end

