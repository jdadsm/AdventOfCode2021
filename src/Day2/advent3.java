package Day2;
import java.util.*;
import java.io.*;

public class advent3 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent3.txt");
        Scanner sc = new Scanner(f);
        int depth = 0, length = 0;
        String[] input;

        while (sc.hasNextLine()) {
            input = sc.nextLine().split(" ");

            switch (input[0]) {
                case "up":
                    depth -= Integer.parseInt(input[1]);
                    break;

                case "down":
                    depth += Integer.parseInt(input[1]);
                    break;

                default:
                    length += Integer.parseInt(input[1]);
                    break;
            }
        }

        sc.close();
        System.out.print(depth * length);
    }
}
