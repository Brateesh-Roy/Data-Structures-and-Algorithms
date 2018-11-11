class AVLNode<X>{
    public AVLNode<X> Parent;
    public X data;
    public int comparator;
    public int height;
    public int balance;
    public AVLNode<X> leftchild;
    public AVLNode<X> rightchild;
    public AVLNode<X> intermediate;
    public AVLNode(AVLNode<X> papa,X newdata, AVLNode<X> newleftchild,AVLNode<X>newrightchild){
        Parent=papa;
        data=newdata;
        leftchild=newleftchild;
        rightchild=newrightchild;
    }
}
public class AVLTree <X> {
    public int max(int a, int b){
        if (a>b)
            return a;
        else return b;
    }
    public int getHeight(AVLNode<X> x){
        if(x==null)
            return -1;
        else return x.height;
    }
    AVLNode<X> root;
    public AVLTree(AVLNode <X> newroot){
        root=newroot;
        if (root!=null)
            root.height=0;
    }
    AVLNode<X> successor;
    AVLNode<X> predecessor;

    public AVLNode<X> getSuccessor(int comp){
        AVLNode<X> base=getNode(comp);
        if(base.rightchild!=null){
            base=base.rightchild;
            while (base.leftchild!=null){
                base=base.leftchild;
            }
            return base;
        }
        else {
            if (base.Parent!=null){
                if (base==base.Parent.leftchild)
                    return base.Parent;
            }
            while (base.Parent!=null){
                base=base.Parent;
                if (base.Parent!=null){
                    if (base==base.Parent.leftchild)
                        return base.Parent;
                }
            }
            return null;
        }
    }
    public AVLNode<X> getPredecessor(int comp){
        predecessor=null;
        AVLNode<X> betweener=getNode(comp-1);
        if(betweener!=null)
            return betweener;
        else return predecessor;
    }
    public AVLNode<X> getMin(){
        AVLNode<X> min=root;
        while (min.leftchild!=null){
            min=min.leftchild;
        }
        return min;
    }
    public AVLNode<X> getMax(){
        AVLNode<X> max=root;
        while (max.rightchild!=null){
            max=max.rightchild;
        }
        return max;
    }
    public AVLNode<X> getNode(int comp){
        AVLNode<X> temp=root;
        if (root==null)
            return null;
        if (comp==root.comparator){
            return root;
        }
        while (temp!=null){
            if (temp.comparator<comp){
                if(temp.rightchild!=null) {
                    predecessor = temp;
                    //System.out.println("predecessor is now "+temp.comparator);
                    temp = temp.rightchild;
                }
                else return null;
            }
            else if (temp.comparator>comp){
                if(temp.leftchild!=null) {
                    successor = temp;
                    //System.out.println("predecessor is now "+temp.comparator);
                    temp = temp.leftchild;
                }
                else return null;
            }
            else return temp;
        }
        return null;
    }
    public AVLTree<X>subtreeAt(AVLNode<X> rootnode){
        AVLTree<X> newsub=new AVLTree<>(rootnode);
        return newsub;
    }
    public void rotate_zig_zig(AVLNode<X> x){
        AVLNode<X> y = x.Parent;
        AVLNode<X> z = y.Parent;
        AVLNode<X> w=z.Parent;
        if (w!=null) {
            if(w.rightchild!=null) {
                if (w.rightchild.equals(z)) {
                    w.rightchild = y;
                }
            }
            if (w.leftchild!=null) {
                if (w.leftchild.equals(z)) {
                    w.leftchild = y;
                }
            }
        }
        else {
            root=y;
        }
        if (y.rightchild!=null&&z.rightchild!=null) {
            if (y.rightchild.equals(x) && z.rightchild.equals(y)) {
                y.Parent = w;
                z.rightchild = y.leftchild;
                if (y.leftchild != null) {
                    y.leftchild.Parent = z;
                    //System.out.println("transfer");
                }
                z.Parent = y;
                y.leftchild = z;
            }
        }
        if (y.leftchild!=null&&z.leftchild!=null) {
            if (y.leftchild.equals(x) && z.leftchild.equals(y)) {
                y.Parent = w;
                z.leftchild = y.rightchild;
                if (y.leftchild != null) {
                    y.rightchild.Parent = z;
                    //System.out.println("transfer");
                }
                z.Parent = y;
                y.rightchild = z;
            }
        }
        z.height=max(getHeight(z.leftchild),getHeight(z.rightchild))+1;
        z.balance=getHeight(z.rightchild)-getHeight(z.leftchild);
        y.height=max(getHeight(y.leftchild),getHeight(y.rightchild))+1;
        y.balance=getHeight(y.rightchild)-getHeight(y.leftchild);
        x.height=max(getHeight(x.leftchild),getHeight(x.rightchild))+1;
        x.balance=getHeight(x.rightchild)-getHeight(x.leftchild);
        //System.out.println("rt of y is "+y.rightchild.comparator);
        //System.out.println("lt of y is "+y.leftchild.comparator);
    }
    public void rotate_zig_zag(AVLNode<X> x){
        AVLNode<X> y = x.Parent;
        AVLNode<X> z = y.Parent;
        AVLNode<X> w=z.Parent;
        if (w!=null) {
            if (w.rightchild.equals(z)) {
                w.rightchild = x;
            } else if (w.leftchild.equals(z)) {
                w.leftchild = x;
            }
        }
        else root=x;
        if (y.rightchild!=null&&z.leftchild!=null) {
            if (y.rightchild.equals(x) && z.leftchild.equals(y)) {
                x.Parent = w;
                y.rightchild = x.leftchild;
                z.leftchild = x.rightchild;
                if (x.leftchild != null) {
                    x.leftchild.Parent = y;
                    //System.out.println("transfer");
                }
                if (x.rightchild != null) {
                    x.rightchild.Parent = z;
                    //System.out.println("transfer");
                }
                y.Parent = x;
                z.Parent = x;
                x.leftchild = y;
                x.rightchild = z;
            }
        }
        if (y.leftchild!=null&&z.rightchild!=null) {
        if (y.leftchild.equals(x) && z.rightchild.equals(y)) {
                x.Parent = w;
                y.leftchild = x.rightchild;
                z.rightchild = x.leftchild;
                if (x.leftchild != null) {
                    x.leftchild.Parent = y;
                }
                if (x.rightchild != null) {
                    x.rightchild.Parent = z;
                }
                y.Parent = x;
                z.Parent = x;
                x.rightchild = y;
                x.leftchild = z;
            }
        }
        z.height=max(getHeight(z.leftchild),getHeight(z.rightchild))+1;
        z.balance=getHeight(z.rightchild)-getHeight(z.leftchild);
        y.height=max(getHeight(y.leftchild),getHeight(y.rightchild))+1;
        y.balance=getHeight(y.rightchild)-getHeight(y.leftchild);
        x.height=max(getHeight(x.leftchild),getHeight(x.rightchild))+1;
        x.balance=getHeight(x.rightchild)-getHeight(x.leftchild);
        //System.out.println("rt of x is "+x.rightchild.comparator);
        //System.out.println("lt of x is "+x.leftchild.comparator);
    }
    public void insert(AVLNode<X> insertion){
        AVLNode<X> temp=root;
        if (root==null){
            root=insertion;
            root.height=0;
            return;
        }
        while (temp.rightchild!=null||temp.leftchild!=null){
            if (insertion.comparator>temp.comparator){
                if (temp.rightchild!=null) {
                    insertion.Parent = temp.rightchild;
                }
                else {
                    insertion.Parent=temp;
                    break;
                }
                temp.rightchild.intermediate=insertion;
                temp.intermediate=null;
                temp=temp.rightchild;
            }
            else{
                if (temp.leftchild!=null) {
                    insertion.Parent = temp.leftchild;
                }
                else {
                    insertion.Parent=temp;
                    break;
                }
                temp.leftchild.intermediate=insertion;
                temp.intermediate=null;
                temp=temp.leftchild;
            }
        }
        if (insertion.comparator>temp.comparator) {
            temp.rightchild = insertion;
            temp.intermediate=null;
        }
        else {
            temp.leftchild = insertion;
            temp.intermediate=null;
        }
        insertion.height=0;
        insertion.balance=0;
        insertion.Parent=temp;
        AVLNode<X> tempsec=insertion.Parent;
        while (tempsec!=null){
            //System.out.println("tempsec is now "+tempsec.comparator);
            //System.out.println("rht "+getHeight(tempsec.rightchild)+" and lht "+getHeight(tempsec.leftchild));
            tempsec.height=max(getHeight(tempsec.rightchild),getHeight(tempsec.leftchild))+1;
            //System.out.println("node "+tempsec.comparator+" has now height "+tempsec.height);
            tempsec.balance=getHeight(tempsec.rightchild)-getHeight(tempsec.leftchild);
            //System.out.println("node "+tempsec.comparator+" has now balance "+tempsec.balance);
            if(tempsec.balance==0)
                break;
            if (tempsec.balance==2||tempsec.balance==-2){
                //System.out.println("restructuring");
                if (getHeight(tempsec.leftchild)>getHeight(tempsec.rightchild)){
                    AVLNode<X> y=tempsec.leftchild;
                    if (getHeight(y.leftchild)>getHeight(y.rightchild)){
                        AVLNode<X> x=y.leftchild;
                        //System.out.println("x is "+x.comparator);
                        rotate_zig_zig(x);
                    }
                    if(getHeight(y.leftchild)<getHeight(y.rightchild)) {
                        AVLNode<X> x=y.rightchild;
                        //System.out.println("x is "+x.comparator);
                        rotate_zig_zag(x);
                    }
                }
                if(getHeight(tempsec.leftchild)<getHeight(tempsec.rightchild)){
                    AVLNode<X> y=tempsec.rightchild;
                    if (getHeight(y.leftchild)>getHeight(y.rightchild)){
                        AVLNode<X> x=y.leftchild;
                        //System.out.println("x is "+x.comparator);
                        rotate_zig_zag(x);
                    }
                    if (getHeight(y.leftchild)<getHeight(y.rightchild)){
                        AVLNode<X> x=y.rightchild;
                        //System.out.println("x is "+x.comparator);
                        rotate_zig_zig(x);
                    }
                }
            }
            tempsec=tempsec.Parent;
        }
        //System.out.println("---------------------------------------------------------------------------------------------");
    }
}
