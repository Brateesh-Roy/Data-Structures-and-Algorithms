import java.util.*;
import java.lang.*;
import java.io.*;
class SearchResult implements Comparable<SearchResult>{
    PageEntry pageEntry;
    double relevance;
    public SearchResult(PageEntry p, double r){
        pageEntry=p;
        relevance=r;
    }
    public PageEntry getPageEntry(){
        return pageEntry;
    }
    public double getRelevance(){
        return relevance;
    }
    public int compareTo(SearchResult otherObject){
        if (otherObject.relevance>relevance)
            return 1;
        else if(otherObject.relevance==relevance)
            return 0;
        else
            return -1;
    }
}