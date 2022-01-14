package Day4;
import java.util.*;
import java.io.*;

public class advent7 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent7.txt");
        List<String[][]> result = new ArrayList<String[][]>();
        Scanner sc = new Scanner(f);

        String[] numeros = sc.nextLine().split(",");

        while (sc.hasNextLine()) {
            String[][] input = new String[5][5];
            String linha = sc.nextLine();
            for (int i = 0; i < 5; i++) {
                linha = sc.nextLine();
                if (linha.charAt(0) == ' ') {
                    linha = linha.substring(1, linha.length());
                }
                String[] line = linha.split("\\s+");
                for (int j = 0; j < 5; j++) {
                    input[i][j] = line[j];
                }
                if (i == 4) {
                    result.add(input);
                }
            }
        }
        int[] output = new int[3];
        output[0] = numeros.length;
        for (String[][] s : result) {
            int[] teste = bingo(s, numeros);
            // System.out.println(Arrays.toString(teste));
            if (teste[0] < output[0]) {
                output[0] = teste[0];
                output[1] = teste[1];
                output[2] = teste[2];
            }
            // System.out.println(Arrays.toString(output));
        }

        System.out.println(output[1] * output[2]);
        /*
         * for (String n : numeros) {
         * System.out.print(n + ", ");
         * }
         * for (String[][] l : result) {
         * System.out.println(Arrays.deepToString(l));
         * }
         */
        sc.close();
    }

    public static int[] bingo(String[][] mapa, String[] numeros) {
        int[] result = new int[3]; // 0-em que numero acabou 1-último número a ser retirado 2-soma de todos os
                                   // números que ficaram no mapa
        int sum = 0;
        for (int i = 0; i < numeros.length; i++) {
            result[0] = i;
            if (checkBingo(mapa)) {
                break;
            }
            // System.out.println(Arrays.deepToString(mapa));
            for (int j = 0; j < 5; j++) {
                for (int j2 = 0; j2 < 5; j2++) {
                    if (mapa[j][j2].equals(numeros[i])) {
                        result[1] = Integer.parseInt(mapa[j][j2]);
                        mapa[j][j2] = "";
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (mapa[i][j].equals("")) {
                    continue;
                } else {
                    sum += Integer.parseInt(mapa[i][j]);
                }
            }
        }

        result[2] = sum;

        // System.out.println(Arrays.toString(result));
        return result;
    }

    public static boolean checkBingo(String[][] mapa) {

        for (int i = 0; i < 5; i++) { // verificar linhas
            String check = "";
            for (int j = 0; j < 5; j++) {
                check = check + mapa[i][j];
            }
            if (check.equals("")) {
                return true;
            }
        }

        for (int i2 = 0; i2 < 5; i2++) { // verificar colunas
            String check = "";
            for (int j2 = 0; j2 < 5; j2++) {
                check = check + mapa[j2][i2];
            }
            if (check.equals("")) {
                return true;
            }
        }

        return false;
    }
}
