package Day7;
import java.util.*;
import java.io.*;

public class advent13 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent12.txt");
        Scanner sc = new Scanner(f);

        String[] input = sc.nextLine().split(",");
        int[] pos = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            pos[i] = Integer.parseInt(input[i]);
        }
        int fuel = pos.length * pos.length;
        int temp;
        for (int i = 0; i < pos.length; i++) {
            temp = cost(pos, i);
            if (temp < fuel) {
                fuel = temp;
            }
        }
        System.out.println(fuel);

        sc.close();
    }

    public static int cost(int[] pos, int i) {
        int sum = 0;
        for (int j = 0; j < pos.length; j++) {
            sum += Math.abs(pos[j] - i);
            // System.out.println("Move from " + pos[j] + " to " + i + ": " +
            // Math.abs(pos[j] - i) + " fuel");
        }
        // System.out.println(sum);
        return sum;
    }
}
