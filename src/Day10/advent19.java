package Day10;
import java.util.*;
import java.io.*;

public class advent19 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent19.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        // Scanner sc = new Scanner(f);
        String line;
        Stack<Character> pilha = new Stack<Character>();
        char[] open = { '(', '[', '{', '<' };
        char[] close = { ')', ']', '}', '>' };
        char[] primeirosErros = new char[1000];
        int[] values = { 3, 57, 1197, 25137 };
        int n = 1;
        while ((line = br.readLine()) != null) {
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
                            } else if (primeirosErros[n - 1] == '\0') {
                                primeirosErros[n - 1] = close[i];
                            }
                        }
                    }
                }

            }
            n++;
        }
        // System.out.println(Arrays.toString(primeirosErros));
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (primeirosErros[i] == '\0') {
                continue;
            }
            for (int j = 0; j < close.length; j++) {
                if (close[j] == primeirosErros[i]) {
                    sum += values[j];
                }
            }
        }
        System.out.println(sum);
        br.close();
    }

}