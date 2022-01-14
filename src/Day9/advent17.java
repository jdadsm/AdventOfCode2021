package Day9;
import java.util.*;
import java.io.*;

public class advent17 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent16.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        // Scanner sc = new Scanner(f);

        String line;
        int length = 0;
        int length2 = 0;
        String[][] array = new String[1000][10000];
        for (int i = 0; (line = br.readLine()) != null; i++) {
            if (i == 0)
                length = line.length();
            array[i] = line.split("");
            length2 = i + 1;
        }
        // System.out.println(length);
        // System.out.println(length2);
        String[][] mapa = new String[length2][length];
        int[][] numeros = new int[length2][length];

        for (int i = 0; i < length2; i++) {
            for (int j = 0; j < length; j++) {
                numeros[i][j] = Integer.parseInt(array[i][j]);
            }
        }
        int temp = 0;
        for (int i = 0; i < length2; i++) {
            for (int j = 0; j < length; j++) {
                if (j == 0) {
                    temp = numeros[i][j];
                    continue;
                }
                if (numeros[i][j] < temp) {
                    mapa[i][j] = "-";
                    mapa[i][j - 1] = "+";
                    temp = numeros[i][j];
                } else if (numeros[i][j] == temp) {
                    mapa[i][j] = "+";
                    mapa[i][j - 1] = "+";
                    temp = numeros[i][j];
                } else {
                    mapa[i][j] = "+";
                    if (mapa[i][j - 1] == null) {
                        mapa[i][j - 1] = "-";
                    }
                    temp = numeros[i][j];
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < length2; i++) {
            for (int j = 0; j < length; j++) {
                if (mapa[i][j] == "-") {
                    if (i != 0 && i != length2 - 1) {
                        if (!(numeros[i][j] < numeros[i + 1][j] && numeros[i][j] < numeros[i - 1][j])) {
                            mapa[i][j] = "+";
                        } else {
                            sum += numeros[i][j] + 1;
                        }
                    } else if (i == 0) {
                        if (!(numeros[i][j] < numeros[i + 1][j])) {
                            mapa[i][j] = "+";
                        } else {
                            sum += numeros[i][j] + 1;
                        }
                    } else if (i == length2 - 1) {
                        if (!(numeros[i][j] < numeros[i - 1][j])) {
                            mapa[i][j] = "+";
                        } else {
                            sum += numeros[i][j] + 1;
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(numeros));
        System.out.println(Arrays.deepToString(mapa));
        System.out.println(sum);
        br.close();
    }
}
