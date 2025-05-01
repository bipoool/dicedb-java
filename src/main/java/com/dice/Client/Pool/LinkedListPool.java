package com.dice.Client.Pool;

import com.dice.Client.DiceDbClient.DiceDbClient;

public class LinkedListPool {

  private final int maxSize;

  private final ListNode head;
  private final ListNode tail;

  private int listSize;

  static class ListNode {

    public DiceDbClient client;
    public ListNode next;
    public ListNode prev;

    public ListNode(DiceDbClient client) {
      this.client = client;
    }

    public ListNode() {
    }
  }

  public LinkedListPool(int maxSize) {

    this.maxSize = maxSize;
    this.head = new ListNode();
    this.tail = new ListNode();

    this.head.next = this.tail;
    this.tail.prev = this.head;

    this.listSize = 0;

  }

  public DiceDbClient get() {
    if (this.listSize <= 0) {
      return null;
    }
    ListNode node = this.tail.prev;
    this.tail.prev = node.prev;
    node.prev.next = this.tail;
    this.listSize--;
    return node.client;
  }

  public void put(DiceDbClient connection) {
    if (this.listSize >= this.maxSize) {
      throw new RuntimeException("Pool is full");
    }
    ListNode node = new ListNode(connection);
    node.prev = this.tail.prev;
    node.next = this.tail;
    this.tail.prev.next = node;
    this.tail.prev = node;
    this.listSize++;
  }

  public void close() throws Exception {
    ListNode node = this.head.next;
    while (node != this.tail) {
      node.client.close();
      node = node.next;
    }
    this.head.next = this.tail;
    this.tail.prev = this.head;
    this.listSize = 0;
  }

  public int getSize() {
    return this.listSize;
  }

  public int getMaxSize() {
    return this.maxSize;
  }

  public boolean isEmpty() {
    return this.listSize <= 0;
  }

  public boolean isFull() {
    return this.listSize >= this.maxSize;
  }
}
