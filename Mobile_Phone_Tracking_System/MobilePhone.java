public class MobilePhone {
    public int phonenumber;
    private boolean phonestatus;
    public Exchange phonelocation;
    public MobilePhone(int number){
        phonenumber=number;
        phonestatus=false;
        phonelocation=null;
    }
    public int number(){
        return phonenumber;
    }
    public boolean status(){
        return phonestatus;
    }
    public void switchOff(){
        phonestatus=false;
    }
    public void switchOn(){
        phonestatus=true;
    }
    public Exchange location(){
        try{
            if (phonestatus==true)
            return phonelocation;
            else throw new Exception();
        }
        catch (Exception e){
            System.out.println("The mobile phone is currently switched off.");
        }
        return null;
    }
}
