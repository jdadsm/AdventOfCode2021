package Day15;
import java.util.*;
import java.io.*;

public class advent30 {
    static int maxrisk=0;
    static int tempRisk=0;
    static Stack<String> pilha = new Stack<String>();
    public static void main(String[] args) throws IOException {
        File f = new File("advent29.txt");
        Scanner sc = new Scanner(f);
        String line = sc.nextLine();
        String[] linha = line.split("");
        int length = line.length();
        int[][] input = new int[length*5][length*5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int j2 = 0; j2 < length; j2++) {
                    input[j*length][j2+i*length] = Integer.parseInt(linha[j2])+i+j;
                    if(input[j*length][j2+i*length]>9){
                        input[j*length][j2+i*length]-=9;
                    }
                }
            }
        }
        int index = 1;
        while (sc.hasNextLine()) {
            linha = sc.nextLine().split("");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int j2 = 0; j2 < length; j2++) {
                        input[j*length+index][j2+i*length] = Integer.parseInt(linha[j2])+i+j;
                        if(input[j*length+index][j2+i*length]>9){
                            input[j*length+index][j2+i*length]-=9;
                        }
                    }
                    
                }
            }
            index++;
        }
        //maxrisk = length*length*9;
        //pathOne(input);
        maxrisk=2856;
        System.out.println(maxrisk);
        int[][] risk = new int[length*5][length*5];
        for (int[] arr : risk) {
            Arrays.fill(arr, maxrisk);
        }
        updateRisk(input, risk, 0, 0);
        /*
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                System.out.print(input[i][j]+", ");
            }
            System.out.println();
        }
        */
        
        System.out.println(risk[(length*5)-1][(length*5)-1]);

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
        maxrisk = temp*2/3;
    }

    public static void updateRisk(int[][] input,int[][] risk,int i,int j){
        if (i == input.length - 1 && j == input.length - 1){
            if (tempRisk <= risk[i][j]) {
                tempRisk += input[i][j];
                risk [i][j] = tempRisk;
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
            updateRisk(input, risk, i, j - 1);
            updateRisk(input, risk, i + 1, j);
        } else if (i == input.length - 1 && j == 0) {
            updateRisk(input, risk, i - 1, j);
            updateRisk(input, risk, i, j + 1);
        } else if (i == 0) {
            updateRisk(input, risk, i, j + 1);
            updateRisk(input, risk, i + 1, j);
        } else if (j == 0) {
            updateRisk(input, risk, i, j + 1);
            updateRisk(input, risk, i + 1, j);
        } else if (i == input.length - 1) {
            updateRisk(input, risk, i - 1, j);
            updateRisk(input, risk, i, j + 1);
        } else if (j == input.length - 1) {
            updateRisk(input, risk, i + 1, j);
            updateRisk(input, risk, i, j - 1);
        } else {
            updateRisk(input, risk, i, j + 1);
            updateRisk(input, risk, i + 1, j);
            //updateRisk(input, risk, i, j - 1);
            //updateRisk(input, risk, i - 1, j);
        }

        tempRisk -= input[i][j];
        pilha.pop();

        return;
    }
}