package Day14;
import java.util.*;
import java.io.*;

public class advent27 {
    static int steps = 10;
    static Queue<String> fila = new LinkedList<String>();
    public static void main(String[] args) throws IOException {
        File f = new File("teste.txt");
        Scanner sc = new Scanner(f);

        String start = sc.nextLine();
        List<String> first = new ArrayList<String>();
        List<String> second = new ArrayList<String>();
        List<Character> charray = new ArrayList<Character>();
        int length=0;
        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] line = new String[2];
            if (!linha.isEmpty()) {
                line = linha.split(" -> ");
                first.add(line[0]);
                if(!second.contains(line[1])){
                    charray.add(line[1].charAt(0));
                    length++;
                }
                second.add(line[1]);
            }
        }
        final int [] counter = new int[length];
        String letras = "";
        for (char c : charray) {
            letras+=c;
        }
        
        for (int i = 0; i < start.length()-1; i++) {
            fila.add(""+start.charAt(i)+start.charAt(i+1));
        }
        for (int i = 0; i < start.length(); i++) {
            counter[letras.indexOf(start.charAt(i))]++;
        }
        count(letras,counter,first,second);
        Arrays.sort(counter);
        System.out.println(Arrays.toString(counter));
        System.out.println(counter[counter.length-1]-counter[0]);

        //System.out.println(first.toString());
        //System.out.println(second.toString());

        sc.close();
    }

    public static void count(String letras,int[] counter,List<String> first,List<String> second){
        if(steps==0){
            return;
        }
        int size = fila.size();
        for (int i = 0; i < size; i++) {
            String temp = fila.remove();
            char c = second.get(first.indexOf(temp)).charAt(0);
            counter[letras.indexOf(c)]++;
            fila.add(""+temp.charAt(0) + c);
            fila.add(""+c+temp.charAt(1));
        }
        steps--;
        count(letras,counter,first,second);
    }
}
