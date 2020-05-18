# 栈和队列

> 栈：后进先出；队列：先进先出

在使用JDK11相关的API接口时，Stack已经不被推荐使用了，因为它继承自Vector，是JDK1.0的产物，设计不够合理且性能低效。

因此，在使用栈和队列时，个人倾向于使用如下方式：

```java
// 当作为栈使用时，性能比Stack好；当作为队列使用时，性能比LinkedList好。
Deque<T> deque = new ArrayDeque<T>();

// stack
deque.push();
deque.peek();
deque.pop();

// queue
deque.offer();
deque.peek();
deque.pop();

// deque
deque.offerFirst();
deque.offerLast();
deque.peekFirst();
deque.peekLast();
deque.pollFirst();
deque.pollLast();
```

## ArrayDeque

### 1. offer

```java
public boolean offer(E e)
```

- **Parameters:**

  `e` - the element to add

- **Returns:**

  `true` (as specified by [`Queue.offer(E)`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html#offer(E)))

- **Throws:**

  `NullPointerException` - if the specified element is null

### 2. poll

```java
public E poll()
```

- **Returns:**

  the head of the queue represented by this deque, or `null` if this deque is empty

### 3. peek

```java
public E peek()
```

- **Returns:**

  the head of the queue represented by this deque, or `null` if this deque is empty

### 4. push

```java
public void push(E e)
```

- **Parameters:**

  `e` - the element to push

- **Throws:**

  `NullPointerException` - if the specified element is null

### 5. pop

```java
public E pop()
```

- **Returns:**

  the element at the front of this deque (which is the top of the stack represented by this deque)

- **Throws:**

  `NoSuchElementException` - if this deque is empty

## 题目

### 225. implement-stack-using-queues

------

**Description:**

Implement the following operations of a stack using queues.

- push(x) -- Push element x onto stack.
- pop() -- Removes the element on top of the stack.
- top() -- Get the top element.
- empty() -- Return whether the stack is empty.

**Example:**

```
MyStack stack = new MyStack();

stack.push(1);
stack.push(2);  
stack.top();   // returns 2
stack.pop();   // returns 2
stack.empty(); // returns false
```

**Notes:**

- You must use *only* standard operations of a queue -- which means only `push to back`, `peek/pop from front`, `size`, and `is empty` operations are valid.
- Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
- You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

[Discussion](https://leetcode.com/problems/implement-stack-using-queues/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/implement-stack-using-queues/solution/)

**Code:**

```java
import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode id=225 lang=java
 *
 * [225] Implement Stack using Queues
 */

// @lc code=start
class MyStack {
    private Queue<Integer> queue;
    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.offer(queue.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
// @lc code=end
```

### 232. implement-queue-using-stacks

------

**Description:**

Implement the following operations of a queue using stacks.

- push(x) -- Push element x to the back of queue.
- pop() -- Removes the element from in front of queue.
- peek() -- Get the front element.
- empty() -- Return whether the queue is empty.

**Example:**

```
MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);  
queue.peek();  // returns 1
queue.pop();   // returns 1
queue.empty(); // returns false
```

**Notes:**

- You must use *only* standard operations of a stack -- which means only `push to top`, `peek/pop from top`, `size`, and `is empty` operations are valid.
- Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
- You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).

[Discussion](https://leetcode.com/problems/implement-queue-using-stacks/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/implement-queue-using-stacks/solution/)

**Code:**

```java
import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode id=232 lang=java
 *
 * [232] Implement Queue using Stacks
 */

// @lc code=start
class MyQueue {
    private Deque<Integer> stack;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new ArrayDeque<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        Deque<Integer> temp = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        stack.push(x);
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return stack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
// @lc code=end
```

### 155. min-stack

------

**Description:**

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

- push(x) -- Push element x onto stack.
- pop() -- Removes the element on top of the stack.
- top() -- Get the top element.
- getMin() -- Retrieve the minimum element in the stack.

**Example:**

```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
```

[Discussion](https://leetcode.com/problems/min-stack/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/min-stack/solution/)

**Code:**

```java
import java.util.Deque;
import java.util.ArrayDeque;

/*
 * @lc app=leetcode id=155 lang=java
 *
 * [155] Min Stack
 */

// @lc code=start
class MinStack {
    private Deque<Integer> stack;
    private int minVal;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayDeque<>();
        minVal = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        if (x <= minVal) {
            stack.push(minVal);
            minVal = x;
        }
        stack.push(x);
    }
    
    public void pop() {
        if (stack.pop() == minVal) {
            if (stack.size() <= 1) {
                minVal = Integer.MAX_VALUE;
            } else {
                minVal = stack.pop();
            }
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minVal;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
// @lc code=end
```

## 参考

[ArrayDeque JDK11](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayDeque.html)