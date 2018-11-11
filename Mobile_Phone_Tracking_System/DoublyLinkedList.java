public class DoublyLinkedList {
    Node header;
    Node trailer;
    private int size=0;
    public DoublyLinkedList(){
        header=new Node(null,null,null);
        trailer=new Node(null,null,null);
        header.next=trailer;
        trailer.prev=header;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return (size==0);
    }
    public Object first(){
        if(isEmpty())
            return null;
        return header.next.data;
    }
    public Object last(){
        if(isEmpty())
            return null;
        return trailer.prev.data;
    }
    public void addBetween(Node predecessor, Object newdata, Node successor){
        Node newNode=new Node(predecessor,newdata,successor);
        predecessor.next=newNode;
        successor.prev=newNode;
        size=size+1;
    }
    public void insertFront(Object newdata){
        addBetween(header,newdata,header.next);
    }
    public void insertRear(Object newdata){
        addBetween(trailer.prev,newdata,trailer);
    }
    public Object removeNode(Node deletion){
        Node predecessor=deletion.prev;
        Node successor=deletion.next;
        predecessor.next=successor;
        successor.prev=predecessor;
        size=size-1;
        return deletion.data;
    }
    public Object deleteFront(){
        if(isEmpty())
            return null;
        return removeNode(header.next);
    }
    public Object deleteRear(){
        if(isEmpty())
            return null;
        return removeNode(trailer.prev);
    }



}

