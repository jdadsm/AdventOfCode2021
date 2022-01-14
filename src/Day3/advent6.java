package Day3;
import java.util.*;
import java.io.*;

public class advent6 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent6.txt");
        Scanner sc = new Scanner(f);

        int index = 0;
        String oxygen = "", co2 = "";
        String[] array = new String[1000000];
        String[] array2 = new String[1000000];
        while (sc.hasNextLine()) {
            array[index] = sc.nextLine();
            index++;
        }

        int length = array[0].length();

        java.lang.System.arraycopy(array, 0, array2, 0, array.length);

        oxygen = getOxygen(array);
        System.out.println(oxygen);
        /*
         * for (int i = 0; i < array2.length; i++) {
         * if (array2[i] == null) {
         * break;
         * }
         * System.out.println(array2[i]);
         * }
         */
        co2 = getCO2(array2);
        System.out.println(co2);
        int o = 0, c = 0;
        for (int j = 0; j < length; j++) {
            if (oxygen.charAt(j) == '1') {
                o += Math.pow(2, length - 1 - j);
            }
            if (co2.charAt(j) == '1') {
                c += Math.pow(2, length - 1 - j);
            }
        }
        int result = o * c;
        System.out.println(result);
        sc.close();
    }

    public static String getOxygen(String[] array) {
        int zeros = 0, uns = 0;
        String oxygen = "";

        for (int i = 0; i < 12; i++) {
            for (int k = 0; k < array.length; k++) {
                if (array[k] == null) {
                    break;
                }
                if (array[k] != "skip") {
                    if (array[k].charAt(i) == '1') {
                        uns++;
                    } else {
                        zeros++;
                    }
                }
            }
            if (uns >= zeros) {
                for (int l = 0; l < array.length; l++) {
                    if (array[l] == null) {
                        break;
                    }
                    if (array[l] != "skip") {
                        if (array[l].charAt(i) == '0') {
                            array[l] = "skip";
                        }
                    }
                }
            } else {
                for (int m = 0; m < array.length; m++) {
                    if (array[m] == null) {
                        break;
                    }
                    if (array[m] != "skip") {
                        if (array[m].charAt(i) == '1') {
                            array[m] = "skip";
                        }
                    }
                }
            }
            zeros = 0;
            uns = 0;
            int n = 0;
            int count = 0;
            while (array[n] != null) {
                if (array[n] != "skip") {
                    count++;
                    oxygen = array[n];
                }
                n++;
            }
            if (count == 1) {
                return oxygen;
            }

        }
        return oxygen;
    }

    public static String getCO2(String[] array2) {
        int zeros = 0, uns = 0;
        String co2 = "";
        for (int i = 0; i < 12; i++) {
            for (int k = 0; k < array2.length; k++) {
                if (array2[k] == null) {
                    break;
                }
                if (array2[k] != "skip") {
                    if (array2[k].charAt(i) == '1') {
                        uns++;
                    } else {
                        zeros++;
                    }
                }
            }
            if (uns >= zeros) {
                for (int l = 0; l < array2.length; l++) {
                    if (array2[l] == null) {
                        break;
                    }
                    if (array2[l] != "skip") {
                        if (array2[l].charAt(i) == '1') {
                            array2[l] = "skip";
                        }
                    }
                }
            } else {
                for (int m = 0; m < array2.length; m++) {
                    if (array2[m] == null) {
                        break;
                    }
                    if (array2[m] != "skip") {
                        if (array2[m].charAt(i) == '0') {
                            array2[m] = "skip";
                        }
                    }
                }
            }
            zeros = 0;
            uns = 0;
            int n = 0;
            int count = 0;
            while (array2[n] != null) {
                if (array2[n] != "skip") {
                    count++;
                    co2 = array2[n];
                }
                n++;
            }
            if (count == 1) {
                return co2;
            }

        }
        int n1 = 0;
        while (array2[n1] != null) {

            if (array2[n1] != "skip") {
                if (array2[n1].charAt(11) == '0') {
                    co2 = array2[n1];
                }
            }
            n1++;
        }
        return co2;
    }
}
