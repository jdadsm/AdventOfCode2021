package Day6;
import java.util.*;
import java.io.*;

public class advent11 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent11.txt");
        Scanner sc = new Scanner(f);

        String[] input = sc.nextLine().split(",");

        long[] dias = new long[10];
        for (String s : input) {
            dias[Integer.parseInt(s)]++;
        }

        for (int i = 0; i < 80; i++) {
            passarUmDia(dias);
        }
        long sum = 0;
        for (int i = 0; i < dias.length; i++) {
            sum += dias[i];
        }
        System.out.println(sum);
        sc.close();
    }

    public static void passarUmDia(long[] dias) {
        dias[9] = dias[0];
        dias[7] += dias[0];
        for (int i = 0; i < dias.length - 1; i++) {
            dias[i] = dias[i + 1];
        }
        dias[9] = 0;
    }
}
