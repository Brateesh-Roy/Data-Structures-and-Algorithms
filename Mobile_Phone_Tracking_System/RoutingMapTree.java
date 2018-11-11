public class RoutingMapTree {
    public Exchange root;
    public ExchangeList routingmapexchangelist=new ExchangeList();
    public RoutingMapTree(){
        Exchange e=new Exchange(0);
        e.papa=null;
        root=e;
        routingmapexchangelist.insertexchange(root);
    }
    public void switchOn(MobilePhone a, Exchange b){
        if (a.status()==false) {
            a.switchOn();
            a.phonelocation = b;
            Exchange updator = b;
            while (!updator.isRoot()) {
                updator.residentmobilephoneset.insertphone(a);
                updator = updator.papa;
            }
            updator.residentmobilephoneset.insertphone(a);
        }
    }
    public void switchOff(MobilePhone a){
        if(a.status()==true){
            a.switchOff();
        }
    }
    public void clearTrace(MobilePhone a){
        Exchange deletor=a.location();
        while (!deletor.isRoot()){
            deletor.residentmobilephoneset.deletephone(a);
            deletor=deletor.papa;
        }
        deletor.residentmobilephoneset.deletephone(a);

    }
    public Exchange returnexchange(int findUID){
        exchangenode pointer=routingmapexchangelist.header.next;
        if(pointer!=null){
            while(pointer.data.UID!=findUID){
                pointer=pointer.next;
            }
            return pointer.data;
        }
        return null;
    }
    public RoutingMapTree subtree(Exchange e){
        RoutingMapTree stree=new RoutingMapTree();
        stree.root=e;
        return stree;
    }
    public MobilePhone extractor(Exchange e, int phonenumber){
        Node temp=e.residentmobilephoneset.mobilephoneset.Mylist.header.next;
        while (((MobilePhone)temp.data).number()!=phonenumber && temp!=null){
            temp=temp.next;
        }
        return ((MobilePhone)temp.data);
    }
    public boolean isAvailable(int checknumber){
        Node temp=root.residentmobilephoneset.mobilephoneset.Mylist.header.next;
        while (temp.next!=null && ((MobilePhone)temp.data).number()!=checknumber  ){
            temp=temp.next;
        }
        if(temp.next==null)
            return false;
        else
            return true;
    }
    public Exchange findPhone(MobilePhone m){
        try {
            if (isAvailable(m.phonenumber)) {
                try {
                    if (m.status() == true)
                        return m.phonelocation;
                    else throw new Exception();
                } catch (Exception e) {
                    System.out.println("Error - Mobile phone with identifier "+m.phonenumber+" is currently switched off");
                }
            }
            else throw new Exception();
        }
        catch(Exception f){
            System.out.println("Error - No mobile phone with identifier "+m.phonenumber+" found in the network");
        }
        return null;
    }
    public Exchange lowestRouter(Exchange a,Exchange b){
        if (a.UID==b.UID)
            return a;
        else {
            ExchangeList A=new ExchangeList();
            ExchangeList B=new ExchangeList();
            Exchange one=a;
            Exchange two=b;
            while (!one.isRoot()){
                A.insertexchange(one);
                one=one.papa;
            }
            A.insertexchange(one);
            while (!two.isRoot()){
                B.insertexchange(two);
                two=two.papa;
            }
            B.insertexchange(two);
            exchangenode ONE=A.trailer.prev;
            exchangenode TWO=B.trailer.prev;
            while (ONE.data.UID==TWO.data.UID){
                ONE=ONE.prev;
                TWO=TWO.prev;
            }
            Exchange answer=ONE.next.data;
            return answer;
        }
    }
    public ExchangeList routeCall(MobilePhone a,MobilePhone b){
        Exchange lr=lowestRouter(a.phonelocation,b.phonelocation);
        ExchangeList route=new ExchangeList();
        Exchange mover=a.phonelocation;
        exchangenode temp=route.trailer.prev;
        while (mover.UID!=lr.UID){
            route.insertexchange(mover);
            mover=mover.papa;
        }
        mover=b.phonelocation;
        ExchangeList part2=new ExchangeList();
        while (mover.UID!=lr.UID){
            part2.insertfront(mover);
            mover=mover.papa;
        }
        part2.insertfront(lr);
        exchangenode joiner1=part2.header.next;
        exchangenode joiner2=part2.trailer.prev;
        joiner1.prev=route.trailer.prev;
        joiner2.next=route.trailer;
        route.trailer.prev.next=joiner1;
        route.trailer.prev=joiner2;
        return route;
    }
    public void movePhone(MobilePhone a, Exchange b){

        clearTrace(a);
        switchOff(a);
        switchOn(a,b);
        System.out.println(b.residentmobilephoneset.mobilephoneset.IsMember(a));
    }
    public String performAction(String actionMessage){
        String []splitactionMessage=actionMessage.split(" ");
        if ("addExchange".equals(actionMessage.substring(0,11))){
            try{
                int newUID=Integer.parseInt(splitactionMessage[2]);
                int parentUID=Integer.parseInt(splitactionMessage[1]);
                Exchange newExchange=new Exchange(newUID);
                routingmapexchangelist.insertexchange(newExchange);
                Exchange parent=returnexchange(parentUID);
                if(parent.equals(null)){
                    throw new Exception();
                }
                newExchange.papa=parent;
                parent.childrenset.add(newExchange);
                while (parent.papa!=null){
                    if(!parent.residentmobilephoneset.mobilephoneset.IsEmpty()&&!newExchange.residentmobilephoneset.mobilephoneset.IsEmpty())
                        parent.residentmobilephoneset.mobilephoneset= parent.residentmobilephoneset.mobilephoneset.Union(newExchange.residentmobilephoneset.mobilephoneset);
                    else if(parent.residentmobilephoneset.mobilephoneset.IsEmpty()&&!newExchange.residentmobilephoneset.mobilephoneset.IsEmpty())
                        parent.residentmobilephoneset.mobilephoneset=newExchange.residentmobilephoneset.mobilephoneset;
                    parent=parent.papa;
                }
                if(!parent.residentmobilephoneset.mobilephoneset.IsEmpty()&&!newExchange.residentmobilephoneset.mobilephoneset.IsEmpty())
                    parent.residentmobilephoneset.mobilephoneset= parent.residentmobilephoneset.mobilephoneset.Union(newExchange.residentmobilephoneset.mobilephoneset);
                else if(parent.residentmobilephoneset.mobilephoneset.IsEmpty()&&!newExchange.residentmobilephoneset.mobilephoneset.IsEmpty())
                    parent.residentmobilephoneset.mobilephoneset=newExchange.residentmobilephoneset.mobilephoneset;
                return "";
            }
            catch (Exception e){
                System.out.println("Required insertion location does not exist.");
            }
        }
        if("switchOnMobile".equals(splitactionMessage[0])){
            int newphonenumber=Integer.parseInt(splitactionMessage[1]);
            int baseUID=Integer.parseInt(splitactionMessage[2]);
            try{
                Exchange base=returnexchange(baseUID);
                if(base.equals(null)){
                    throw new Exception();
                }
                if(!isAvailable(newphonenumber)) {
                    MobilePhone mp = new MobilePhone(newphonenumber);
                    mp.phonelocation = base;
                    switchOn(mp, base);
                }
                else{
                    MobilePhone c = extractor(root, newphonenumber);
                    {c.switchOn();}
                }
                return "";
            }
            catch (Exception e){
                System.out.println("Error- No exchange with identifier "+baseUID);
            }
        }
        if ("switchOffMobile".equals(splitactionMessage[0])){
            int phonenumber = Integer.parseInt(splitactionMessage[1]);
            try {
                if(isAvailable(phonenumber)) {
                    MobilePhone c = extractor(root, phonenumber);
                    switchOff(c);
                }
                else throw new Exception();
                return "";
            }
            catch (Exception e){
                System.out.println("Error- No mobile phone with identifier "+phonenumber);
            }
        }
        if ("queryNthChild".equals(splitactionMessage[0])){
            int index = Integer.parseInt(splitactionMessage[2]);
            int queryUID=Integer.parseInt(splitactionMessage[1]);
            try {
                Exchange parent = returnexchange(queryUID);
                if(index>parent.numChildren()){
                    throw new Exception();
                }
                int printout = (parent.childrenset.get(index).UID);
                String answer=actionMessage + ": " + printout;
                System.out.println(answer);
                return answer;
            }
            catch (Exception e){
                System.out.println("Error - No "+index+" child of Exchange "+queryUID);
            }
        }
        if("queryMobilePhoneSet".equals(splitactionMessage[0])){
            Exchange residentset=returnexchange(Integer.parseInt(splitactionMessage[1]));
            Node n=residentset.residentmobilephoneset.mobilephoneset.Mylist.header.next;
            String answer=actionMessage+": ";
            int printout;
            while (n.next.data!=null){
                MobilePhone temp=((MobilePhone)n.data);
                printout=temp.number();
                if(temp.status()==true){
                    answer=answer+printout+", ";
                }
                n=n.next;
            }
            answer=answer+((MobilePhone)n.data).number();
            System.out.println(answer);
            return answer;
        }
        if("findPhone".equals(splitactionMessage[0])){
            int phnumber=Integer.parseInt(splitactionMessage[1]);
            MobilePhone check=new MobilePhone(phnumber);
            if(isAvailable(phnumber)) {
                MobilePhone c = extractor(root, phnumber);
                String answer = "queryFindPhone "+phnumber + ": " + findPhone(c).UID;
                System.out.println(answer);
                return answer;
            }
            else{
                String answer="queryFindPhone "+phnumber + ": "+"Error - No mobile phone with identifier "+phnumber+" found in the network";
                System.out.println(answer);
                return answer;
            }
        }
        if("lowestRouter".equals(splitactionMessage[0])){
            Exchange e1=returnexchange(Integer.parseInt(splitactionMessage[1]));
            Exchange e2=returnexchange(Integer.parseInt(splitactionMessage[2]));
            String answer="queryLowestRouter "+e1.UID+" "+e2.UID+": "+lowestRouter(e1,e2).UID;
            System.out.println(answer);
            return answer;
        }
        if("findCallPath".equals(splitactionMessage[0])){
            MobilePhone m1=extractor(root,Integer.parseInt(splitactionMessage[1]));
            MobilePhone m2=extractor(root,Integer.parseInt(splitactionMessage[2]));
            if(m1.status()==true&&m2.status()==true) {
                String answer = "queryFindCallPath "+m1.phonenumber+" "+m2.phonenumber + ":";
                exchangenode temp = routeCall(m1, m2).header.next;
                while (temp.next.next!=null) {
                    answer = answer + " " + temp.data.UID + ",";
                    temp = temp.next;
                }
                answer = answer + " " + temp.data.UID;
                System.out.println(answer);
                return answer;
            }
            else if(m1.status()==false){
                String answer="queryFindCallPath "+m1.phonenumber+" "+m2.phonenumber+": "+"Error - Mobile phone with identifier "+m1.phonenumber+" is currently switched off";
                System.out.println(answer);
                return answer;
            }
            else if(m2.status()==false){
                String answer="queryFindCallPath "+m1.phonenumber+" "+m2.phonenumber+": "+"Error - Mobile phone with identifier "+m2.phonenumber+" is currently switched off";
                System.out.println(answer);
                return answer;
            }
        }
        if ("movePhone".equals(splitactionMessage[0])){
            int n=Integer.parseInt(splitactionMessage[1]);
            try {
                Exchange newlocation = returnexchange(Integer.parseInt(splitactionMessage[2]));
                if (newlocation.equals(null))
                    throw new Exception();
                try {
                    if (isAvailable(n)) {
                        MobilePhone m = extractor(root, n);
                        movePhone(m, newlocation);
                    }
                    else throw new Exception();
                }
                catch (Exception f){
                    System.out.println("No such cellphone");
                }

            }
            catch (Exception e){
                System.out.println("No such exchange");
            }
        }

        return "";
    }
}
