public class check {
    private static int getHashIndex(String str){
        long  hash = 7;
        for (int i = 0; i < str.length(); i++) {
            hash = hash*31 + str.charAt(i);
        }
        long hashIndex=hash%1024;
        return (int)hashIndex;
    }
    private static int getHashIndex2(String str){
        int  hash = 7;
        for (int i = 0; i < str.length(); i++) {
            hash = hash*31 + str.charAt(i);
        }
        int hashIndex=hash%1024;
        return hashIndex;
    }
    public static void main(String args[]){

    }
}
