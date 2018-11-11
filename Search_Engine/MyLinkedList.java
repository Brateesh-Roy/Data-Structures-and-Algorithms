public class MyLinkedList <X> {
    Node<X> header;
    Node<X> trailer;
    public int size=0;
    public MyLinkedList (){
        header=new Node <>(null,null,null);
        trailer=new Node <> (null,null,null);
        header.next=trailer;
        trailer.prev=header;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return (size==0);
    }
    public X first(){
        if(isEmpty())
            return null;
        return header.next.data;
    }
    public X last(){
        if(isEmpty())
            return null;
        return trailer.prev.data;
    }
    public void addBetween(Node<X> predecessor, X newdata, Node<X> successor){
        Node<X> newNode=new Node <>(predecessor,newdata,successor);
        predecessor.next=newNode;
        successor.prev=newNode;
        size=size+1;
    }
    public void insertFront(X newdata){
        addBetween(header,newdata,header.next);
    }
    public void insertRear(X newdata){
        addBetween(trailer.prev,newdata,trailer);
    }
    public X removeNode(Node<X> deletion){
        Node<X> predecessor=deletion.prev;
        Node<X> successor=deletion.next;
        predecessor.next=successor;
        successor.prev=predecessor;
        size=size-1;
        return deletion.data;
    }
    public X deleteFront(){
        if(isEmpty())
            return null;
        return removeNode(header.next);
    }
    public X deleteRear(){
        if(isEmpty())
            return null;
        return removeNode(trailer.prev);
    }
    public boolean IsMember(X element){
        if(!isEmpty()){
            Node<X> p=header.next;
            while (p.next!=null){
                if(p.data.equals(element)){
                    return true;
                }
                p=p.next;
            }
            return false;

        }
        else
            return false;
    }
}


