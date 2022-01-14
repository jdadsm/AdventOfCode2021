package ExtraClasses;
public class Pair {
    Node Root;

    public Pair(){
        
    }
    
    public Pair(Node left,Node right, Node root){
        this.Root = root;
        this.Root.leftPair = left; 
        this.Root.rightPair = right;
    }

    public Pair(int v){
        this.Root.value = v;
        Root.leftPair = null;
        Root.rightPair = null;
    }

    static int indexSTP = 0;
    public Pair stringToPair(String s){
        Node left = new Node();
        Node right = new Node();
        Node root = new Node();
        Pair par = new Pair(left,right,root);
        indexSTP++;
        par.Root.leftPair = stringToPair(s,par.Root.leftPair);
        indexSTP++;
        par.Root.rightPair = stringToPair(s,par.Root.rightPair);
        indexSTP = 0;
        return par;
    }

    private Node stringToPair(String s,Node n){
        if(s.charAt(indexSTP)=='[' ){
            indexSTP++;
            Node x = new Node();
            n.leftPair = stringToPair(s, x);
            indexSTP++;
            Node y = new Node();
            n.rightPair = stringToPair(s, y);
        }else if(s.charAt(indexSTP)==']'){
            indexSTP++;
            n = stringToPair(s, n);
        }else if(s.charAt(indexSTP)==','){
            indexSTP++;
            n = stringToPair(s, n);
        }else if(Character.isDigit(s.charAt(indexSTP))){
            String val = getWholeNumber(s, indexSTP);
            n.value = Integer.parseInt(val);
            indexSTP+=val.length()-1;
        }
        
        return n;
    }

    public String pairToString(){
        String output="[";
        if(this.Root.leftPair.value==-1){
            output = pairToString(this.Root.leftPair,output);
        }else{
            output+=""+this.Root.leftPair.value;
        }
        
        output+=",";
        if(this.Root.rightPair.value==-1){
            output = pairToString(this.Root.rightPair,output);
        }else{
            output+=""+this.Root.rightPair.value;
        }
        output+="]";

        return output;
    }

    private String pairToString(Node input,String output){
        if(input.value!=-1){
            return output+=input.value;
        }
        if(input.leftPair.value==-1){
            output += "[";
            output = pairToString(input.leftPair, output);
            if(input.rightPair.value==-1){
                output+=",";
                output = pairToString(input.rightPair, output);
            }else{
                output+=","+input.rightPair.value;
            }
            output+="]";
        }else{
            output+="["+input.leftPair.value;
            if(input.rightPair.value==-1){
                output+=",";
                output = pairToString(input.rightPair, output);
            }else{
                output+=","+input.rightPair.value;
            }
            output+="]";
        }
        

        return output;
    }

    public Pair addPair(String pair2){
        String pair1 = this.pairToString();
        if(pair2==""){
            return stringToPair(pair1);
        }
        String parFinal = "["+pair1+","+pair2+"]";
        return stringToPair(parFinal);
    }
    
    public int depht(Node n){
        if(n.value!=-1){
            return 0;
        }
        int leftDepth = depht(n.leftPair);
        int rightDepth = depht(n.rightPair);
        
        return ((leftDepth>rightDepth)?leftDepth:rightDepth)+1;
    }
    /*
    public int height(Node n,Node root){
        return height(root)-height(n);
    }

    public int height(Node n){
        if(n == null){
            return -1;
        }
        int leftHeight = height(n.leftPair);
        int rightHeight = height(n.rightPair);

        return ((leftHeight>rightHeight)?leftHeight:rightHeight)+1;
    }
    */

