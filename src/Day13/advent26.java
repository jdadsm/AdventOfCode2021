package Day13;
import java.io.*;
import java.util.*;

public class advent26 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent26.txt");
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

        int size2 = instructions.size();
        for (int j = 0; j < size2; j++) {
            int size = input.size();
            for (int i = 0; i < size; i++) {
                String[] point = input.get(i).split(",");
                int x = Integer.parseInt(point[0]);
                int y = Integer.parseInt(point[1]);
                char check = instructions.get(instructions.size() - 1 - j).charAt(11);
                int fa = Integer.parseInt(
                        instructions.get(instructions.size() - 1 - j).substring(13,
                                instructions.get(instructions.size() - 1 - j).length()));
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
            if (j != instructions.size() - 1) {
                input.clear();
                input.addAll(output);
                output.clear();
            } else {
                int[][] res = new int[6][40];
                int size3 = output.size();
                for (int i = 0; i < size3; i++) {
                    String[] temp = output.remove(0).split(",");
                    int x = Integer.parseInt(temp[1]);
                    int y = Integer.parseInt(temp[0]);
                    res[x][y] = 1;
                }
                for (int i = 0; i < res.length; i++) {
                    for (int k = 0; k < res[0].length; k++) {
                        if (res[i][k] == 1) {
                            System.out.print("0");
                        } else {
                            System.out.print(" ");
                        }

                    }
                    System.out.println();
                }
            }

        }
        System.out.println(output.size());

        // System.out.println(input.toString());
        // System.out.println(instructions.toString());
        sc.close();
    }
}