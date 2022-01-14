package Day1;
import java.util.*;
import java.io.*;

public class advent2 {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        File f = new File("advent2.txt");
        Scanner sc = new Scanner(f);

        int antes, agora, antes2, totalantes, total;
        int count = 0;

        antes = sc.nextInt();
        antes2 = sc.nextInt();
        agora = sc.nextInt();
        totalantes = antes + antes2 + agora;

        while (sc.hasNextInt()) {
            antes = antes2;
            antes2 = agora;
            agora = sc.nextInt();
            total = antes + antes2 + agora;
            if (totalantes < total) {
                count++;
            }
            totalantes = total;
        }
        sc.close();
        System.out.print(count);
    }
}
