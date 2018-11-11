class exchangenode{
    public exchangenode prev;
    public Exchange data;
    public exchangenode next;
    public exchangenode(exchangenode predecessor, Exchange input, exchangenode successor){
        prev=predecessor;
        data=input;
        next=successor;
    }
}
public class ExchangeList {
    public exchangenode header;
    public exchangenode trailer;
    public ExchangeList(){
        header=new exchangenode(null,null,null);
        trailer=new exchangenode(null,null,null);
        header.next=trailer;
        trailer.prev=header;
    }
    public void insertexchange(Exchange insertion){
            exchangenode n = new exchangenode(null,null,null);
            exchangenode temp=trailer.prev;
            n.data=insertion;
            n.next=trailer;
            n.prev=trailer.prev;
            temp.next=n;
            trailer.prev=n;
    }
    public void insertfront(Exchange insertion){
        exchangenode n=new exchangenode(null,null,null);
        exchangenode temp=header.next;
        n.data=insertion;
        n.prev=header;
        n.next=header.next;
        temp.prev=n;
        header.next=n;
    }
}
