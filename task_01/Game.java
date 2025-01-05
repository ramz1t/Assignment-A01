import java.util.Scanner;

abstract class Game {
    protected int scorePlayer, scoreBot;
    public abstract void play();
    protected Scanner scanner = new Scanner(System.in);

    protected void displayScore() {
        System.out.println(String.format("Score: You %d - %d Pixel the Bot", scorePlayer, scoreBot));
    }
}
