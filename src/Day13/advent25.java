package Day13;
import java.io.*;
import java.util.*;

public class advent25 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent25.txt");
        // BufferedReader br = new BufferedReader(new FileReader(f));
        Scanner sc = new Scanner(f);
        List<String> input = new ArrayList<String>();
        List<String> output = new ArrayList<String>();
        List<String> instructions = new ArrayList<String>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }

        int index = input.size() - 1;
        while (!input.get(index).isEmpty()) {
            instructions.add(input.remove(index));
            index--;
        }
        input.remove(index);

        int size = input.size();
        for (int i = 0; i < size; i++) {
            String[] point = input.get(i).split(",");
            int x = Integer.parseInt(point[0]);
            int y = Integer.parseInt(point[1]);
            char check = instructions.get(instructions.size() - 1).charAt(11);
            int fa = Integer.parseInt(
                    instructions.get(instructions.size() - 1).substring(13,
                            instructions.get(instructions.size() - 1).length()));
            // System.out.println(fa);
            // System.out.println(check);
            if (check == 'x' && x > fa) {
                x = -x + 2 * fa;
            } else if (check == 'y' && y > fa) {
                y = -y + 2 * fa;
            }
            String op = x + "," + y;
            if (!output.contains(op)) {
                output.add(op);
            }

        }
        System.out.println(output.size());

        // System.out.println(input.toString());
        // System.out.println(instructions.toString());
        sc.close();
    }
}