    public Pair explode(){
       int depth = 0;
       String tempLeft="";
       String tempRight="";
       int indexTempLeft = -1;
       int indexTempRight = -1;
       String s = this.pairToString();
       String newPair = "";
       for (int i = 0; i < s.length(); i++) {
            if(depth==5){
                String leftValue = getWholeNumber(s, i);
                int indexLeft = i;
                i+=leftValue.length()+1;
                String rightValue = getWholeNumber(s, i);
                int indexRight = i;
                i+=rightValue.length();
                if(tempLeft!=""){
                    newPair+=s.substring(0, indexTempLeft) + (Integer.parseInt(leftValue)+Integer.parseInt(tempLeft));
                    newPair+=s.substring(indexTempLeft+tempLeft.length(),indexLeft-1)+"0";
                    indexTempRight = getRightIndex(s, i);
                    if(indexTempRight!=-1){
                        tempRight = getWholeNumber(s, indexTempRight);
                        newPair+=s.substring(indexRight+2, indexTempRight) + (Integer.parseInt(rightValue)+Integer.parseInt(tempRight));
                        newPair+=s.substring(indexTempRight+tempRight.length(),s.length());
                    }else{
                        newPair+=s.substring(indexRight+2, s.length());
                    }
                }else{
                    newPair+=s.substring(0, indexLeft-1)+"0";
                    indexTempRight = getRightIndex(s, i);
                    if(indexTempRight!=-1){
                        tempRight = getWholeNumber(s, indexTempRight);
                        newPair+=s.substring(indexRight+2, indexTempRight) + (Integer.parseInt(rightValue)+Integer.parseInt(tempRight));
                        newPair+=s.substring(indexTempRight+tempRight.length(),s.length());
                    }else{
                        newPair+=s.substring(indexRight+2, s.length());
                    }
                }
                break;
            }
        
            if(s.charAt(i)=='['){
                depth++;
           }else if(s.charAt(i)==']'){
                depth--;
           }else if(Character.isDigit(s.charAt(i))){
                tempLeft = getWholeNumber(s,i);
                indexTempLeft = i;
                i+= tempLeft.length()-1;
           }
       }
       return stringToPair(newPair);
    }

    private static int getRightIndex(String s,int i){
        int index=-1;
        for (int j = i; j < s.length(); j++) {
            if(Character.isDigit(s.charAt(j))){
                return j;
            }
        }
        return index;
    }

    private static String getWholeNumber(String s,int i){
        String res="";
        while(Character.isDigit(s.charAt(i))){
            res+=s.charAt(i);
            i++;
        }
        return res;
    }
    public Pair split(){
        String s = this.pairToString();
        String newPair = "";
        int left=-1,right=-1,index=-1;
        for (int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))){
                int temp = Integer.parseInt(getWholeNumber(s, i));
                if(temp>9){
                    index = i;
                    left = temp/2;
                    right = (temp/2) + (temp%2);
                    i=s.length();
                }
            }
        }
        newPair+=s.substring(0, index)+"["+left+","+right+"]"+s.substring(index+2, s.length());
        return stringToPair(newPair);
    }

    public boolean checkExplode(){
        boolean check = false;
        if(depht(this.Root)>4){
            check=true;
        }
        return check;
    }

    public boolean checkSplit(){
        boolean check = false;
        String s = this.pairToString();
        for (int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))){
                int temp = Integer.parseInt(getWholeNumber(s, i));
                if(temp>9){
                    check = true;
                    i = s.length();
                }
            }
        }
        return check;
    }

    public int getMagnitude(){
        Node n = this.Root;
        int magnitude = 3*getMagnitudeLeft(n.leftPair)+2*getMagnitudeRight(n.rightPair);
        return magnitude;
    }

    private int getMagnitudeLeft(Node n){
        if(n.value!=-1){
            return n.value;
        }
        int magnitude = 3*getMagnitudeLeft(n.leftPair)+2*getMagnitudeRight(n.rightPair);
        return magnitude;
    }

    private int getMagnitudeRight(Node n){
        if(n.value!=-1){
            return n.value;
        }
        int magnitude = 3*getMagnitudeLeft(n.leftPair)+2*getMagnitudeRight(n.rightPair);
        return magnitude;
    }

}
