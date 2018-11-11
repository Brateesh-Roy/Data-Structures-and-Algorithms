import java.util.*;
import java.lang.*;
import java.io.*;
public class MySort {
    ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries){
        ArrayList<SearchResult> list=new ArrayList<>();
        if (!listOfSortableEntries.clcn.isEmpty()) {
            Node<SearchResult> temp = listOfSortableEntries.clcn.header.next;
            while (temp.next != null) {
                list.add(temp.data);
                temp = temp.next;
            }
            Collections.sort(list);
            //System.out.println("sorteeeed++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        return list;
    }
}
