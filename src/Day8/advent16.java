package Day8;
import java.util.*;
import java.io.*;

public class advent16 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent16.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        // Scanner sc = new Scanner(f);
        List<String[]> lista = new ArrayList<String[]>();
        List<String[]> fourDigits = new ArrayList<String[]>();
        String line;

        while ((line = br.readLine()) != null) {
            lista.add(line.split(" \\| "));
            fourDigits.add(line.split(" \\| ")[1].split(" "));
        }
        char[] result;
        int sum = 0;
        for (String[] s : lista) {
            result = discoverPattern(s[0].split(" "));
            String[] pattern = getPattern(result);
            sum += calculateSum(s[1].split(" "), pattern);
        }

        System.out.println(sum);
        br.close();
    }

    public static char[] discoverPattern(String[] input) {
        char[] output = new char[7];
        String dois = "", tres = "", quatro = "", cincotemp = "";
        String[] cinco = new String[3];
        String[] seis = new String[3];

        int i1 = 0;
        int i2 = 0;
        for (String s : input) {
            if (s.length() == 2) {
                dois = s;
            } else if (s.length() == 3) {
                tres = s;
            } else if (s.length() == 4) {
                quatro = s;
            } else if (s.length() == 5) {
                cinco[i1] = s;
                i1++;
            } else if (s.length() == 6) {
                seis[i2] = s;
                i2++;
            }
        }

        for (int i = 0; i < tres.length(); i++) { // descobrir "a"
            if (dois.indexOf(tres.charAt(i)) == -1) {
                output[0] = tres.charAt(i);
            }
        }

        for (int i = 0; i < cinco.length; i++) { // descobrir "3"
            int count = 0;
            for (int j = 0; j < 2; j++) {
                if (cinco[i].indexOf(dois.charAt(j)) != -1) {
                    count++;
                }
            }
            if (count == 2) {
                cincotemp = cinco[i];
                break;
            }
        }

        char[] possibled1 = new char[2]; // array com "d" e "g"
        int n = 0;
        for (int i = 0; i < 5; i++) {
            if ((cincotemp.charAt(i) != output[0]) && (cincotemp.charAt(i) != dois.charAt(0))
                    && cincotemp.charAt(i) != dois.charAt(1)) {
                possibled1[n] = cincotemp.charAt(i);
                n++;
            }
        }

        char[] possibled2 = new char[2]; // array com "b" e "d"
        int m = 0;
        for (int i = 0; i < 4; i++) {
            if ((quatro.charAt(i) != dois.charAt(0)) && quatro.charAt(i) != dois.charAt(1)) {
                possibled2[m] = quatro.charAt(i);
                m++;
            }
        }

        for (int i = 0; i < possibled2.length; i++) { // descobrir "b","d" e "g"
            if (possibled1[i] == possibled2[0]) {
                output[3] = possibled2[0];
                output[1] = possibled2[1];
                if (i == 0) {
                    output[6] = possibled1[1];
                } else {
                    output[6] = possibled1[0];
                }

            } else if (possibled1[i] == possibled2[1]) {
                output[3] = possibled2[1];
                output[1] = possibled2[0];
                if (i == 0) {
                    output[6] = possibled1[1];
                } else {
                    output[6] = possibled1[0];
                }
            }
        }

        String numerodois = "";
        for (int i = 0; i < cinco.length; i++) { // descobrir "2"
            if (cinco[i] != cincotemp && cinco[i].indexOf(output[1]) == -1) {
                numerodois = cinco[i];
            }
        }

        for (int i = 0; i < numerodois.length(); i++) { // descobrir "c","e" e "f"
            if (numerodois.charAt(i) == dois.charAt(0)) {
                output[2] = numerodois.charAt(i);
                output[5] = dois.charAt(1);
            } else if (numerodois.charAt(i) == dois.charAt(1)) {
                output[2] = numerodois.charAt(i);
                output[5] = dois.charAt(0);
            } else if ((numerodois.charAt(i) != output[0]) && (numerodois.charAt(i) != output[3])
                    && (numerodois.charAt(i) != output[6])) {
                output[4] = numerodois.charAt(i);
            }
        }

        return output;
    }

    public static String[] getPattern(char[] result) {
        String[] pattern = new String[10];
        pattern[0] = "" + result[0] + result[1] + result[2] + result[4] + result[5] + result[6];
        pattern[1] = "" + result[2] + result[5];
        pattern[2] = "" + result[0] + result[2] + result[3] + result[4] + result[6];
        pattern[3] = "" + result[0] + result[2] + result[3] + result[5] + result[6];
        pattern[4] = "" + result[1] + result[2] + result[3] + result[5];
        pattern[5] = "" + result[0] + result[1] + result[3] + result[5] + result[6];
        pattern[6] = "" + result[0] + result[1] + result[3] + result[4] + result[5] + result[6];
        pattern[7] = "" + result[0] + result[2] + result[5];
        pattern[8] = "abcdefg";
        pattern[9] = "" + result[0] + result[1] + result[2] + result[3] + result[5] + result[6];
        return pattern;
    }

    public static int calculateSum(String[] fourDigits, String[] pattern) {
        String sum = "";
        for (int j = 0; j < fourDigits.length; j++) {
            for (int i = 0; i < pattern.length; i++) {
                char[] first = pattern[i].toCharArray();

                char[] second = fourDigits[j].toCharArray();

                Arrays.sort(first);

                Arrays.sort(second);

                if (Arrays.equals(first, second)) {
                    sum += i;
                }
            }
        }
        return Integer.parseInt(sum);
    }
}
