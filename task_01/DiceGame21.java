public class DiceGame21 extends Game {
    final private Dice die;
    private int countYou = 0, countBot = 0;

    DiceGame21(Dice die) {
        this.die = die;
    }

    public void play() {
        while (true) { 
            countBot = 0;
            countYou = 0;

            // First two throws (1)
            System.out.println("-- You roll --");
            for (int i = 0; i < 2; i++) {
                countYou += die.roll();
                showMoveInfo();
            }

            // Decide another throw (2)
            System.out.print("Do you want to throw another die? [_/n]: ");
            while (!scanner.nextLine().equals("n")) {
                countYou += die.roll();
                showMoveInfo();
                System.out.print("Do you want to throw another die? [_/n]: ");
            }

            // Check if got 21 from 3 throws (7)
            if (countYou == 21) {
                System.out.println("Your sum is 21, bull's-eye! You win!");
                scoreYou++;
                displayScore();
                continue;
            } else {
                System.out.println("Your sum is " + countYou + "\n");
            }

            // Avatar makes moves (4) 5 throws at once
            System.out.println("-- Pixel the Bot rolls --");
            for (int i = 0; i < 5; i++) {
                countBot += die.roll();
                showMoveInfo();
            }
            System.out.println("Bot sum is " + countBot + "\n");

            // Check if eqaual (6)
            if (countYou == countBot) {
                System.out.println("You have same count, Pixel the Bot wins!\n");
                scoreBot++;
            }

            // Check who is closer (5)
            int botDiff = 21 - countBot;
            int youDiff = 21 - countYou;

            if (botDiff < 0 && youDiff > 0) {
                System.out.println("Bot is over 21. You win!");
                scoreYou++;
            } else if (botDiff > 0 && youDiff < 0) {
                System.out.println("You are over 21. Bot wins!");
                scoreBot++;
            } else if (youDiff < botDiff) {
                System.out.println("You are closer. You win!");
                scoreYou++;
            } else {
                System.out.println("Bot is closer. Bot wins!");
                scoreBot++;
            }

            displayScore();

            System.out.print("Another round? [_/q]: ");
            if (scanner.nextLine().equals("q")) {
                break;
            }
        }
    }

    public void showMoveInfo() {
        System.out.println("Last roll " + die.toString() + " (" + die.getValue() + ")");
    }
}
