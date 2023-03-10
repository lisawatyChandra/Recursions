package recursion;

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
    public void addAll(final LinkedList collection) {
        for (int i = 0; i < collection.size(); i++) {
            addLast(collection.get(i));
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
        // if current instance of linked list is an empty list
        if (head == null) {
            return 0.0;
        }

        // otherwise, extract value from the head node
        // of current instance of linked list, and sum that
        // to the value in the next node of current instance of
        // linked list
        return head.getData() + sumRecursive(head.getNext());
    }

/**
 * Creates a new AtaLinkedList that has all the values of this
 * AtaLinkedList in reverse order.
 * @return a new reverse order list
 */
public LinkedList reverse() {
    // PARTICIPANTS: Use your GILA answers to **recursively**
    // create the reversed AtaLinkedList

    return reverseRecursive(head);
}

private LinkedList reverseRecursive(Node head) {
    // if the passed in node is null, then we've
    // reached the end of the current instance of
    // linked list
    if (head == null) {
        return new LinkedList();
    }

    // keep stepping through current instance of linked list
    LinkedList reversedList = reverseRecursive(head.getNext());
    // once we've reached the end of the current instance
    // of linked list, which means we hit the base case
    // of `head == null`, where we are given a new instance
    // of an empty linked list, then we start traversing
    // the callstack backward as we pop each stack frame
    // off the stack.
    // Then we add the passed in node's data to the end
    // of the list by calling `LinkedList#addLast()`
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
        // if current instance of linked list is an empty list
        if (head == null) {
            // pops the stack frame off the stack
            return 0;
        }

        // increment size of sublist by one
        return 1 + sizeRecursive(head.getNext());
    }

    // EXTENSION
    /**
     * Determines if the list contains a node with a double value
     * that equals the double value passed in as argument
     * to the method call.
     * @param number to check for in the list
     * @return true, if the list contains the number.
     */
    public boolean contains(Double number) {
        return containsRecursive (head, number);
    }

    private boolean containsRecursive(Node head, Double number) {
        // if current instance of linked list is an empty list,
        // or we've reached the end of current instance of
        // linked list
        if (head == null) {
            return false;
        }

        // if the double value contained in the passed-in
        // node equals the double value passed in as argument
        // to the method call
        if (head.getData().equals(number)) {
            return true;
        } else {
            // otherwise, check if the next node contains
            // the double value that equals the double value
            // passed in to the method call
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
//        List<Double> values = new ArrayList<>();
//        Node node = head;
//        while (node != null) {
//            values.add(node.getData());
//            node = node.getNext();
//        }

        return hashCodeRecursive(head);
    }

    private int hashCodeRecursive(Node node) {
        if (node == null) {
            return 0;
        }

        // Question: how many hash codes do we end up having?
        return Objects.hash(node.getData(), hashCodeRecursive(node.getNext()));
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
//        Node thisNode = this.head;
//        Node otherNode = other.head;
//        while (thisNode != null && otherNode != null) {
//            if (!thisNode.getData().equals(otherNode.getData())) {
//                return false;
//            }
//            thisNode = thisNode.getNext();
//            otherNode = otherNode.getNext();
//        }

        return equalsRecursive(this.head, other.head);
    }

    private boolean equalsRecursive(Node thisNode, Node otherNode) {
        if (thisNode == null || otherNode == null) {
            return false;
        }

        if (!thisNode.getData().equals(otherNode.getData())) {
            return false;
        }

        return equalsRecursive(thisNode.getNext(), otherNode.getNext());
    }

    // EXTENSION
    @Override
    public String toString() {
//        Node node = head;
//        StringBuilder stringBuilder = new StringBuilder("[");
//
//        while (node != null) {
//            stringBuilder.append(node.getData())
//                .append(", ");
//            node = node.getNext();
//        }
//        stringBuilder.append("]");
//
//        return stringBuilder.toString();

        return String.format("[%s]", toStringRecursive(head));
    }

    private String toStringRecursive(Node node) {
        if (node == null) {
            return "";
        }

        return String.format("%.4f, %s", node.getData(), toStringRecursive(node.getNext()));
    }

    public static void main(String[] args) {
        LinkedList linkedList1 = new LinkedList();

        // double value = RANDOM.nextDouble();
        linkedList1.addFirst(1.1);
        linkedList1.addFirst(3.3);
        linkedList1.addFirst(2.2);

        System.out.println(linkedList1);

        Double sum = linkedList1.sum();
        System.out.printf("%.4f%n", sum);

        System.out.println(linkedList1.reverse());
        System.out.println(linkedList1.size());
        System.out.println(linkedList1.contains(3.6));
        System.out.println(linkedList1.contains(3.3));


        LinkedList linkedList2 = new LinkedList();
        final Random RANDOM = new Random();
        linkedList2.addFirst(2.2 + RANDOM.nextDouble());
        linkedList2.addFirst(3.1 + RANDOM.nextDouble());
        linkedList2.addFirst(2.2 + RANDOM.nextDouble());
        linkedList2.addFirst(RANDOM.nextDouble());
        linkedList2.addFirst(2.2 + RANDOM.nextDouble());
        linkedList2.addFirst(2.5 + RANDOM.nextDouble());
        linkedList2.addFirst(2.2 + RANDOM.nextDouble());
        linkedList2.addFirst(2.2 + RANDOM.nextDouble());
        System.out.println(linkedList2);

        linkedList2.addAll(linkedList1);
        System.out.println(linkedList2);
        System.out.printf("%.4f%n", linkedList2.getFirst());
        System.out.printf("%.4f%n", linkedList2.max());
    }
}
