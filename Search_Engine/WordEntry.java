public class WordEntry {
    String Word;
    MyLinkedList<Position> allPositionsForThisWord;
    AVLTree<Position> potree;
    public WordEntry(String word){
        Word=word;
        MyLinkedList<Position> p=new MyLinkedList<>();
        allPositionsForThisWord=p;
        AVLTree<Position> xyz=new AVLTree<>(null);
        potree=xyz;
    }
    public void addPosition(Position position){
        allPositionsForThisWord.insertRear(position);
        //AVLNode<Position> newnode=new AVLNode<>(null,position,null,null);
        //newnode.comparator=position.getWordIndex();
        //potree.insert(newnode);
        //System.out.println("insavl "+newnode.comparator);
    }
    /*public void addAVLNode(Position p, int comp){
        AVLNode<Position> newnode=new AVLNode<>(null,p,null,null);
        newnode.comparator=comp;
        potree.insert(newnode);
        System.out.println("insavl "+newnode.comparator+Word+p.wordIndex);
    }*/
    public void addPositions(MyLinkedList<Position> positions){
        //Node<Position> n=positions.header.next;
        //while (n.next!=null){
            //AVLNode<Position> newnode=new AVLNode<>(null,n.data,null,null);
            //newnode.comparator=n.data.getWordIndex();
            //potree.insert(newnode);
        //}
        Node<Position> splicehead=allPositionsForThisWord.trailer.prev;
        Node<Position> splicetail=allPositionsForThisWord.trailer;
        Node<Position> joinhead=positions.header.next;
        Node<Position> jointail=positions.trailer.prev;
        joinhead.prev=splicehead;
        jointail.next=splicetail;
        splicehead.next=joinhead;
        splicetail.prev=jointail;
    }
    public MyLinkedList<Position> getAllPositionsForThisWord(){
        return allPositionsForThisWord;
    }
    public float getTermFrequency(){
        int termFrequency=allPositionsForThisWord.size();
        return termFrequency;
    }
}
