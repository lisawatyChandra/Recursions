package recursion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * A bare-bones singly-linked list implementation.
 */
public class LinkedList {

    private Node head;
    private Node tail;

    /**
     * Constructs a new, empty linked list.
     */
    public LinkedList() {
        // linked list is currently empty since
        // both head and tail nodes are pointing to null
        head = null;
        tail = null;
    }

    /**
     * Add an entry as the new first element in the list.
     * @param data The new value to add at the head of the list
     */
    public void addFirst(final Double data) {
        // instantiates a new node initialized with
        // passed in data, and current head node as the next node
        Node newHead = new Node(data, head);
        // reassign head node to point to this new node
        head = newHead;
        // if tail node points to null (this means there is only
        // one node in the current instance of linked list
        // so reassign tail node to point to this new node as well
        if (tail == null) {
            tail = newHead;
        }
    }

    /**
     * Add an entry as the new last element in the list.
     * @param data The new value to add at the tail of the list
     */
    public void addLast(final Double data) {
        // instantiates a new node initialized with passed in data
        // and null as its next node
        Node node = new Node(data, null);

        // if current instance of linked list is an empty list,
        // which means its head node is null, and by extension
        // its tail  node is also null, then reassign head node
        // to this new node, and reassign tail node to the same
        // new node that head node was reassigned to
        if (head == null) {
            head = node;
            tail = head;
        } else {
            // otherwise, assign this new node as the new tail node
            // by calling `Node#setNext()` on the current tail node
            tail.setNext(node);
            // then, reassign tail node to point to the new tail node
            tail = tail.getNext();
        }
    }

    /**
     * Returns the value of the first element in the list.
     * Throws IndexOutOfBoundsException if the list is empty.
     * @return the first value, or null if list is empty
     */
    public Double getFirst() {
        return get(0);
    }

    /**
     * Returns the value of the Nth element in the list (zero-indexed).
     * Throws IndexOutOfBoundsException if n is size of list or
     * greater, or if n is negative.
     *
     * We normally would not want to call this method often on a linked
     * list because it is O(n), but this might be helpful for your
     * reverse() unit tests.
     *
     * @param n the index of the element to return data from
     * @return The value at that element, if the element exists
     */
    public Double get(int n) {
        Node node = head;
        for (int i = 0; i < n; i++) {
            if (node == null) {
                break;
            }
            node = node.getNext();
        }
        if (node == null) {
            throw new IndexOutOfBoundsException("n was too big for this list: " + n);
        }

        return node.getData();
    }

    /**
     * Add the collection's data values to the end of the list.
     * @param collection The collection of Double to add to the list
     */
    public void addAll(final Collection<Double> collection) {
        for (Double data : collection) {
            addLast(data);
        }
    }

    /**
     * Computes and returns the sum of all Double elements in the list.
     * @return The sum of all elements in the list
     */
    public Double sum() {
        // PARTICIPANTS: Use your GILA answers to **recursively**
        // calculate the sum of this AtaLinkedList
        return sumRecursive(head);
    }

    private Double sumRecursive(Node head) {
        if (head == null) {
            return 0.0;
        }

        return head.getData() + sumRecursive(head.getNext());
    }

/**
 * Creates a new AtaLinkedList that has all of the values of this
 * AtaLinkedList in reverse order.
 * @return a new reverse order list
 */
public LinkedList reverse() {
    // PARTICIPANTS: Use your GILA answers to **recursively**
    // create the reversed AtaLinkedList

    return reverseRecursive(head);
}

private LinkedList reverseRecursive(Node head) {
    if (head == null) {
        return new LinkedList();
    }

    LinkedList reversedList = reverseRecursive(head.getNext());
    reversedList.addLast(head.getData());
    return reversedList;
}

    // EXTENSION
    /**
     * Calculates the size of the linked list. The number of nodes
     * with data.
     * @return size of the linked list
     */
    public int size() {
        return sizeRecursive(head);
    }

