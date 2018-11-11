public class Node{
    public Node prev;
    public Object data;
    public Node next;
    Node(Node predecessor, Object o,Node successor){
        prev=predecessor;
        data=o;
        next=successor;
    }
}
