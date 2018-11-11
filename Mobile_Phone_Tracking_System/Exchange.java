import java.util.ArrayList;
import java.lang.*;
public class Exchange {
    public int UID;
    public Exchange papa;
    public ArrayList<Exchange> childrenset=new ArrayList<>();
    public MobilePhoneSet residentmobilephoneset=new MobilePhoneSet();

    public Exchange(int number){
        UID=number;
    }
    public Exchange parent(){
        return papa;
    }
    public int numChildren(){
        return childrenset.size();
    }
    public Exchange child(int i){
        return childrenset.get(i-1);
    }
    public boolean isRoot(){
        return (papa==null);
    }
    public RoutingMapTree subtree(int i){
        RoutingMapTree rmtree=new RoutingMapTree();
        rmtree.root=childrenset.get(i);
        return rmtree;
    }
    public MobilePhoneSet residentSet(){
       return residentmobilephoneset;
    }

}
