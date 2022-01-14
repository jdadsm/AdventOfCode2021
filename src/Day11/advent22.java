package Day11;
import java.io.*;

public class advent22 {
    static int flashCounter = 0;
    static int FlashesPerStep = 0;

    public static void main(String[] args) throws IOException {
        File f = new File("advent22.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        // Scanner sc = new Scanner(f);
        String[] line = br.readLine().split("");
        int[][] array = new int[line.length][line.length];

        for (int i = 0; i < array.length; i++) {
            array[0][i] = Integer.parseInt(line[i]);
        }
        String linha;
        int n = 1;
        while ((linha = br.readLine()) != null) {
            line = linha.split("");
            for (int i = 0; i < array.length; i++) {
                array[n][i] = Integer.parseInt(line[i]);
            }
            n++;
        }
        int numberOfSteps = 1000;
        for (int i = 0; i < numberOfSteps; i++) {
            // System.out.println("Step " + (i + 1));
            step(array, i);
            if (FlashesPerStep == array.length * array.length) {
                System.out.println("Step " + (i + 1));
                break;
            } else {
                FlashesPerStep = 0;
            }
        }

        // System.out.println(flashCounter);

        br.close();
    }

    public static void step(int[][] array, int print) {
        String[][] flashes = new String[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j]++;
            }
        }
        checkNines(array, flashes);
        /*
         * if ((print + 1) % 1 == 0) {
         * for (int i = 0; i < array.length; i++) {
         * System.out.print("[");
         * for (int j = 0; j < array.length; j++) {
         * System.out.print(array[i][j] + ", ");
         * }
         * System.out.println("]");
         * }
         * System.out.print("\n");
         * System.out.println("Total Flashes:" + flashCounter);
         * }
         */
    }

    public static void checkNines(int[][] array, String[][] flashes) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] > 9) {
                    FlashEffect(array, i, j, flashes);
                }
            }
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] > 9) {
                    array[i][j] = 0;
                    flashCounter++;
                }
            }
        }
    }

    public static void FlashEffect(int[][] array, int i, int j, String[][] flashes) {
        if (flashes[i][j] == "flashed" || array[i][j] < 10) {
            return;
        }
        flashes[i][j] = "flashed";

        if (i == 0 && j == 0) {
            array[i][j + 1]++;
            FlashEffect(array, i, j + 1, flashes);
            array[i + 1][j]++;
            FlashEffect(array, i + 1, j, flashes);
            array[i + 1][j + 1]++;
            FlashEffect(array, i + 1, j + 1, flashes);
        } else if (i == 0 && j == array.length - 1) {
            array[i][j - 1]++;
            FlashEffect(array, i, j - 1, flashes);
            array[i + 1][j - 1]++;
            FlashEffect(array, i + 1, j - 1, flashes);
            array[i + 1][j]++;
            FlashEffect(array, i + 1, j, flashes);
        } else if (i == array.length - 1 && j == 0) {
            array[i - 1][j]++;
            FlashEffect(array, i - 1, j, flashes);
            array[i - 1][j + 1]++;
            FlashEffect(array, i - 1, j + 1, flashes);
            array[i][j + 1]++;
            FlashEffect(array, i, j + 1, flashes);
        } else if (i == array.length - 1 && j == array.length - 1) {
            array[i - 1][j - 1]++;
            FlashEffect(array, i - 1, j - 1, flashes);
            array[i - 1][j]++;
            FlashEffect(array, i - 1, j, flashes);
            array[i][j - 1]++;
            FlashEffect(array, i, j - 1, flashes);
        } else if (i == 0) {
            array[i][j - 1]++;
            FlashEffect(array, i, j - 1, flashes);
            array[i][j + 1]++;
            FlashEffect(array, i, j + 1, flashes);
            array[i + 1][j - 1]++;
            FlashEffect(array, i + 1, j - 1, flashes);
            array[i + 1][j]++;
            FlashEffect(array, i + 1, j, flashes);
            array[i + 1][j + 1]++;
            FlashEffect(array, i + 1, j + 1, flashes);
        } else if (j == 0) {
            array[i - 1][j]++;
            FlashEffect(array, i - 1, j, flashes);
            array[i - 1][j + 1]++;
            FlashEffect(array, i - 1, j + 1, flashes);
            array[i][j + 1]++;
            FlashEffect(array, i, j + 1, flashes);
            array[i + 1][j]++;
            FlashEffect(array, i + 1, j, flashes);
            array[i + 1][j + 1]++;
            FlashEffect(array, i + 1, j + 1, flashes);
        } else if (i == array.length - 1) {
            array[i][j - 1]++;
            FlashEffect(array, i, j - 1, flashes);
            array[i][j + 1]++;
            FlashEffect(array, i, j + 1, flashes);
            array[i - 1][j - 1]++;
            FlashEffect(array, i - 1, j - 1, flashes);
            array[i - 1][j]++;
            FlashEffect(array, i - 1, j, flashes);
            array[i - 1][j + 1]++;
            FlashEffect(array, i - 1, j + 1, flashes);
        } else if (j == array.length - 1) {
            array[i - 1][j]++;
            FlashEffect(array, i - 1, j, flashes);
            array[i - 1][j - 1]++;
            FlashEffect(array, i - 1, j - 1, flashes);
            array[i][j - 1]++;
            FlashEffect(array, i, j - 1, flashes);
            array[i + 1][j]++;
            FlashEffect(array, i + 1, j, flashes);
            array[i + 1][j - 1]++;
            FlashEffect(array, i + 1, j - 1, flashes);
        } else {
            array[i - 1][j - 1]++;
            FlashEffect(array, i - 1, j - 1, flashes);
            array[i - 1][j]++;
            FlashEffect(array, i - 1, j, flashes);
            array[i - 1][j + 1]++;
            FlashEffect(array, i - 1, j + 1, flashes);
            array[i][j - 1]++;
            FlashEffect(array, i, j - 1, flashes);
            array[i][j + 1]++;
            FlashEffect(array, i, j + 1, flashes);
            array[i + 1][j - 1]++;
            FlashEffect(array, i + 1, j - 1, flashes);
            array[i + 1][j]++;
            FlashEffect(array, i + 1, j, flashes);
            array[i + 1][j + 1]++;
            FlashEffect(array, i + 1, j + 1, flashes);
        }
        FlashesPerStep++;
    }
}