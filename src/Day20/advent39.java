package Day20;

import java.util.*;
import java.io.*;

public class advent39 {
    static char infiniteChar = '.';
    public static void main(String[] args) throws IOException {
        File f = new File("advent39.txt");
        Scanner sc = new Scanner(f);
        String algorithm = sc.nextLine();

        List<String> input = new ArrayList<String>();
        while (sc.hasNextLine()) {
            String l = sc.nextLine();
            if (!l.isEmpty()) {
                input.add(l);
            }
        }

        int n = 2;
        List<String> output = new ArrayList<String>();
        while (n != 0) {
            output = applyImageEnhancement(input, algorithm);
            input.clear();
            input.addAll(output);
            n--;

            System.out.println((2 - n) + "\n");
            for (String string : output) {
                System.out.println(string);
            }
        }

        int count = 0;
        for (int i = 0; i < output.size(); i++) {
            String temp = output.get(i);
            int size = temp.length();
            for (int j = 0; j < size; j++) {
                if (temp.charAt(j) == '#') {
                    count++;
                }
            }
        }

        System.out.println("Número de pixels: " + count);

        sc.close();
    }

    public static List<String> applyImageEnhancement(List<String> input, String algorithm) {
        List<String> output = new ArrayList<String>();
        int index1 = 1;
        
        padding(input, algorithm,2);

        while (index1 < input.size() - 1) {

            String temp = "";
            for (int i = 1; i < input.get(0).length() - 1; i++) {
                temp += getPixel(input, algorithm, index1, i);
            }
            output.add(temp);
            index1++;
        }
        

        return output;
    }

    public static String getPixel(List<String> input, String algorithm, int i, int j) {
        String res = "";
        String start = "";
        start += input.get(i - 1).charAt(j - 1);
        start += input.get(i - 1).charAt(j);
        start += input.get(i - 1).charAt(j + 1);
        start += input.get(i).charAt(j - 1);
        start += input.get(i).charAt(j);
        start += input.get(i).charAt(j + 1);
        start += input.get(i + 1).charAt(j - 1);
        start += input.get(i + 1).charAt(j);
        start += input.get(i + 1).charAt(j + 1);

        res += algorithm.charAt(BinaryToInt(start));

        return res;
    }

    public static int BinaryToInt(String input) {
        int res = 0;
        String temp = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '#') {
                temp += "1";
            } else {
                temp += "0";
            }
        }
        for (int i = 0; i < input.length(); i++) {
            res += Character.getNumericValue(temp.charAt(i)) * Math.pow(2, input.length() - i - 1);
        }
        return res;
    }

    public static void padding(List<String> input,String algorithm,int limit) {
        List<String> temp = new ArrayList<String>();
       

        for (int l = 0; l < limit; l++) {
            String first = "";
            String last = "";
            temp.clear();
            for (int i = 0; i < input.size() + 2; i++) {
                first += infiniteChar;
                last += infiniteChar;
            }
            temp.add(first);
            for (int i = 0; i < input.size(); i++) {
                String tempLine = infiniteChar + input.get(i) + infiniteChar;
                temp.add(tempLine);
            }
            temp.add(last);
            input.clear();
            input.addAll(temp);
        }

        if(infiniteChar=='.' && algorithm.charAt(0)=='#'){
            infiniteChar='#';
        }else if(infiniteChar=='#' && algorithm.charAt(algorithm.length()-1)=='.'){
            infiniteChar='.';
        }
    }

}
