public class Node <X> {
    public Node<X> prev;
    public X data;
    public Node<X> next;

    Node(Node<X> predecessor, X o, Node<X> successor) {
        prev = predecessor;
        data = o;
        next = successor;
    }
}
