public class MyHashTable {
    MyLinkedList <WordEntry>[] llarray=new MyLinkedList [997];
    private int getHashIndex(String str){
        long  hash = 7;
        for (int i = 0; i < str.length(); i++) {
            hash = hash*31 + str.charAt(i);
        }
        long hashIndex=hash%997;
        if (hashIndex<0){
            hashIndex=(hashIndex+997)%997;
        }
        return (int)hashIndex;
    }
    public MySet<PageEntry> pagesWhichContainWord(String str){
        MySet<PageEntry> pageset=new MySet<>();
        int hashindex=getHashIndex(str);
       // System.out.println("given word is"+str);
       // System.out.println(hashindex);
       // System.out.println(llarray[hashindex]!=null);
        if(llarray[hashindex]!=null) {
            Node<WordEntry> temp = llarray[hashindex].header.next;
            while (temp.next!=null) {
                //System.out.println("going over- "+temp.data.Word);
                if (temp.data.Word.equals(str)){
                    break;
                }
                temp = temp.next;
            }
            if(temp.next==null){
               // System.out.println("reached end. word not found");
                return pageset;
            }
            if (temp.data.Word.equals(str)) {
                //System.out.println(temp.data.Word+" -word found");
                Node<Position> tempsec = temp.data.allPositionsForThisWord.header.next;
                while (tempsec.next!=null) {
                    PageEntry pe = tempsec.data.getPageEntry();
                    if (!pageset.IsMember(pe)) {
                        pageset.addElement(pe);
                    }
                    tempsec = tempsec.next;
                }
            }
        }
        return pageset;
    }
    public WordEntry copier(WordEntry w){
        WordEntry copied=new WordEntry(w.Word);
        Node<Position> temp=w.allPositionsForThisWord.header.next;
        while (temp!=null&&temp.next!=null) {
            copied.allPositionsForThisWord.insertRear(temp.data);
            temp=temp.next;
        }
        return copied;
    }
    public void addPositionsForWord(WordEntry w){
        //System.out.println("Adding "+w.Word);
        int hashIndexForWord=getHashIndex(w.Word);
        WordEntry copy=copier(w);
        //System.out.println("Adding copy "+copy.Word);
        if(llarray[hashIndexForWord]!=null){
            if(!llarray[hashIndexForWord].isEmpty()) {
                Node<WordEntry> temp = llarray[hashIndexForWord].header.next;
                while (temp.next!=null) {
                    if (temp.data.Word.equals(copy.Word)) {
                        //System.out.println("found existing word "+copy.Word);
                        temp.data.addPositions(copy.allPositionsForThisWord);
                        break;
                    }
                    temp = temp.next;
                }
                if (temp.next==null){
                    llarray[hashIndexForWord].insertRear(copy);
                }
            }
            else llarray[hashIndexForWord].insertRear(copy);
        }
        else {
            MyLinkedList<WordEntry> newll = new MyLinkedList<>();
            llarray[hashIndexForWord]=newll;
            llarray[hashIndexForWord].insertRear(copy);
        }
    }
}
