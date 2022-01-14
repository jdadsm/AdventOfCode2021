package Day2;
import java.util.*;
import java.io.*;

public class advent4 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent4.txt");
        Scanner sc = new Scanner(f);
        int depth = 0, length = 0, aim = 0;
        String[] input;

        while (sc.hasNextLine()) {
            input = sc.nextLine().split(" ");

            switch (input[0]) {
                case "up":
                    aim -= Integer.parseInt(input[1]);
                    break;

                case "down":
                    aim += Integer.parseInt(input[1]);
                    break;

                default:
                    length += Integer.parseInt(input[1]);
                    depth += aim * Integer.parseInt(input[1]);
                    break;
            }
        }

        sc.close();
        System.out.print(depth * length);
    }
}
