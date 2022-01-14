package Day8;
import java.util.*;
import java.io.*;

public class advent15 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent14.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        // Scanner sc = new Scanner(f);
        List<String[]> lista = new ArrayList<String[]>();
        String line;

        while ((line = br.readLine()) != null) {
            lista.add(line.split(" \\| ")[1].split(" "));
        }
        int count = 0;
        for (String[] s : lista) {
            for (String string : s) {
                if ((string.length() == 2) || (string.length() == 3) || (string.length() == 4)
                        || (string.length() == 7)) {
                    count++;
                }
            }
        }
        System.out.println(count);

        br.close();
    }
}
