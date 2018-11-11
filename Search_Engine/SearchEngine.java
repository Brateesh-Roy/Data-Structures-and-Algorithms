import java.util.ArrayList;
import java.util.Collections;

public class SearchEngine {
    InvertedPageIndex IPI;
    public SearchEngine(){
        InvertedPageIndex newIPI=new InvertedPageIndex();
        IPI=newIPI;
    }
    public PageEntry findpage(String name){
        Node<PageEntry> temp=IPI.PEntryList.header.next;
        while (temp.next!=null){
            if(temp.data.pagename.equals(name))
                return temp.data;
            temp=temp.next;
        }

        return null;
    }
    void performAction(String actionMessage){
        //System.out.println("starting "+actionMessage);
        String actionMessageBreakdown[]=actionMessage.split(" ");
        if(actionMessageBreakdown[0].equals("addPage")){
            String newpagename=actionMessageBreakdown[1];
            PageEntry newpageentry=new PageEntry(newpagename);
            IPI.addPage(newpageentry);
        }
        if(actionMessageBreakdown[0].equals("queryFindPagesWhichContainWord")){
            String reqword=actionMessageBreakdown[1].toLowerCase();
            MySet<PageEntry> reqset;
            if (reqword.equals("stacks")||reqword.equals("stack")){
                reqset=IPI.getPagesWhichContainWord("stack").union(IPI.getPagesWhichContainWord("stacks"));
            }
            else if (reqword.equals("structures")||reqword.equals("structure")){
                reqset=IPI.getPagesWhichContainWord("structure").union(IPI.getPagesWhichContainWord("structures"));
            }
            else if (reqword.equals("applications")||reqword.equals("application")){
                //System.out.println("flag it");
                reqset=IPI.getPagesWhichContainWord("application").union(IPI.getPagesWhichContainWord("applications"));
            }
            else reqset=IPI.getPagesWhichContainWord(reqword);
            //Node<PageEntry> temp=reqset.clcn.header.next;
            /*if (!reqset.clcn.isEmpty()) {
                String printout = "";
                while (temp.next.next != null) {
                    printout = printout + temp.data.pagename + ", ";
                    temp = temp.next;
                }
                printout = printout + temp.data.pagename;
                System.out.println(printout);
                //System.out.println("done");
            }*/
            MySet<SearchResult> setuple=new MySet<>();
            Node<PageEntry> temp=reqset.clcn.header.next;
            if (!reqset.clcn.isEmpty()){
                while (temp.next!=null) {
                    SearchResult newsr;
                    if (reqword.equals("stacks")||reqword.equals("stack")){
                        newsr = new SearchResult(temp.data, temp.data.getwordrelevance("stack"));
                    }
                    else if (reqword.equals("structures")||reqword.equals("structure")){
                        newsr = new SearchResult(temp.data, temp.data.getwordrelevance("structure"));
                    }
                    else if (reqword.equals("applications")||reqword.equals("application")){
                        //System.out.println("flag it");
                        newsr = new SearchResult(temp.data, temp.data.getwordrelevance("application"));
                    }
                    else newsr = new SearchResult(temp.data, temp.data.getwordrelevance(reqword));
                    //System.out.println(newsr.pageEntry.pagename);
                    //System.out.println(newsr.relevance);
                    setuple.clcn.insertRear(newsr);
                    temp=temp.next;
                }
            }
            MySort slist=new MySort();
            ArrayList<SearchResult> aslist=slist.sortThisList(setuple);
            if (!aslist.isEmpty()){
                int j;
                String printout="";
                for (j=0;j<aslist.size()-1;j++){
                    printout=printout+aslist.get(j).pageEntry.pagename+", ";
                }
                printout=printout+aslist.get(j).pageEntry.pagename;
                System.out.println(printout);
            }
            else System.out.println("No webpage contains word "+actionMessageBreakdown[1] );
        }
        if(actionMessageBreakdown[0].equals("queryFindPositionsOfWordInAPage")){
            String reqword=actionMessageBreakdown[1].toLowerCase();
            String reqpagename=actionMessageBreakdown[2];
            PageEntry p=findpage(reqpagename);
            if (p==null){
                System.out.println("No webpage "+reqpagename+" found");
            }
            else {
                //System.out.println(reqword);
                //System.out.println(p.pagename);
                String printout = "";
                Node<WordEntry> temp = p.pageIndex.wordEntries.header.next;
                while (temp.next != null) {
                    if (temp.data.Word.equals(reqword)) {
                        //System.out.println("found");
                        break;
                    }
                    temp = temp.next;
                }
                if (temp.next == null) {
                    System.out.println("Webpage " + reqpagename + " does not contain word " + reqword);
                    return;
                }
                if (temp.data.Word.equals(reqword)) {
                    Node<Position> tempsec = temp.data.getAllPositionsForThisWord().header.next;
                    while (tempsec.next.next != null) {
                        printout = printout + tempsec.data.wordIndex + ", ";
                        tempsec = tempsec.next;
                    }
                    printout = printout + tempsec.data.wordIndex;
                    System.out.println(printout);
                } else {
                    System.out.println("Webpage " + reqpagename + " does not contain word " + reqword);
                }
                WordEntry nwe=p.getwordentry(reqword);
                AVLNode<Position> temp1=nwe.potree.getMin();
                //while (temp1!=null) {
                    //System.out.println("starter has reached position " + temp1.data.wordIndex);
                    //System.out.println(temp1.comparator);
                    /*if (nwe.potree.getPredecessor(temp1.comparator)!=null) {
                        System.out.println("previous is " + nwe.potree.getPredecessor(temp1.comparator).data.wordIndex);
                        System.out.println("current is "+nwe.potree.getSuccessor(nwe.potree.getPredecessor(temp1.comparator).comparator).data.wordIndex);
                        //System.out.println("precomp is ");
                    }
                    if (nwe.potree.getSuccessor(temp1.comparator)!=null) {
                        System.out.println("next is " + nwe.potree.getSuccessor(temp1.comparator).data.wordIndex);
                    }
                    temp1=nwe.potree.getSuccessor(temp1.comparator);
                }*/
            }
        }
        if (actionMessageBreakdown[0].equals("queryFindPagesWhichContainAllWords")){
            String strarray[]=new String[actionMessageBreakdown.length-1];
            int i;
            for (i=0;i<strarray.length;i++){
                strarray[i]=actionMessageBreakdown[i+1].toLowerCase();
            }
            MySet<PageEntry> reqset=IPI.getPagesWhichContainAllWords(strarray);
            //Node<PageEntry> temp2=reqset.clcn.header.next;
            //System.out.println(temp2.data.pagename);
            /*if (!reqset.clcn.isEmpty()){
                String printout = "";
                while (temp2.next.next != null) {
                    printout = printout + temp2.data.pagename + ", ";
                    temp2 = temp2.next;
                }
                printout = printout + temp2.data.pagename;
                //System.out.println(printout);
            }*/
            MySet<SearchResult> setuple=new MySet<>();
            Node<PageEntry> temp=reqset.clcn.header.next;
            if (!reqset.clcn.isEmpty()){
                while (temp.next!=null) {
                    SearchResult newsr = new SearchResult(temp.data, temp.data.getRelevance(strarray, false));
                    //System.out.println(newsr.pageEntry.pagename);
                    //System.out.println(newsr.relevance);
                    setuple.clcn.insertRear(newsr);
                    temp=temp.next;
                }
            }
            //Node<SearchResult> tempsec=setuple.clcn.header.next;
            /*if (!setuple.clcn.isEmpty()){
                String printout = "";
                while (tempsec.next.next != null) {
                    printout = printout + tempsec.data.pageEntry.pagename + ", ";
                    tempsec = tempsec.next;
                }
                //printout = printout + tempsec.data.pageEntry.pagename;
                //System.out.println(printout+" sr");
            }*/
            else System.out.println("No webpage contains all these words" );
            MySort slist=new MySort();
            ArrayList<SearchResult> aslist=slist.sortThisList(setuple);
            if (!aslist.isEmpty()){
                int j;
                String printout="";
                for (j=0;j<aslist.size()-1;j++){
                    printout=printout+aslist.get(j).pageEntry.pagename+", ";
                }
                printout=printout+aslist.get(j).pageEntry.pagename;
                System.out.println(printout);
            }
        }
        if (actionMessageBreakdown[0].equals("queryFindPagesWhichContainAnyOfTheseWords")){
            String strarray[]=new String[actionMessageBreakdown.length-1];
            int i;
            for (i=0;i<strarray.length;i++){
                strarray[i]=actionMessageBreakdown[i+1].toLowerCase();
            }
            MySet<PageEntry> reqset=IPI.getPagesWhichContainAnyofTheseWords(strarray);
            //Node<PageEntry> temp2=reqset.clcn.header.next;
            //System.out.println(temp2.data.pagename);
            /*if (!reqset.clcn.isEmpty()){
                String printout = "";
                while (temp2.next.next != null) {
                    printout = printout + temp2.data.pagename + ", ";
                    temp2 = temp2.next;
                }
                printout = printout + temp2.data.pagename;
                //System.out.println(printout);
            }*/
            Node<PageEntry> temp=reqset.clcn.header.next;
            MySet<SearchResult> setuple=new MySet<>();
            if (!reqset.clcn.isEmpty()){
                while (temp.next!=null) {
                    SearchResult newsr = new SearchResult(temp.data, temp.data.getRelevance(strarray, false));
                    //System.out.println(newsr.pageEntry.pagename);
                    //System.out.println(newsr.getRelevance());
                    setuple.clcn.insertRear(newsr);
                    temp=temp.next;
                }
            }
            //Node<SearchResult> tempsec=setuple.clcn.header.next;
            /*if (!setuple.clcn.isEmpty()){
                String printout = "";
                while (tempsec.next.next != null) {
                    printout = printout + tempsec.data.pageEntry.pagename + ", ";
                    tempsec = tempsec.next;
                }
                printout = printout + tempsec.data.pageEntry.pagename;
                //System.out.println(printout+" sr");
            }*/
            else System.out.println("No webpage contains any of these words" );
            MySort slist=new MySort();
            ArrayList<SearchResult> aslist=slist.sortThisList(setuple);
            if (!aslist.isEmpty()){
                int j;
                String printout="";
                for (j=0;j<aslist.size()-1;j++){
                    printout=printout+aslist.get(j).pageEntry.pagename+", ";
                }
                printout=printout+aslist.get(j).pageEntry.pagename;
                System.out.println(printout);
            }
        }
        if (actionMessageBreakdown[0].equals("queryFindPagesWhichContainPhrase")){
            String strarray[]=new String[actionMessageBreakdown.length-1];
            int i;
            for (i=0;i<strarray.length;i++){
                strarray[i]=actionMessageBreakdown[i+1].toLowerCase();
            }
            MySet<PageEntry> reqset=IPI.getPagesWhichContainPhrase(strarray);
            //Node<PageEntry> temp2=reqset.clcn.header.next;
            //System.out.println(temp2.data.pagename);
            /*if (!reqset.clcn.isEmpty()){
                String printout = "";
                while (temp2.next.next != null) {
                    printout = printout + temp2.data.pagename + ", ";
                    temp2 = temp2.next;
                }
                printout = printout + temp2.data.pagename;
                //System.out.println(printout);
            }*/
            Node<PageEntry> temp=reqset.clcn.header.next;
            MySet<SearchResult> setuple=new MySet<>();
            if (!reqset.clcn.isEmpty()){
                while (temp.next!=null) {
                    SearchResult newsr = new SearchResult(temp.data, temp.data.getRelevance(strarray, true));
                    //System.out.println(newsr.pageEntry.pagename);
                    //System.out.println(newsr.relevance);
                    setuple.clcn.insertRear(newsr);
                    temp=temp.next;
                }
            }
            //Node<SearchResult> tempsec=setuple.clcn.header.next;
            /*if (!setuple.clcn.isEmpty()){
                String printout = "";
                while (tempsec.next.next != null) {
                    printout = printout + tempsec.data.pageEntry.pagename + ", ";
                    tempsec = tempsec.next;
                }
                printout = printout + tempsec.data.pageEntry.pagename;
                //System.out.println(printout+" sr");
            }*/
            else System.out.println("No webpage contains this phrase" );
            MySort slist=new MySort();
            ArrayList<SearchResult> aslist=slist.sortThisList(setuple);
            if (!aslist.isEmpty()){
                int j;
                String printout="";
                for (j=0;j<aslist.size()-1;j++){
                    printout=printout+aslist.get(j).pageEntry.pagename+", ";
                }
                printout=printout+aslist.get(j).pageEntry.pagename;
                System.out.println(printout);
            }
        }

        //System.out.println("ending "+actionMessage);
    }
}
