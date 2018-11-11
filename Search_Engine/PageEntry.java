import java.io.*;
import java.util.Scanner;
public class PageEntry {
    int realwords;
    MyLinkedList<String> stopWords;
    MyLinkedList<Integer> punctuations ;
    String modifiedPage;
    String wordarray[];
    String pagename;
    PageIndex pageIndex;
    InvertedPageIndex ipi;
    public PageEntry(String pageName) {
        realwords=0;
        MyLinkedList<String> sW = new MyLinkedList<>();
        MyLinkedList<Integer> punct = new MyLinkedList<>();
        String mpage=new String();
        PageIndex pi=new PageIndex();
        AVLTree<Position> ntree=new AVLTree<>(null);
        pagename = pageName;
        stopWords = sW;
        punctuations = punct;
        modifiedPage=mpage;
        pageIndex=pi;
        punctuations.insertRear(123);
        punctuations.insertRear(125);
        punctuations.insertRear(91);
        punctuations.insertRear(93);
        punctuations.insertRear(60);
        punctuations.insertRear(62);
        punctuations.insertRear(61);
        punctuations.insertRear(40);
        punctuations.insertRear(41);
        punctuations.insertRear(46);
        punctuations.insertRear(44);
        punctuations.insertRear(59);
        punctuations.insertRear(58);
        punctuations.insertRear(39);
        punctuations.insertRear(45);
        punctuations.insertRear(33);
        punctuations.insertRear(63);
        punctuations.insertRear(35);
        punctuations.insertRear(34);
        stopWords.insertRear("a");
        stopWords.insertRear("an");
        stopWords.insertRear("the");
        stopWords.insertRear("they");
        stopWords.insertRear("these");
        stopWords.insertRear("this");
        stopWords.insertRear("for");
        stopWords.insertRear("is");
        stopWords.insertRear("are");
        stopWords.insertRear("was");
        stopWords.insertRear("of");
        stopWords.insertRear("or");
        stopWords.insertRear("and");
        stopWords.insertRear("does");
        stopWords.insertRear("will");
        stopWords.insertRear("whose");
        try {
            FileInputStream fstream = new FileInputStream("webpages/" + pageName);
            Scanner s = new Scanner(fstream);
            String pageprocessor = new String();
            while (s.hasNext()) {
                pageprocessor = pageprocessor + s.nextLine()+" ";
            }
            int i = 0;
            int temp;
            while (i < pageprocessor.length()) {
                temp = pageprocessor.charAt(i);
                if (punctuations.IsMember(temp)) {
                    modifiedPage = modifiedPage + " ";
                } else {
                    modifiedPage = modifiedPage + pageprocessor.charAt(i);
                }
                i=i+1;
            }
            modifiedPage = modifiedPage.toLowerCase();
            wordarray= modifiedPage.trim().split(" +");
            int j = 0;
            int k=1;
            while (j < wordarray.length) {
                if (!stopWords.IsMember(wordarray[j])) {
                    Position p = new Position(this, j+1);
                    WordEntry nwe;
                    if (wordarray[j].equals("stacks")){
                        pageIndex.addPositionForWord("stack", p);
                        nwe=getwordentry("stack");
                    }
                    else if(wordarray[j].equals("structures")){
                        pageIndex.addPositionForWord("structure", p);
                        nwe=getwordentry("structure");
                    }
                    else if(wordarray[j].equals("applications")){
                        pageIndex.addPositionForWord("application", p);
                        nwe=getwordentry("application");
                    }
                    else {
                        pageIndex.addPositionForWord(wordarray[j], p);
                        nwe=getwordentry(wordarray[j]);
                    }
                    //pageIndex.addAVLNodeForWord(wordarray[j],p,k);
                    //nwe=getwordentry(wordarray[j]);
                    //System.out.println("Added pe "+nwe.Word);
                    AVLNode<Position> newnode=new AVLNode<>(null,p,null,null);
                    newnode.comparator=k;
                    nwe.potree.insert(newnode);
                    //System.out.println("insavl position "+nwe.potree.getNode(newnode.comparator).data.wordIndex+" comp "+newnode.comparator+" having word "+nwe.Word);
                    /*if (nwe.potree.getPredecessor(k)!=null){
                        System.out.println("previous was "+nwe.potree.getPredecessor(nwe.potree.getNode(newnode.comparator).comparator).data.wordIndex);
                        //System.out.println(nwe.potree.getNode(newnode.comparator).equals(newnode));
                        //System.out.println(nwe.potree.getSuccessor(nwe.potree.getPredecessor(newnode.comparator).comparator)!=null);
                        //System.out.println("checknext is "+nwe.potree.getSuccessor(nwe.potree.getPredecessor(nwe.potree.getNode(k).comparator).comparator).data.wordIndex);
                    }*/
                    //AVLNode<Position> temp1=nwe.potree.getMin();
                    //System.out.println("staaaaaartveri-----------------------------------------");
                    /*while (temp1!=null) {
                        System.out.println("starter has reached position " + temp1.data.wordIndex);
                        System.out.println(temp1.comparator);
                        if (nwe.potree.getPredecessor(temp1.comparator)!=null) {
                            System.out.println("previous is " + nwe.potree.getPredecessor(temp1.comparator).data.wordIndex);
                            System.out.println("current is "+nwe.potree.getSuccessor(nwe.potree.getPredecessor(temp1.comparator).comparator).data.wordIndex);
                            //System.out.println("precomp is ");
                        }
                        if (nwe.potree.getSuccessor(temp1.comparator)!=null) {
                            System.out.println("next is " + nwe.potree.getSuccessor(temp1.comparator).data.wordIndex);
                        }
                        temp1=nwe.potree.getSuccessor(temp1.comparator);
                    }*/
                    //System.out.println("veri-----------------------------------------");
                    //AVLNode<Position> newinsertion=new AVLNode<>(null,p,null,null);
                    //newinsertion.comparator=p.getWordIndex();
                    //positionAVLTree.insert(newinsertion);
                    k=k+1;
                    realwords=realwords+1;
                    //System.out.println("pavl "+newinsertion.comparator);
                }
                j=j+1;
            }
            //System.out.println("created");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found.");
        }
    }
    public WordEntry getwordentry(String str){
        if (pageIndex.wordEntries.isEmpty())
            return null;
        else {
            Node<WordEntry> temp=pageIndex.wordEntries.header.next;
            while (temp.next!=null){
                if (temp.data.Word.equals(str)){
                    return temp.data;
                }
                temp=temp.next;
            }
            return temp.data;
        }
    }
    PageIndex getPageIndex() {
        return pageIndex;
    }
    public double getwordrelevance(String str){
        double wordrelevance=0;
        if (pageIndex.wordEntries.isEmpty())
            return 0;
        Node<WordEntry> temp=pageIndex.wordEntries.header.next;
        while (temp.next!=null){
            if (temp.data.Word.equals(str)){
                double tf=(temp.data.getTermFrequency()/realwords);
                double idf= Math.log((double) ipi.PEntryList.size()/(double) ipi.getPagesWhichContainWord(str).clcn.size());
                wordrelevance=tf*idf;
                return wordrelevance;
            }
            temp=temp.next;
        }
        return wordrelevance;
    }
    public double getRelevance(String str[],boolean doTheseWordsRepresentAPhrase){
        double relevance=0;
        if (doTheseWordsRepresentAPhrase==true){
            int m=0;
            WordEntry stentry=getwordentry(str[0]);
            AVLNode<Position> starter=stentry.potree.getMin();
            while (starter!=null) {
                //System.out.println("starter has reached position "+starter.data.wordIndex);
                int haa=starter.comparator;
                if (stentry.potree.getSuccessor(starter.comparator)!=null) {
                    //System.out.println("next is " + stentry.potree.getSuccessor(starter.comparator).data.wordIndex);
                }
                if (stentry.potree.getPredecessor(starter.comparator)!=null){
                    //System.out.println("previous was "+stentry.potree.getPredecessor(starter.comparator).data.wordIndex);
                }
                int jaa=1;
                while (jaa<str.length){
                    if(getwordentry(str[jaa]).potree.getNode(haa+1)!=null) {
                        //System.out.println("found and going over "+str[jaa]);
                        jaa = jaa + 1;
                        haa = haa + 1;
                    }
                    else break;
                }
                if (jaa==str.length){
                    //System.out.println("found phrase "+m+1);
                    m=m+1;
                }
                starter=stentry.potree.getSuccessor(starter.comparator);
                //System.out.println(starter==null);
            }
            double tf=(double) m/(double) (realwords-(str.length-1)*m);
            double idf= Math.log((double) ipi.PEntryList.size()/(double) (ipi.getPagesWhichContainPhrase(str).clcn.size()));
            //System.out.println("divided by "+(wordarray.length-(str.length-1)*m));
            //System.out.println(ipi.PEntryList.size()+" and "+ipi.getPagesWhichContainPhrase(str).clcn.size());
            //System.out.println("tf is "+tf);
            //System.out.println("m is "+m);
            //System.out.println("idf is "+idf);
            relevance=tf*idf;
        }
        else {
            int i;
            for (i=0;i<str.length;i++){
                relevance=relevance+getwordrelevance(str[i]);
            }
        }
        //System.out.println(relevance);
        return relevance;
    }
}
