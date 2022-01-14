package Day9;
import java.util.*;
import java.io.*;

public class advent18 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent17.txt");
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

        String[][] mapa = new String[length2][length];
        int[][] numeros = new int[length2][length];

        for (int i = 0; i < length2; i++) {
            for (int j = 0; j < length; j++) {
                numeros[i][j] = Integer.parseInt(array[i][j]);
            }
        }
        getLowPoints(mapa, numeros, length, length2);
        /*
         * for (int i = 0; i < length2; i++) {
         * for (int j = 0; j < length; j++) {
         * System.out.print("[" + mapa[i][j] + "]");
         * }
         * System.out.print("\n");
         * }
         */
        getBasin(mapa, numeros, length, length2);

        // System.out.println(Arrays.deepToString(numeros));
        /*
         * System.out.print("\n");
         * for (int i = 0; i < length2; i++) {
         * for (int j = 0; j < length; j++) {
         * System.out.print("[" + numeros[i][j] + "]");
         * }
         * System.out.print("\n");
         * }
         * System.out.print("\n");
         */
        // System.out.println(Arrays.deepToString(mapa));
        /*
         * for (int i = 0; i < length2; i++) {
         * for (int j = 0; j < length; j++) {
         * System.out.print("[" + mapa[i][j] + "]");
         * }
         * System.out.print("\n");
         * }
         */

        br.close();
    }

    public static void getLowPoints(String[][] mapa, int[][] numeros, int length, int length2) {
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
        for (int i = 0; i < length2; i++) {
            for (int j = 0; j < length; j++) {
                if (mapa[i][j] == "-") {
                    if (i != 0 && i != length2 - 1) {
                        if (!(numeros[i][j] < numeros[i + 1][j] && numeros[i][j] < numeros[i - 1][j])) {
                            mapa[i][j] = "+";
                        }
                    } else if (i == 0) {
                        if (!(numeros[i][j] < numeros[i + 1][j])) {
                            mapa[i][j] = "+";
                        }
                    } else if (i == length2 - 1) {
                        if (!(numeros[i][j] < numeros[i - 1][j])) {
                            mapa[i][j] = "+";
                        }
                    }
                }
            }
        }
        return;
    }

    public static void getBasin(String[][] mapa, int[][] numeros, int length, int length2) {
        int[] basinsSize = new int[length2 * length];
        int n = 0;
        for (int i = 0; i < length2; i++) {
            for (int j = 0; j < length; j++) {
                if (mapa[i][j] == "+") {
                    continue;
                }
                basinsSize[n] = checkCoordenate(mapa, numeros, length, length2, i, j);
                n++;
                // System.out.println(basinsSize[n]);
            }
        }
        Arrays.sort(basinsSize);
        // System.out.println(Arrays.toString(basinsSize));
        System.out.println((basinsSize[basinsSize.length - 1] + 1) * (basinsSize[basinsSize.length - 2] + 1)
                * (basinsSize[basinsSize.length - 3] + 1));
    }

    private static int checkCoordenate(String[][] mapa, int[][] numeros, int length, int length2, int i, int j) {
        int c = 0;
        if (numeros[i][j] == 9) {
            return c;
        }
        if (i != 0) {
            if ((numeros[i - 1][j] - numeros[i][j] > 0) && numeros[i - 1][j] != 9) { // verificar esquerda
                if (mapa[i - 1][j] != "-") {
                    mapa[i - 1][j] = "-";
                    c++;
                    c += checkCoordenate(mapa, numeros, length, length2, i - 1, j);
                }
            }
        }
        if (i != length2 - 1) {
            if ((numeros[i + 1][j] - numeros[i][j] > 0) && numeros[i + 1][j] != 9) { // verificar direita
                if (mapa[i + 1][j] != "-") {
                    mapa[i + 1][j] = "-";
                    c++;
                    c += checkCoordenate(mapa, numeros, length, length2, i + 1, j);
                }
            }
        }
        if (j != 0) {
            if ((numeros[i][j - 1] - numeros[i][j] > 0) && numeros[i][j - 1] != 9) { // verificar acima
                if (mapa[i][j - 1] != "-") {
                    mapa[i][j - 1] = "-";
                    c++;
                    c += checkCoordenate(mapa, numeros, length, length2, i, j - 1);
                }
            }
        }
        if (j != length - 1) {
            if ((numeros[i][j + 1] - numeros[i][j] > 0) && numeros[i][j + 1] != 9) { // verificar abaixo
                if (mapa[i][j + 1] != "-") {
                    mapa[i][j + 1] = "-";
                    c++;
                    c += checkCoordenate(mapa, numeros, length, length2, i, j + 1);
                }
            }
        }

        return c;
    }

}
