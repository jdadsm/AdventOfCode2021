package Day18;
import ExtraClasses.*;
import java.util.*;
import java.io.*;

public class advent36 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent36.txt");
        Scanner sc = new Scanner(f);

        int magnitude = 0,temp;
        Pair res = new Pair();
        
        List<String> input = new ArrayList<String>();
        while(sc.hasNextLine()){
            input.add(sc.nextLine());
        }
        for (int i = 0; i < input.size(); i++) {
            Pair par1 = (new Pair()).stringToPair(input.get(i));
            for (int j = 0; j < input.size(); j++) {
                if(i==j){
                    continue;
                }
                Pair par2 = (new Pair()).stringToPair(input.get(j));
                Pair parFinal = par1.addPair(par2.pairToString());
                while(parFinal.checkExplode() || parFinal.checkSplit()){
                    while(parFinal.checkExplode()){
                        parFinal = parFinal.explode();
                    }
                    if(parFinal.checkSplit()){
                        parFinal = parFinal.split();
                    }
                }
                temp = parFinal.getMagnitude();
                if(temp>magnitude){
                    magnitude = temp;
                    res = parFinal;
                }

            }
        }
         
        
        
        System.out.println(res.pairToString());
        System.out.println(magnitude);
        


        sc.close();
    }
}