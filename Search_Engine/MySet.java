public class MySet <X> {
    public MyLinkedList<X> clcn=new MyLinkedList<>();

    public boolean IsMember(X element){
        if(!clcn.isEmpty()){
            Node<X> p=clcn.header.next;
            while (p.next!=null){
                if(p.data.equals(element)){
                    return true;
                }
                p=p.next;
            }
            if (p==clcn.trailer)
                return false;
            return true;
        }
        else
            return false;
    }
    void addElement(X element){
        if(!IsMember(element))
            clcn.insertRear(element);
    }
    public MySet<X> union (MySet<X> otherset){
        if (otherset==null&&this==null){
            return null;
        }
        else if(this!=null&&otherset==null){
            return this;
        }
        if (!clcn.isEmpty()&&otherset.clcn.isEmpty()){
            return this;
        }
        if (clcn.isEmpty()&& !otherset.clcn.isEmpty()){
            return otherset;
        }
        MySet <X> Unionset=new MySet<>();
        Node<X> traveller=clcn.header.next;
        //System.out.println(traveller==null);
        while (traveller.next!=null){
            Unionset.clcn.insertRear(traveller.data);
            traveller=traveller.next;
        }
        traveller=otherset.clcn.header.next;
        while (traveller.next!=null){
            if(!IsMember(traveller.data)){
                Unionset.clcn.insertRear(traveller.data);
            }
            traveller=traveller.next;
        }
        return Unionset;
    }
    public MySet<X> intersection(MySet<X> otherSet){
        if (otherSet==null||this==null)
            return null;
        else if (otherSet.clcn.isEmpty()||clcn.isEmpty()){
            MySet<X> Intersectionset=new MySet<>();
            return Intersectionset;
        }
        MySet<X> Intersectionset=new MySet<>();
        Node<X> traveller=clcn.header.next;
        while (traveller.next!=null){
            if(otherSet.IsMember(traveller.data)){
                Intersectionset.clcn.insertRear(traveller.data);
            }
            traveller=traveller.next;
        }
        return Intersectionset;
    }
}
