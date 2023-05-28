package linkedlist.doubly;

import linkedlist.BiNode;
import linkedlist.LinkedList;
import linkedlist.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DoublyLinkedList<E, K extends BiNode<E>, L extends DoublyLinkedList> extends LinkedList<E, K, L> {

    public BiNode<E> head;
    public BiNode<E> tail;

    @Override
    public void addAtFirst(E data) {
        if (isEmpty()) {
            this.head = this.tail = new BiNode<>(data, null, null);
        } else {
            this.head = new BiNode<>(data, this.head, null);
        }
        size++;
    }

    @Override
    public void addAtLast(E data) {
        if (isEmpty()) {
            addAtFirst(data);
        } else {
            if (this.tail != null) {
                BiNode<E> node = new BiNode<>(data, null, this.tail);
                this.tail.nxt = node;
                this.tail = node;
            }
            size++;
        }
    }

    @Override
    public void addAtFirst(K node) {
        if (isEmpty()) {
            this.head = this.tail = node;
        } else {
            node.nxt = this.head;
            this.head.prev = node;
            this.head = node;
        }
        size++;
    }

    @Override
    public void addAtLast(K node) {
        if (isEmpty()) {
            addAtFirst(node);
        } else {
            this.tail.nxt = node;
            node.prev = this.tail;
            tail = node;
            size++;
        }
    }

    @Override
    public void addAt(int idx, E data) {

        if (idx < 0 || idx > size) {
            System.out.println("Invalid Index");
            return;
        }

        if (isEmpty() || idx == 0) {
            addAtFirst(data);
        } else if (idx == size - 1) {
            addAtLast(data);
        } else {
            int cnt = 0;
            BiNode<E> cur = this.head;
            BiNode<E> pre = this.head;
            while (cur != null) {
                if (cnt == idx) {
                    BiNode<E> node = new BiNode<>(data);
                    pre.nxt = node;
                    node.prev = pre;
                    cur.prev = node;
                    node.nxt = cur;
                    size++;
                    return;
                }

                pre = cur;
                cur = cur.nxt;
                cnt++;
            }
        }

    }

    @Override
    public void addAt(int idx, K node) {
        if (idx < 0 || idx > size) {
            System.out.println("Invalid Index");
            return;
        }

        if (isEmpty() || idx == 0) {
            addAtFirst(node);
        } else if (idx == size - 1) {
            addAtLast(node);
        } else {
            int cnt = 0;
            BiNode<E> cur = this.head;
            BiNode<E> pre = this.head;
            while (cur != null) {
                if (cnt == idx) {
                    pre.nxt = node;
                    node.prev = pre;
                    cur.prev = node;
                    node.nxt = cur;
                    size++;
                    return;
                }

                pre = cur;
                cur = cur.nxt;
                cnt++;
            }
        }
    }

    @Override
    public void append(L other) {

        if (isEmpty()) {
            this.head = this.tail = other.head;
        } else {
            this.tail.nxt = other.head;
            other.head.prev = this.tail;
            this.tail = other.tail;
        }

        this.size += other.size;
    }

    @Override
    public void removeFirst() {
        if (isEmpty()) {
            System.out.println("LinkedList is Empty");
            return;
        }

        if (isHeadOnly()) {
            head = tail = null;
        } else {
            BiNode<E> ptr = this.head;
            head = ptr.nxt;
            ptr.prev = null;
        }

        size--;
    }

    @Override
    public void removeLast() {
        if (isEmpty()) {
            System.out.println("LinkedList is Empty");
            return;
        }

        if (isHeadOnly()) {
            removeFirst();
        } else {
            this.tail = this.tail.prev;
            this.tail.nxt = null;
            size--;
        }
    }

    @Override
    public void removeMiddle() {
        if (isEmpty()) {
            System.out.println("LinkedList is Empty");
        }

        removeAt(this.size / 2);
    }

    @Override
    public void removeAt(int idx) {
        if (isEmpty()) {
            System.out.println("LinkedList is Empty");
            return;
        }

        if (idx < 0 || idx > this.size) {
            System.out.println("Invalid Index");
            return;
        }

        if (idx == 0) {
            removeFirst();
        } else if (idx == this.size - 1) {
            removeLast();
        } else {
            int cnt = 0;
            BiNode<E> cur = this.head;
            BiNode<E> pre = this.head;
            while (cur != null) {
                if (cnt == idx) {
                    pre.nxt = cur.nxt;
                    if (cur.nxt != null) cur.nxt.prev = pre;
                    size--;
                    return;
                }
                pre = cur;
                cur = cur.nxt;
                cnt++;
            }
        }
    }

    @Override
    public void remove(K p) {
        if (isEmpty()) {
            System.out.println("LinkedList is Empty");
            return;
        }

        if (head == p) {
            removeFirst();
        } else if (tail == p) {
            removeLast();
        } else {
            BiNode<E> cur = head;
            BiNode<E> pre = head;

            while (cur.nxt != p) {
                pre = cur;
                cur = cur.nxt;
                if (cur == null) {
                    return;
                }
            }

            pre.nxt = cur.nxt;
            cur.prev = pre;
            size--;
        }
    }

    @Override
    public void removeIfDataIsEven() {

        if (!(head.data instanceof Integer)) {
            System.out.println("Not valid Generic");
            return;
        }

        if (isEmpty()) {
            System.out.println("LinkedList is Empty");
            return;
        }

        BiNode<E> cur = head;
        BiNode<E> pre = null;

        while (cur != null) {
            if ((Integer) cur.data % 2 == 0) {
                if (pre == null) {
                    if (cur.nxt != null) {
                        head = cur.nxt;
                        head.prev = null;
                    } else {
                        head = tail = null;
                    }
                } else {
                    pre.nxt = cur.nxt;
                    if (cur.nxt != null) {
                        cur.nxt.prev = pre;
                    } else {
                        tail = pre;
                    }
                }
                size--;
            } else {
                pre = cur;
            }
            cur = cur.nxt;
        }
    }

    @Override
    public void clear() {

        BiNode<E> ptr = this.head;

        while (ptr != null) {
            removeFirst();
            ptr = ptr.nxt;
        }

    }

    @Override
    public boolean contains(E data, Comparator<? super E> c) {
        if (isEmpty()) return false;

        BiNode<E> ptr = this.head;

        while (ptr != null) {
            if (c.compare(ptr.data, data) == 0) {
                return true;
            }
            ptr = ptr.nxt;
        }

        return false;
    }

    @Override
    public E getAt(int index) {
        if (isEmpty() || index < 0 || index >= this.size) return null;

        BiNode<E> ptr = this.head;
        int cnt = 0;

        while (ptr != null) {
            if (cnt == index) {
                return ptr.data;
            }
            ptr = ptr.nxt;
            cnt++;
        }

        return null;
    }

    @Override
    public E getAtFromBack(int index) {
        if (isEmpty() || index < 0 || index >= size) return null;
        return getAt(size - index - 1);
    }

    @Override
    public E getMiddle() {
        if (isEmpty()) return null;
        else return getAt(size / 2);
    }

    @Override
    public List<E> getAll() {
        BiNode<E> ptr = this.head;
        List<E> rst = new ArrayList<>();

        while (ptr != null) {
            rst.add(ptr.data);
            ptr = ptr.nxt;
        }

        return rst;
    }

    @Override
    public E getStartInCycle() {
        return null;
    }

    @Override
    public E getIntersection(L other) {
        return null;
    }

    @Override
    public void reverse() {

    }

    @Override
    public void deduplicate() {

    }

    @Override
    public boolean isPalindrome(Comparator<? super E> c) {
        return false;
    }

    @Override
    public K sum(K p) {
        return null;
    }

    @Override
    public K reverse(K node) {
        return null;
    }

    @Override
    public void dump() {
        Node<E> ptr = head;

        StringBuilder sb = new StringBuilder();
        while (ptr != null) {
            sb.append(ptr.data).append(" ");
            ptr = ptr.nxt;
        }
        System.out.println(sb);
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public boolean isHeadOnly() {
        return this.head.nxt == null;
    }

    @Override
    public E getHead() {
        return this.head.data;
    }

    @Override
    public E getTail() {
        return this.tail.data;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
