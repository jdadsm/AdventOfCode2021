package Day14;
import java.util.*;
import java.io.*;

public class advent28 {
    static int steps = 40;
    public static void main(String[] args) throws IOException {
        File f = new File("advent28.txt");
        Scanner sc = new Scanner(f);

        String start = sc.nextLine();
        List<String> first = new ArrayList<String>();
        List<String> second = new ArrayList<String>();
        List<Character> charray = new ArrayList<Character>();
        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] line = new String[2];
            if (!linha.isEmpty()) {
                line = linha.split(" -> ");
                first.add(line[0]);
                if(!second.contains(line[1])){
                    charray.add(line[1].charAt(0));
                }
                second.add(line[1]);
            }
        }
        long [] beforestep = new long[first.size()];
        long [] afterstep = new long[first.size()];
        for (int i = 0; i < start.length()-1; i++) {
            afterstep[first.indexOf(""+start.charAt(i)+start.charAt(i+1))]++;
            beforestep[first.indexOf(""+start.charAt(i)+start.charAt(i+1))]++;
        }
        
        count(beforestep,afterstep,first,second);
        
        long [] res = result(first,afterstep,charray,start.charAt(0),start.charAt(start.length()-1));
        Arrays.sort(res);
        System.out.println(Arrays.toString(res));
        System.out.println(res[res.length-1]-res[0]);
        sc.close();
    }

    public static void count(long[] beforestep,long[] afterstep,List<String> first,List<String> second){
        if(steps==0){
            return;
        }
        
        for (int i = 0; i < first.size(); i++) {
            if(beforestep[i]==0){
                continue;
            }
            String character = second.get(i);
            afterstep[i]-=beforestep[i];
            int index = first.indexOf(""+first.get(i).charAt(0)+character);
            afterstep[index]+=beforestep[i];
            index = first.indexOf(""+character+first.get(i).charAt(1));
            afterstep[index]+=beforestep[i];
        }


        steps--;
        beforestep = afterstep.clone();
        count(beforestep,afterstep,first,second);
    }

    public static long [] result(List<String> first,long[] afterstep,List<Character> charray,char zero,char last){
        long [] res = new long[charray.size()];
        for (int i = 0; i < afterstep.length; i++) {
            int index = charray.indexOf(first.get(i).charAt(0));
            res[index]+=afterstep[i];
            index = charray.indexOf(first.get(i).charAt(1));
            res[index]+=afterstep[i];
        }
        res[charray.indexOf(zero)]+=1;
        res[charray.indexOf(last)]+=1;
        for (int i = 0; i < res.length; i++) {
            res[i]=res[i]/2;
        }
        return res;
    }
}
