
import java.util.Arrays;
import java.util.Random;

public class RockPaperScissors extends Game {
    String move;

    String[] commands = {"r", "p", "s"};
    Random random = new Random();
    String botMove;

    public void play() {
        System.out.println("Welcome to a game of rock ✊, scissor ✂, paper ✋!");
        System.out.println("You will play against Pixel the Bot and we keep score.\n");

        displayScore();
        System.out.println("Select rock ✊ [r], scissor ✂ [s], paper ✋ [p] or quit [q]:");

        while (true) {
            move = scanner.nextLine();

            if (move.equals("q")) {
                break;
            }

            if (!Arrays.asList(commands).contains(move)) {
                System.err.println("Illegal move");
                continue;
            }

            botMove = commands[random.nextInt(commands.length)];

            System.out.print(String.format("You did %s and Pixel the Bot did %s. ", getChoiceTitle(move), getChoiceTitle(botMove)));

            if (move.equals(botMove)) {
                System.out.println("You are equal! No points!");
            } else if (move.equals("r") && botMove.equals("s")) {
                System.out.println("Rock beats scissors! You get 1 point");
                scoreYou++;
            } else if (move.equals("s") && botMove.equals("p")) {
                System.out.println("Scissors cut paper! You get 1 point");
                scoreYou++;
            } else if (move.equals("p") && botMove.equals("r")) {
                System.out.println("Paper covers rock! You get 1 point");
                scoreYou++;
            } else if (botMove.equals("r") && move.equals("s")) {
                System.out.println("Rock beats scissors! Pixel gets 1 point");
                scoreBot++;
            } else if (botMove.equals("s") && move.equals("p")) {
                System.out.println("Scissors cut paper!Pixel gets 1 point");
                scoreBot++;
            } else if (botMove.equals("p") && move.equals("r")) {
                System.out.println("Paper covers rock! Pixel gets 1 point");
                scoreBot++;
            } else {
                System.out.println("Why are you here?!");
            }

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
}
