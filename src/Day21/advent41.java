package Day21;

import java.util.*;
import java.io.*;
import ExtraClasses.Player;

public class advent41 {
    static int timesDiceRolled = 0;
    static int diceValue = 0;
    public static void main(String[] args) throws IOException{
        File f = new File("advent41.txt");
        Scanner sc = new Scanner(f);
        String temp = sc.nextLine();
        Player player1 = new Player(Character.getNumericValue(temp.charAt(temp.length()-1)));
        temp = sc.nextLine();
        Player player2 = new Player(Character.getNumericValue(temp.charAt(temp.length()-1)));

        int rollsPerTurn = 3;
        int diceSides = 100;
        int winningScore = 1000;

        while(!player1.checkWinningScore(winningScore) && !player2.checkWinningScore(winningScore)){
            diceValue = player1.rollTheDice(diceValue, rollsPerTurn,diceSides);
            timesDiceRolled+= rollsPerTurn;
            if(player1.checkWinningScore(winningScore) || player2.checkWinningScore(winningScore)){
                break;
            }
            diceValue = player2.rollTheDice(diceValue, rollsPerTurn,diceSides);
            timesDiceRolled+= rollsPerTurn;
        }
        
        int res = 0;
        
        if (player1.checkWinningScore(winningScore)) {
            res = timesDiceRolled*player2.getScore();
        }else{
            res = timesDiceRolled*player1.getScore();
        }

        System.out.println(res);


        
        sc.close();
    }
}
