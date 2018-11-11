public class test {
    public static void main(String Args[]){
        RoutingMapTree r=new RoutingMapTree();
        r.performAction("addExchange 0 1");
        r.performAction("addExchange 0 2");
        r.performAction("addExchange 0 3");
        r.performAction("addExchange 0 4");
        r.performAction("addExchange 1 5");
        r.performAction("addExchange 1 6");
        r.performAction("switchOnMobile 111 5");
        r.performAction("switchOnMobile 222 5");
        r.performAction("switchOffMobile 111 5");
        Exchange p=r.returnexchange(3);
        System.out.println(p.UID);
    }
}
