package Day21;

import java.util.*;
import java.io.*;
import ExtraClasses.Player;

public class advent42 {
    static Player player1;
    static Player player2;
    static long[][][][] savedWins1 = new long[21][21][10][10];
    static long[][][][] savedWins2 = new long[21][21][10][10];
    static Stack<Integer> positions1 = new Stack<Integer>();
    static Stack<Integer> positions2 = new Stack<Integer>();
    static long totalWins1 = 0;
    static long totalWins2 = 0;

    public static void main(String[] args) throws IOException {
        File f = new File("teste.txt");
        Scanner sc = new Scanner(f);
        String temp = sc.nextLine();
        int p1StartPos = Character.getNumericValue(temp.charAt(temp.length() - 1));
        player1 = new Player(p1StartPos);
        temp = sc.nextLine();
        int p2StartPos = Character.getNumericValue(temp.charAt(temp.length() - 1));
        player2 = new Player(p2StartPos);
        positions1.add(p1StartPos);
        positions2.add(p2StartPos);
        rollDiracDice1();

        System.out.println(savedWins1[0][0][p1StartPos - 1][p2StartPos - 1]);
        System.out.println(savedWins2[0][0][p1StartPos - 1][p2StartPos - 1]);

        sc.close();
    }

    public static long rollDiracDice1() {
        long wins1 = 0;
        long wins2 = 0;
        int player1score = player1.getScore();
        int player2score = player2.getScore();
        int player1pos = player1.getCurrentPosition() - 1;
        int player2pos = player2.getCurrentPosition() - 1;
        if (savedWins1[player1score][player2score][player1pos][player2pos] != 0 || savedWins2[player1score][player2score][player1pos][player2pos] != 0) {
            wins1 += savedWins1[player1score][player2score][player1pos][player2pos];
        } else {
            for (int i1 = 1; i1 <= 3; i1++) {
                for (int i2 = 1; i2 <= 3; i2++) {
                    for (int i3 = 1; i3 <= 3; i3++) {
                        if (player1.rollDirac(i1 + i2 + i3)) {
                            wins1++;
                            continue;
                        }
                        positions1.add(player1.getCurrentPosition());
                        wins2 += rollDiracDice2();
                    }
                }
            }
        }
        savedWins1[player1.getScore()][player2.getScore()][player1.getCurrentPosition()-1][player2.getCurrentPosition()-1] = wins1;
        player2.setScore(player2score - positions2.pop());
        if(!positions2.isEmpty()){
            player2.setCurrentPosition(positions2.peek()); 
        }
        
        return wins2;
    }

    public static long rollDiracDice2() {
        long wins1 = 0;
        long wins2 = 0;
        int player1score = player1.getScore();
        int player2score = player2.getScore();
        int player1pos = player1.getCurrentPosition() - 1;
        int player2pos = player2.getCurrentPosition() - 1;
        if (savedWins2[player1score][player2score][player1pos][player2pos] != 0 || savedWins1[player1score][player2score][player1pos][player2pos] != 0) {
            wins2 += savedWins2[player1score][player2score][player1pos][player2pos];
        } else {
            for (int i1 = 1; i1 <= 3; i1++) {
                for (int i2 = 1; i2 <= 3; i2++) {
                    for (int i3 = 1; i3 <= 3; i3++) {
                        if (player2.rollDirac(i1 + i2 + i3)) {
                            wins2++;
                            continue;
                        }
                        positions2.add(player2.getCurrentPosition());
                        wins1 += rollDiracDice1();
                    }
                }
            }
        }
        savedWins2[player1.getScore()][player2.getScore()][player1.getCurrentPosition()-1][player2.getCurrentPosition()-1] = wins2;
        player1.setScore(player1score - positions1.pop());
        if(!positions1.isEmpty()){
            player1.setCurrentPosition(positions1.peek()); 
        }
        return wins1;
    }

}
