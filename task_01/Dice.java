import java.util.Random;

class Dice {
    private int sides;
    private int lastRoll;
    private String[] diceGraphics = { "⚀", "⚁", "⚂", "⚃", "⚄", "⚅" };

    Dice(int sides) {
        this.sides = sides;
    }

    public int roll() {
        Random random = new Random();
        lastRoll = random.nextInt(sides) + 1;
        return lastRoll;
    }

    @Override
    public String toString() {
        return diceGraphics[lastRoll - 1];
    }

    public int getValue() {
        return lastRoll;
    }
}