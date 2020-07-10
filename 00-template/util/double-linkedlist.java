class DoubleLinkedNode{
    int key;
    int value;
    DoubleLinkedNode pre;
    DoubleLinkedNode post;
}

private DoubleLinkedNode head, tail;

private void addNode(DoubleLinkedNode node) {
    node.pre = head;
    node.post = head.post;
    head.post.pre = node;
    head.post = node;
}

private DoubleLinkedNode removeNode(DoubleLinkedNode node) {
    node.post.pre = node.pre;
    node.pre.post = node.post;
    return node;
}

private void moveNode(DoubleLinkedNode node) {
    removeNode(node);
    addNode(node);
}

private DoubleLinkedNode removeLastNode() {
    DoubleLinkedNode node = tail.pre;
    return removeNode(node);
}