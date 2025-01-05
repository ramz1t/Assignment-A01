import java.util.Arrays;
import java.util.Random;

public class RockPaperScissors extends Game {
    private String[] commands = {"r", "p", "s"};
    private Random random = new Random();
    private String playerMove, botMove;

    public void play() {
        System.out.println("Welcome to a game of rock ✊, scissor ✂, paper ✋!");
        System.out.println("You will play against Pixel the Bot and we keep score.\n");

        displayScore();
        System.out.print("Select ");
        for (String command : commands) {
            System.out.print(getChoiceTitle(command) + " [" + command + "]");
            if (!command.equals(commands[commands.length - 1])) {
                System.out.print(", ");
            }
        }
        System.out.println(" or quit [q]:");

        while (true) {
            playerMove = scanner.nextLine();

            if (playerMove.equals("q")) {
                break;
            }

            if (!isValidMove(playerMove)) {
                System.err.println("Illegal move");
                continue;
            }

            botMove = getBotMove();

            System.out.print(String.format("You did %s and Pixel the Bot did %s. ", getChoiceTitle(playerMove), getChoiceTitle(botMove)));

            evaluateScore();

            displayScore();
        }
    }

    private String getChoiceTitle(String cmd) {
        return switch(cmd) {
            case "r" -> "rock ✊";
            case "s" -> "scissor ✂";
            case "p" -> "paper ✋";
            default -> "Unknown";
        };
    }

    private void evaluateScore() {
        if (playerMove.equals(botMove)) {
            System.out.println("You are equal! No points!");
        } else {
            boolean playerWins = (playerMove.equals("r") && botMove.equals("s")) ||
                               (playerMove.equals("s") && botMove.equals("p")) ||
                               (playerMove.equals("p") && botMove.equals("r"));
            
            String message = switch(playerMove + botMove) {
                case "rs", "sr" -> "Rock beats scissors!";
                case "sp", "ps" -> "Scissors cut paper!"; 
                case "pr", "rp" -> "Paper covers rock!";
                default -> "Invalid move combination";
            };

            if (playerWins) {
                System.out.println(message + " You get 1 point");
                scorePlayer++;
            } else {
                System.out.println(message + " Pixel gets 1 point");
                scoreBot++;
            }
        }
    }

    private boolean isValidMove(String move) {
        return Arrays.asList(commands).contains(move);
    }

    private String getBotMove() {
        return commands[random.nextInt(commands.length)];
    }
}
