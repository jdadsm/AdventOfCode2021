package Day1;
import java.util.*;
import java.io.*;

public class advent1 {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        File f = new File("advent1.txt");
        Scanner sc = new Scanner(f);

        int antes, agora;
        int count = 0;

        antes = sc.nextInt();
        agora = sc.nextInt();
        if (antes < agora) {
            count++;
        }

        while (sc.hasNextInt()) {
            antes = agora;
            agora = sc.nextInt();
            if (antes < agora) {
                count++;
            }
        }
        sc.close();
        System.out.print(count);
    }
}
