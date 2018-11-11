public class InvertedPageIndex {
    MyHashTable hashTable=new MyHashTable();
    MyLinkedList<PageEntry> PEntryList=new MyLinkedList<>();
    public void addPage(PageEntry p){
        PEntryList.insertRear(p);
        //System.out.println("Number of pages is now"+PEntryList.size);
        p.ipi=this;
        Node<WordEntry> temp=p.pageIndex.getWordEntries().header.next;
        while (temp.next!=null) {
            hashTable.addPositionsForWord(temp.data);
            temp=temp.next;
        }
    }
    public MySet<PageEntry> getPagesWhichContainWord(String str){
        return hashTable.pagesWhichContainWord(str);
    }
    public MySet<PageEntry>getPagesWhichContainAllWords(String str[]){
        if (!PEntryList.isEmpty()){
            int i;
            MySet<PageEntry> tentatives=new MySet<>();
            tentatives=getPagesWhichContainWord(str[0]);
            if (tentatives!=null&&!tentatives.clcn.isEmpty())
            for (i=1;i<str.length;i++){
                if (tentatives!=null&&!tentatives.clcn.isEmpty()) {
                    //System.out.println(tentatives.clcn.isEmpty());
                    if (str[i].equals("stack")||str[i].equals("stacks"))
                        tentatives = tentatives.intersection(getPagesWhichContainWord("stack").union(getPagesWhichContainWord("stacks")));
                    else if (str[i].equals("structure")||str[i].equals("structures"))
                        tentatives = tentatives.intersection(getPagesWhichContainWord("structure").union(getPagesWhichContainWord("structures")));
                    else if (str[i].equals("application")||str[i].equals("applications"))
                        tentatives = tentatives.intersection(getPagesWhichContainWord("application").union(getPagesWhichContainWord("applications")));
                    else tentatives=tentatives.intersection(getPagesWhichContainWord(str[i]));
                }
                else return null;
            }
            return tentatives;
        }
        else return null;
    }
    public MySet<PageEntry> getPagesWhichContainAnyofTheseWords(String str[]){
        if (!PEntryList.isEmpty()){
            int i;
            MySet<PageEntry> tentatives=new MySet<>();
            tentatives=getPagesWhichContainWord(str[0]);
            if (tentatives!=null)
                for (i=1;i<str.length;i++){
                    if (tentatives!=null) {
                        if (str[i].equals("stack") || str[i].equals("stacks"))
                            tentatives = tentatives.union(getPagesWhichContainWord("stack").union(getPagesWhichContainWord("stacks")));
                        else if (str[i].equals("structure") || str[i].equals("structures"))
                            tentatives = tentatives.union(getPagesWhichContainWord("structure").union(getPagesWhichContainWord("structures")));
                        else if (str[i].equals("application") || str[i].equals("applications"))
                            tentatives = tentatives.union(getPagesWhichContainWord("application").union(getPagesWhichContainWord("applications")));
                        else tentatives = tentatives.union(getPagesWhichContainWord(str[i]));
                    }
                }
            return tentatives;
        }
        else return null;
    }
   public MySet<PageEntry>getPagesWhichContainPhrase(String str[]){
        MySet<PageEntry> phrasecontainer=new MySet<>();
        MySet reduction=getPagesWhichContainAllWords(str);
        if (reduction.clcn.isEmpty())
            return phrasecontainer;
        Node<PageEntry> temp1=reduction.clcn.header.next;
        while (temp1.next!=null){
            WordEntry stentry=temp1.data.getwordentry(str[0]);
            AVLNode<Position> starter=stentry.potree.getMin();
            while (starter!=null) {
                int haa=starter.comparator;
                int jaa=1;
                while (jaa<str.length){
                    if(temp1.data.getwordentry(str[jaa]).potree.getNode(haa+1)!=null) {
                        jaa = jaa + 1;
                        haa = haa + 1;
                    }
                    else break;
                }
                if (jaa==str.length){
                    phrasecontainer.clcn.insertRear(temp1.data);
                    break;
                }
                starter=stentry.potree.getSuccessor(starter.comparator);
            }
            temp1=temp1.next;
        }
        return phrasecontainer;
    }
}
