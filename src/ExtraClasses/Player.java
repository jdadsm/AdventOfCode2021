package ExtraClasses;

public class Player {
    private int currentPosition;
    private int score;

    public Player(int startPosition) {
        currentPosition = startPosition;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int rollTheDice(int diceValue, int times, int diceSides) {
        int sum = 0;

        for (int i = 0; i < times; i++) {
            diceValue++;
            sum += diceValue;
            if (diceValue == diceSides) {
                diceValue = 0;
            }
        }
        movePawn(sum);
        return diceValue;
    }

    public void movePawn(int dice) {
        dice = dice % 10;
        currentPosition += dice;
        if (currentPosition > 10) {
            currentPosition = currentPosition % 10;
        }
        score += currentPosition;
    }

    public boolean checkWinningScore(int winningScore) {
        if (score >= winningScore) {
            return true;
        }
        return false;
    }

    public boolean rollDirac(int val) {
        int prevScore = getScore();
        int prevPos = getCurrentPosition();
        movePawn(val);
        if (checkWinningScore(21)) {
            setScore(prevScore);
            setCurrentPosition(prevPos);
            return true;
        }
        return false;
    }
}
