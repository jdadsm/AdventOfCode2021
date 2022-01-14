package Day7;
import java.util.*;
import java.io.*;

public class advent14 {
    static List<Integer> lista = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
        File f = new File("advent13.txt");
        Scanner sc = new Scanner(f);

        String[] input = sc.nextLine().split(",");
        int[] pos = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            pos[i] = Integer.parseInt(input[i]);
        }
        int fuel = 1000000000;
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
        int index;

        for (int j = 0; j < pos.length; j++) {
            index = Math.abs(pos[j] - i);
            if (lista.size() <= index) {
                fuelcalculator(index);
            }
            // System.out.println("Move from " + pos[j] + " to " + i + ": " +
            // lista.get(index) + " fuel");

            sum += lista.get(index);

        }
        // System.out.println(sum);
        return sum;
    }

    public static void fuelcalculator(int index) {
        int n = 0;
        int temp = index;
        while (temp >= 0) {
            n += temp;
            temp--;
        }
        lista.add(n);
        // System.out.println(lista.toString());
        if (lista.size() <= index) {
            fuelcalculator(index - 1, index);
        }
        Collections.sort(lista);
        // System.out.println(lista.toString());
        return;
    }

    private static void fuelcalculator(int index, int check) {
        if (lista.size() == check + 1) {
            return;
        }
        int temp = index;
        int n = 0;
        while (temp >= 0) {
            n += temp;
            temp--;
        }
        lista.add(n);
        // System.out.println(lista.toString());
        fuelcalculator(index - 1, check);
        return;

    }
}