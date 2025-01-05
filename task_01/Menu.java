import java.util.Scanner;

public class Menu {
    final private Scanner scanner;

    Menu() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        String choice;
        printMenu();
        do {
            choice = getChoice().toLowerCase();
            handleMenuChoice(choice);
        } while (!choice.equals("q"));
    }

    private void handleMenuChoice(String choice) {
        switch (choice) {
            case "1" -> Avatar.print();
            case "2" -> Today.print(); 
            case "3a" -> playRockPaperScissors();
            case "3b" -> playDiceGame();
            case "m" -> printMenu();
            case "q" -> {}
            default -> System.err.println("Bad menu choice, use 'm' to get the menu.");
        }
    }

    private void playRockPaperScissors() {
        RockPaperScissors gameRPC = new RockPaperScissors();
        gameRPC.play();
    }

    private void playDiceGame() {
        Dice die = new Dice(6);
        DiceGame21 game21 = new DiceGame21(die);
        game21.play();
    }

    private String getChoice() {
        System.out.print("Your choice: ");
        return scanner.nextLine();
    }

    private void printMenu() {
        System.out.println("""
                 -----------------
                | 1) Print my avatar
                | 2) Print today date/time
                | 3A) Rock paper scissors
                | 3B) Dice Game 21
                | m) Print menu
                | q) Quit
                 -----------------""");
    }
}