    private int sizeRecursive(Node head) {
        if (head == null) {
            return 0;
        }

        return 1 + sizeRecursive(head.getNext());
    }

    // EXTENSION
    /**
     * Determines if the list contains a node with a double equal
     * to the number specified.
     * @param number to check for in the list
     * @return true, if the list contains the number.
     */
    public boolean contains(Double number) {
        return containsRecursive (head, number);
    }

    private boolean containsRecursive(Node head, Double number) {
        if (head == null) {
            return false;
        }

        if (head.getData().equals(number)) {
            return true;
        } else {
            return containsRecursive(head.getNext(), number);
        }
    }

    // EXTENSION
    /**
     * Computes and returns the max of all Double elements in the list.
     * @return The max double in the list.
     */
    public Double max() {
        return maxRecursive(head.getData(), head.getNext());
    }

    private Double maxRecursive(Double thisNodeData, Node nextNode) {
        // assume current node's data is the max value
        Double maxDouble = thisNodeData;

        // when next node is null, this means either
        // current node is the only node in the linked list,
        // in which case return current node's value as the max value,
        // or we've reached the end of the linked list, in which case
        // return the value currently stored in the max
        // local variable
        if (nextNode == null) {
            return maxDouble;
        }

        // if next node's value is greater than the value
        // currently stored in the max local variable
        // reassign the max local variable to point
        // to next node's value
        if (nextNode.getData() > maxDouble) {
            maxDouble = nextNode.getData();
            // then continue to step through subsequent nodes
            // after having updated current max value
            maxRecursive(maxDouble, nextNode.getNext());
        }

        // otherwise, continue to step through subsequent nodes
        // in the linked list with current max value
        // until either next node is null
        // or next node's value is greater than the current max value
        // in which case the second if condition will execute
        return maxRecursive(maxDouble, nextNode.getNext());
    }

    // EXTENSION
    @Override
    public int hashCode() {
        List<Double> values = new ArrayList<>();
        Node node = head;
        while (node != null) {
            values.add(node.getData());
            node = node.getNext();
        }
        return Objects.hash(values.toArray());
    }

    // EXTENSION
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LinkedList other = (LinkedList) o;
        Node thisNode = this.head;
        Node otherNode = other.head;
        while (thisNode != null && otherNode != null) {
            if (!thisNode.getData().equals(otherNode.getData())) {
                return false;
            }
            thisNode = thisNode.getNext();
            otherNode = otherNode.getNext();
        }

        if (thisNode == null && otherNode == null) {
            return true;
        }

        return false;
    }

    // EXTENSION
    @Override
    public String toString() {
        Node node = head;
        StringBuilder stringBuilder = new StringBuilder("[");

        while (node != null) {
            stringBuilder.append(node.getData())
                .append(", ");
            node = node.getNext();
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LinkedList linkedList1 = new LinkedList();

        // double value = RANDOM.nextDouble();
        linkedList1.addFirst(1.1);
        linkedList1.addFirst(3.3);
        linkedList1.addFirst(2.2);

        System.out.println(linkedList1);

        Double sum = linkedList1.sum();
        System.out.println(sum);

        System.out.println(linkedList1.reverse());
        System.out.println(linkedList1.size());
        System.out.println(linkedList1.contains(3.6));
        System.out.println(linkedList1.contains(3.3));


        LinkedList linkedList2 = new LinkedList();
        final Random RANDOM = new Random();
        linkedList2.addFirst(RANDOM.nextDouble());
        linkedList2.addFirst(RANDOM.nextDouble());
        linkedList2.addFirst(RANDOM.nextDouble());
        linkedList2.addFirst(RANDOM.nextDouble());
        linkedList2.addFirst(RANDOM.nextDouble());
        linkedList2.addFirst(RANDOM.nextDouble());
        linkedList2.addFirst(RANDOM.nextDouble());
        linkedList2.addFirst(RANDOM.nextDouble());
        System.out.println(linkedList2);
        System.out.println(
            String.format("%.3f", linkedList2.max()));
    }
}
