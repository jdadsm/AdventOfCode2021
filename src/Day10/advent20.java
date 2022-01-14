package Day10;
import java.util.*;
import java.io.*;

public class advent20 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent20.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        // Scanner sc = new Scanner(f);
        String line;
        Stack<Character> pilha = new Stack<Character>();
        char[] open = { '(', '[', '{', '<' };
        char[] close = { ')', ']', '}', '>' };
        int[] values = { 1, 2, 3, 4 };
        List<Long> lista = new ArrayList<Long>();
        while ((line = br.readLine()) != null) {
            pilha.clear();
            boolean corrupted = false;
            // System.out.println("Linha " + n);
            char[] chararray = line.toCharArray();
            for (char c : chararray) {
                for (int i = 0; i < open.length; i++) {
                    if (open[i] == c) {
                        pilha.push(c);
                        break;
                    } else if (close[i] == c) {
                        if (!pilha.isEmpty()) {
                            if (pilha.peek() == open[i]) {
                                pilha.pop();
                            } else {
                                corrupted = true;
                            }
                        }
                    }
                }
            }
            if (corrupted) {
                continue;
            }
            String res = "";
            int size = pilha.size();
            if (!pilha.isEmpty()) {
                for (int j = 0; j < size; j++) {
                    char pop = pilha.pop();
                    for (int i = 0; i < close.length; i++) {
                        if (pop == open[i]) {
                            res += close[i];
                        }
                    }
                }
            }
            long temp = 0;
            for (int i = 0; i < res.length(); i++) {
                temp *= 5;
                switch (res.charAt(i)) {
                    case ')':
                        temp += values[0];
                        break;

                    case ']':
                        temp += values[1];
                        break;

                    case '}':
                        temp += values[2];
                        break;

                    case '>':
                        temp += values[3];
                        break;
                    default:
                        System.out.println("Deu merda");
                        break;
                }
            }
            lista.add(temp);
        }

        Collections.sort(lista);
        System.out.println(lista.toString());
        System.out.println(lista.get(lista.size() / 2));

        br.close();
    }

}