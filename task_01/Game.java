import java.util.Scanner;

abstract class Game {
    int scoreYou, scoreBot;
    public abstract void play();
    Scanner scanner = new Scanner(System.in);

    protected void displayScore() {
        System.out.println(String.format("Score: You %d - %d Pixel the Bot", scoreYou, scoreBot));
    }
}
