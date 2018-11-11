public class Position {
    PageEntry p;
    int wordIndex;
    public Position(PageEntry pe, int wordindex){
        p=pe;
        wordIndex=wordindex;
    }
    public PageEntry getPageEntry(){
        return p;
    }
    public int getWordIndex(){
        return wordIndex;
    }
}
