public class Myset{
    public DoublyLinkedList Mylist=new DoublyLinkedList();
    int Mysetsize=Mylist.size();

    public boolean IsEmpty(){
        if(Mylist.size()==0)
            return true;
        return false;
    }
    public boolean IsMember(Object o){
        if(!IsEmpty()){
            Node p=Mylist.header.next;
            while (p.data!=(o)){
                p=p.next;
            }
            if (p==Mylist.trailer)
                return false;
            return true;
        }
        else
            return false;
    }
    public void Insert(Object o){
        Mylist.insertRear(o);
        Mysetsize=Mysetsize+1;
    }
    public void Delete(Object o){
        try {
            if (IsMember(o)) {
                Node n = Mylist.header.next;
                while (n.data != o) {
                    n = n.next;
                }
                Mylist.removeNode(n);
                Mysetsize=Mysetsize-1;
            } else throw new Exception();

        }
        catch (Exception e){
            System.out.println("No such mobile phone.");
        }
    }
    public Myset Union(Myset a){
        Myset Unionset=new Myset();
        Node traveller=Mylist.header.next;
        while (traveller.data!=null){
            Unionset.Mylist.insertRear(traveller.data);
            traveller=traveller.next;
        }
        traveller=a.Mylist.header.next;
        while (traveller.data!=null){
            if(!IsMember(traveller.data)){
                Unionset.Mylist.insertRear(traveller.data);
            }
            traveller=traveller.next;
        }
        return Unionset;

    }
    public Myset Intersection(Myset a){
        Myset Intersectionset=new Myset();
        Node traveller=Mylist.header.next;
        while (traveller.data!=null){
            if(a.IsMember(traveller.data)){
                Intersectionset.Mylist.insertRear(traveller.data);
            }
            traveller=traveller.next;
        }
        return Intersectionset;
    }
}