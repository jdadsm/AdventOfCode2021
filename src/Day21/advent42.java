package Day21;

import java.util.*;
import java.io.*;
import ExtraClasses.Player;

public class advent42 {
    public static void main(String[] args) throws IOException{
        File f = new File("advent42.txt");
        Scanner sc = new Scanner(f);
        String temp = sc.nextLine();
        Player player1 = new Player(Character.getNumericValue(temp.charAt(temp.length()-1)));
        temp = sc.nextLine();
        Player player2 = new Player(Character.getNumericValue(temp.charAt(temp.length()-1)));

        rollDiceQuantum1(player1, player2);

        System.out.println(player1.getWins());
        System.out.println(player2.getWins());
        
        sc.close();
    }

    public static void rollDiceQuantum1(Player player1,Player player2){
        if(player1.rollQuantumDice(player1.getScore(), 1)){
            if(player1.rollQuantumDice(player1.getScore(), 2)){
                if(player1.rollQuantumDice(player1.getScore(), 3)){
                    return;
                }
                rollDiceQuantum2(player1,player2);
            }
        }
        rollDiceQuantum2(player1,player2);
        return;
    }

    public static void rollDiceQuantum2(Player player1,Player player2){
        if(player2.rollQuantumDice(player2.getScore(), 1)){
            if(player2.rollQuantumDice(player2.getScore(), 2)){
                if(player2.rollQuantumDice(player2.getScore(), 3)){
                    return;
                }
            }
            rollDiceQuantum1(player1,player2);
        }
        rollDiceQuantum1(player1,player2);
        return;
    }
}
