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
            choice = getChoice();
            switch (choice) {
                case "1":
                    Avatar.print();
                    break;
                case "2":
                    Today.print();
                    break;
                case "3A":
                    RockPaperScissors gameRPC = new RockPaperScissors();
                    gameRPC.play();
                    break;
                case "3B":
                    Dice die = new Dice(6);
                    DiceGame21 game21 = new DiceGame21(die);
                    game21.play();
                case "m":
                    printMenu();
                    break;
                case "Q":
                case "q":
                    break;
                default:
                    System.err.println("Bad menu choice, use 'm' to get the menu.");
            }
        } while (!(choice.equals("q") || choice.equals("Q")));
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
                | qQ) Quit
                 -----------------""");
    }
}
