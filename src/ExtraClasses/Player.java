package ExtraClasses;

public class Player {
    private int currentPosition;
    private int score;

    public Player(int startPosition){
        currentPosition = startPosition;
        score = 0;
    }

    public int getScore(){
        return score;
    }

    public int getCurrentPosition(){
        return currentPosition;
    }

    public int rollTheDice(int diceValue,int times,int diceSides){
        int sum = 0;
        
        for (int i = 0; i < times; i++) {
            diceValue++;
            sum+= diceValue;
            if(diceValue==diceSides){
                diceValue=0;
            }
        }
        movePawn(sum);
        return diceValue;
    }

    public void movePawn(int dice){
        dice = dice%10;
        currentPosition+=dice;
        if(currentPosition>10){
            currentPosition=currentPosition%10;
        }
        score += currentPosition;
    }

    public boolean checkWinningScore(int winningScore){
        if(score>=winningScore){
            return true;
        }
        return false;
    }
}
