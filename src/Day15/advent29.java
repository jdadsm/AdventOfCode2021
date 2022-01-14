package Day15;
import java.util.*;
import java.io.*;

public class advent29 {
    static int maxrisk=0;
    static int tempRisk=0;
    static Stack<String> pilha = new Stack<String>();
    public static void main(String[] args) throws IOException {
        File f = new File("teste2.txt");
        Scanner sc = new Scanner(f);
        String line = sc.nextLine();
        String[] linha = line.split("");
        int length = line.length();
        int[][] input = new int[length][length];
        for (int i = 0; i < input[0].length; i++) {
            input[0][i] = Integer.parseInt(linha[i]);
        }
        int index = 1;
        while (sc.hasNextLine()) {
            linha = sc.nextLine().split("");
            for (int i = 0; i < input[0].length; i++) {
                input[index][i] = Integer.parseInt(linha[i]);
            }
            index++;
        }
        //maxrisk = length*length*9;
        pathOne(input);
        int[][] risk = new int[length][length];
        for (int[] arr : risk) {
            Arrays.fill(arr, maxrisk);
        }
        updateRisk(input, risk, 0, 0);
        /*
        for (int i = 0; i < risk.length; i++) {
            for (int j = 0; j < risk.length; j++) {
                System.out.print(risk[i][j]+", ");
            }
            System.out.println();
        }
        */
        System.out.println(risk[length-1][length-1]);
        
        sc.close();
    }
    public static void pathOne(int[][] input) {
        int temp = 0;
        for (int i = 0; i < input.length; i++) {
            temp += input[0][i];
        }
        for (int i = 1; i < input.length; i++) {
            temp += input[input.length - 1][i];
        }
        maxrisk = temp;
    }

    public static void updateRisk(int[][] input,int[][] risk,int i,int j){
        if (i == input.length - 1 && j == input.length - 1){
            if (tempRisk < risk[i][j]) {
                tempRisk += input[i][j];
                risk [i][j] = tempRisk;
                //System.out.println(pilha.toString());
                //System.out.println(risk[i][j]);
                tempRisk -= input[i][j];
            }
            return;
        }

        if (pilha.contains(i + "," + j)) {
            return;
        }
        pilha.add(i + "," + j);
        tempRisk+= input[i][j];
        if(tempRisk<risk[i][j]){
            risk[i][j]=tempRisk;
        }else{
            pilha.pop();
            tempRisk -= input[i][j];
            return;
        }
        if (i == 0 && j == 0) {
            tempRisk -= input[i][j];
            updateRisk(input, risk, i, j + 1);
            updateRisk(input, risk, i + 1, j);
        } else if (i == 0 && j == input.length - 1) {
            //updateRisk(input, risk, i, j - 1);
            updateRisk(input, risk, i + 1, j);
        } else if (i == input.length - 1 && j == 0) {
            //updateRisk(input, risk, i - 1, j);
            updateRisk(input, risk, i, j + 1);
        } else if (i == 0) {
            updateRisk(input, risk, i + 1, j);
            updateRisk(input, risk, i, j + 1);
        } else if (j == 0) {
            updateRisk(input, risk, i + 1, j);
            updateRisk(input, risk, i, j + 1);
        } else if (i == input.length - 1) {
            //updateRisk(input, risk, i - 1, j);
            updateRisk(input, risk, i, j + 1);
        } else if (j == input.length - 1) {
            updateRisk(input, risk, i + 1, j);
            //updateRisk(input, risk, i, j - 1);
        } else {
            //System.out.println(i+","+j);
            updateRisk(input, risk, i + 1, j);
            updateRisk(input, risk, i, j + 1);
            //updateRisk(input, risk, i, j - 1);
            //updateRisk(input, risk, i - 1, j);
        }

        tempRisk -= input[i][j];
        pilha.pop();

        return;
    }
}