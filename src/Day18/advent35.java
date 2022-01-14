package Day18;
import ExtraClasses.*;
import java.util.*;
import java.io.*;

public class advent35 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent35.txt");
        Scanner sc = new Scanner(f);
    
        String input = sc.nextLine();
        Pair par = (new Pair()).stringToPair(input); 
        while(par.checkExplode() || par.checkSplit()){
            while(par.checkExplode()){
                par = par.explode();
            }
            if(par.checkSplit()){
                par = par.split();
            }
        }
        while(sc.hasNextLine()){
            par = par.addPair(sc.nextLine());
            while(par.checkExplode() || par.checkSplit()){
                while(par.checkExplode()){
                    par = par.explode();
                }
                if(par.checkSplit()){
                    par = par.split();
                }
            }
        }
        System.out.println(par.pairToString());
        System.out.println(par.getMagnitude());
        


        sc.close();
    }
}