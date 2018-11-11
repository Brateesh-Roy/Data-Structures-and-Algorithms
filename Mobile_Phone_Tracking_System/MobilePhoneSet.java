public class MobilePhoneSet {
    Myset mobilephoneset=new Myset();
    public void insertphone(MobilePhone a){
        mobilephoneset.Insert(a);
    }
    public void deletephone(MobilePhone a){
       mobilephoneset.Delete(a);
    }
}
