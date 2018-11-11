public class PageIndex {
    MyLinkedList<WordEntry> wordEntries=new MyLinkedList<>();
    void addPositionForWord(String str, Position p){
        if(wordEntries.isEmpty()){
            WordEntry newWordEntry= new WordEntry(str);
            newWordEntry.addPosition(p);
            wordEntries.insertRear(newWordEntry);
        }
        else {
            Node<WordEntry> traveller = wordEntries.header.next;
            while (traveller.next!=null) {
                if (traveller.data.Word.equals(str)) {
                    traveller.data.addPosition(p);
                    break;
                }
                traveller = traveller.next;
            }
            if(traveller.data==null){
                WordEntry newWordEntry= new WordEntry(str);
                newWordEntry.addPosition(p);
                wordEntries.insertRear(newWordEntry);
            }
        }
    }
    /*void addAVLNodeForWord(String str,Position p, int comp){
        if(!wordEntries.isEmpty()) {
            Node<WordEntry> traveller = wordEntries.header.next;
            while (traveller.next!=null) {
                if (traveller.data.Word.equals(str)) {
                    traveller.data.addAVLNode(p,comp);
                    break;
                }
                traveller = traveller.next;
            }
        }
    }*/
    public MyLinkedList<WordEntry> getWordEntries(){
        return wordEntries;
    }
}
