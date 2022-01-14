package Day5;
import java.util.*;
import java.io.*;

public class advent9 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent9.txt");
        Scanner sc = new Scanner(f);
        List<String[]> lista = new ArrayList<String[]>();

        while (sc.hasNextLine()) {
            lista.add(sc.nextLine().split(" -> "));
        }
        /*
         * for (String[] strings : lista) {
         * System.out.println(Arrays.toString(strings));
         * }
         */

        int[][] array = new int[1000][1000];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = 0;
            }
        }

        for (String[] s : lista) {
            input(array, s);
        }

        int result = counter(array);

        /*
         * for (int i = 0; i < 10; i++) {
         * for (int j = 0; j < 10; j++) {
         * System.out.print(array[j][i] + ", ");
         * }
         * System.out.print("\n");
         * }
         */

        System.out.println(result);
        sc.close();
    }

    public static void input(int[][] array, String[] s) {
        String[] um = s[0].split(",");
        String[] dois = s[1].split(",");
        int x1 = Integer.parseInt(um[0]);
        int y1 = Integer.parseInt(um[1]);
        int x2 = Integer.parseInt(dois[0]);
        int y2 = Integer.parseInt(dois[1]);
        // System.out.println(x1 + ", " + y1 + ", " + x2 + ", " + y2);

        if (x1 == x2) {
            if (y2 > y1) {
                for (int i = y1; i <= y2; i++) {
                    array[x1][i]++;
                }
            } else {
                for (int i = y1; i >= y2; i--) {
                    array[x1][i]++;
                }
            }
        } else if (y1 == y2) {
            if (x2 > x1) {
                for (int i = x1; i <= x2; i++) {
                    array[i][y1]++;
                }
            } else {
                for (int i = x1; i >= x2; i--) {
                    array[i][y1]++;
                }
            }
        }
    }

    public static int counter(int[][] array) {
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] >= 2) {
                    count++;
                }
            }
        }

        return count;
    }
}
