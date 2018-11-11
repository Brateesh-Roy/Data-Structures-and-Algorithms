public class testavltree {
    public static void main(String args[]){
        AVLTree<Position> testtree=new AVLTree<>(null);
        /*Position testpositionfirst=new Position(null,0);
        AVLNode<Position> testnoderoot=new AVLNode<>(null,testpositionfirst,null,null);
        testnoderoot.comparator=testpositionfirst.wordIndex;
        testtree.insert(testnoderoot);
        System.out.println("done"+0);*/
        int i;
        for (i=0;i<3;i++){
            Position testpositionrest=new Position(null,2*i);
            AVLNode<Position> testnoderest=new AVLNode<>(null,testpositionrest,null,null);
            testnoderest.comparator=testpositionrest.wordIndex;
            testtree.insert(testnoderest);
            System.out.println("inserted "+testnoderest.comparator);
            if (testtree.getPredecessor(testnoderest.comparator)!=null){
                System.out.println("previous was "+testtree.getPredecessor(testnoderest.comparator).data.wordIndex);
                //System.out.println("curr is "+testtree.getSuccessor(testtree.getPredecessor(testnoderest.comparator).comparator).data.wordIndex);
            }
            AVLNode<Position> temp1=testtree.getMin();
            /*System.out.println("staaaaaartveri-----------------------------------------");
            while (temp1!=null) {
                System.out.println("starter has reached position " + temp1.data.wordIndex);
                System.out.println(temp1.comparator);
                if (testtree.getPredecessor(temp1.comparator)!=null) {
                    System.out.println("verifying previous is " + testtree.getPredecessor(temp1.comparator).data.wordIndex);
                    System.out.println("verifying current is "+testtree.getSuccessor(testtree.getPredecessor(temp1.comparator).comparator).data.wordIndex);
                    //System.out.println("precomp is ");
                }
                if (testtree.getSuccessor(temp1.comparator)!=null) {
                    System.out.println("verifying next is " + testtree.getSuccessor(temp1.comparator).data.wordIndex);
                }
                temp1=testtree.getSuccessor(temp1.comparator);
            }
            System.out.println("eeeeeendveri-----------------------------------------");
            //System.out.println(i+" has parent "+testnoderest.Parent.comparator);
            /*if (i==4){
                System.out.println(testtree.getNode(2).Parent.comparator);
            }
            if (i==2){
                //System.out.println(testtree.getNode(0).Parent.comparator);
            }*/
            System.out.println("done"+2*i);
        }
        AVLNode<Position> starter=testtree.getMin();
        System.out.println(testtree.getNode(4).comparator);
        System.out.println(testtree.getSuccessor(2).comparator);
        while (starter!=null){
            System.out.println("starter reached "+starter.comparator);
            System.out.println("next is "+testtree.getSuccessor(starter.comparator).comparator);
            if (testtree.getPredecessor(starter.comparator)!=null){
                System.out.println("previous was "+testtree.getPredecessor(starter.comparator).data.wordIndex);
            }
            starter=testtree.getSuccessor(starter.comparator);
        }
        /*System.out.println(testtree.getNode(0).Parent.comparator);
        System.out.println(testtree.getMin().Parent.comparator);
        System.out.println(testtree.getSuccessor(0).comparator);
        System.out.println(testtree.getNode(2).Parent.comparator);
        System.out.println(testtree.getNode(4).Parent.comparator);
        System.out.println(testtree.getNode(5).Parent.comparator);
        System.out.println(testtree.getSuccessor(4).comparator);*/


    }
}
