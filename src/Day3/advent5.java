package Day3;
import java.util.*;
import java.io.*;

public class advent5 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent5.txt");
        Scanner sc = new Scanner(f);

        int zeros = 0, uns = 0, index = 0;
        long powerconsumption, ie = 0, ig = 0;
        String gammarate = "", epsilonrate = "";
        String[] array = new String[10000000];
        while (sc.hasNextLine()) {
            array[index] = sc.nextLine();
            index++;
        }

        for (int i = 0; i < array[0].length(); i++) {
            for (int k = 0; k < array.length; k++) {
                if (array[k] == null) {
                    break;
                }
                if (array[k].charAt(i) == '1') {
                    uns++;
                } else {
                    zeros++;
                }
            }
            if (uns > zeros) {
                gammarate += '1';
                epsilonrate += '0';
            } else {
                gammarate += '0';
                epsilonrate += '1';
            }
            zeros = 0;
            uns = 0;
        }
        System.out.println(gammarate);
        System.out.println(epsilonrate);

        for (int j = 0; j < array[0].length(); j++) {
            if (gammarate.charAt(j) == '1') {
                ig += Math.pow(2, array[0].length() - 1 - j);
            } else {
                ie += Math.pow(2, array[0].length() - 1 - j);
            }
        }
        sc.close();

        powerconsumption = ie * ig;
        System.out.println(powerconsumption);

    }
}
