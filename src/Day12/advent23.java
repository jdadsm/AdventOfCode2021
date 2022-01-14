package Day12;
import java.io.*;
import java.util.*;

public class advent23 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent23.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        // Scanner sc = new Scanner(f);
        String linha;
        String[] line;
        Queue<String[]> temp = new LinkedList<String[]>();
        while ((linha = br.readLine()) != null) {
            line = linha.split("-");
            temp.add(line);
        }
        String[][] input = new String[temp.size()][2];
        for (int i = 0; i < input.length; i++) {
            String[] t = temp.remove();
            input[i][0] = t[0];
            input[i][1] = t[1];
        }
        // System.out.println(Arrays.deepToString(input));
        List<String> output = new ArrayList<String>();

        output = getPaths(input);

        // System.out.println(output.toString());
        System.out.println(output.size());
        br.close();
    }

    public static List<String> getPaths(String[][] input) {
        List<String> currentPath = new ArrayList<String>();
        List<String> output = new ArrayList<String>();
        List<String> LWCounter = new ArrayList<String>();
        currentPath.add("start");
        LWCounter.add("start");
        for (int i = 0; i < input.length; i++) {
            if (input[i][0].equals(currentPath.get(currentPath.size() - 1))) {
                if (LWCounter.contains(input[i][1])) {
                    continue;
                }
                currentPath.add(input[i][1]);
                if (isStringLowerCase(input[i][1])) {
                    LWCounter.add(input[i][1]);
                }
                getPaths(input, currentPath, output, LWCounter);
            } else if (input[i][1].equals(currentPath.get(currentPath.size() - 1))) {
                if (LWCounter.contains(input[i][0])) {
                    continue;
                }
                currentPath.add(input[i][0]);
                if (isStringLowerCase(input[i][0])) {
                    LWCounter.add(input[i][0]);
                }
                getPaths(input, currentPath, output, LWCounter);
            }
        }
        return output;
    }

    private static void getPaths(String[][] input, List<String> currentPath, List<String> output,
            List<String> LWCounter) {
        if (currentPath.get(currentPath.size() - 1).equals("end")) {
            output.add(currentPath.toString());
            currentPath.remove("end");
            LWCounter.remove("end");
            return;
        }
        for (int i = 0; i < input.length; i++) {
            if (input[i][0].equals(currentPath.get(currentPath.size() - 1))) {
                if (LWCounter.contains(input[i][1])) {
                    continue;
                }
                currentPath.add(input[i][1]);
                if (isStringLowerCase(input[i][1])) {
                    LWCounter.add(input[i][1]);
                }
                getPaths(input, currentPath, output, LWCounter);
            } else if (input[i][1].equals(currentPath.get(currentPath.size() - 1))) {
                if (LWCounter.contains(input[i][0])) {
                    continue;
                }
                currentPath.add(input[i][0]);
                if (isStringLowerCase(input[i][0])) {
                    LWCounter.add(input[i][0]);
                }
                getPaths(input, currentPath, output, LWCounter);
            }
        }
        if (isStringLowerCase(currentPath.get(currentPath.size() - 1))) {
            LWCounter.remove(currentPath.get(currentPath.size() - 1));
        }
        currentPath.remove(currentPath.size() - 1);
        return;
    }

    private static boolean isStringLowerCase(String str) {

        // convert String to char array
        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {

            // if any character is not in lower case, return false
            if (!Character.isLowerCase(charArray[i]))
                return false;
        }

        return true;

    }
}
