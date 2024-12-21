public class DiceGame21 extends Game {
    final private Dice die;
    private int countPlayer = 0, countBot = 0;

    DiceGame21(Dice die) {
        this.die = die;
    }

    public void play() {
        while (true) {
            playRound();

            System.out.print("Another round? [_/q]: ");
            if (scanner.nextLine().equals("q")) {
                break;
            }
        }
    }

    private void playRound() {
        countBot = 0;
        countPlayer = 0;

        playerTurn();
        
        if (isBullseye()) {
            return;
        }

        botTurn();
        determineWinner();
        displayScore();
    }

    private void playerTurn() {
        System.out.println("-- You roll --");
        
        // First two mandatory throws
        for (int i = 0; i < 2; i++) {
            rollForPlayer();
        }

        // Optional additional throws
        while (wantsToRollAgain()) {
            rollForPlayer();
        }
    }

    private void rollForPlayer() {
        countPlayer += die.roll();
        showMoveInfo();
    }

    private boolean wantsToRollAgain() {
        System.out.print("Do you want to throw another die? [_/n]: ");
        return !scanner.nextLine().equals("n");
    }

    private boolean isBullseye() {
        if (countPlayer == 21) {
            System.out.println("Your sum is 21, bull's-eye! You win!");
            scorePlayer++;
            displayScore();
            return true;
        }
        System.out.println("Your sum is " + countPlayer + "\n");
        return false;
    }

    private void botTurn() {
        System.out.println("-- Pixel the Bot rolls --");
        for (int i = 0; i < 5; i++) {
            countBot += die.roll();
            showMoveInfo();
        }
        System.out.println("Bot sum is " + countBot + "\n");
    }

    private void determineWinner() {
        if (countPlayer == countBot) {
            System.out.println("You have same count, Pixel the Bot wins!\n");
            scoreBot++;
            return;
        }

        int botDiff = 21 - countBot;
        int youDiff = 21 - countPlayer;

        if (botDiff < 0 && youDiff > 0) {
            System.out.println("Bot is over 21. You win!");
            scorePlayer++;
        } else if (botDiff > 0 && youDiff < 0) {
            System.out.println("You are over 21. Bot wins!");
            scoreBot++;
        } else if (youDiff < botDiff) {
            System.out.println("You are closer. You win!");
            scorePlayer++;
        } else {
            System.out.println("Bot is closer. Bot wins!");
            scoreBot++;
        }
    }

    public void showMoveInfo() {
        System.out.println("Last roll " + die.toString() + " (" + die.getValue() + ")");
    }
}