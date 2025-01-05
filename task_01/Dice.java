import java.util.Random;

class Dice {
    private int sides;
    private int lastRoll;
    private String[] diceGraphics = { "⚀", "⚁", "⚂", "⚃", "⚄", "⚅" };
    private static Random random = new Random();

    Dice(int sides) {
        if (sides < 0) sides = 1;
        this.sides = sides;
    }

    public int roll() {
        lastRoll = random.nextInt(sides) + 1;
        return lastRoll;
    }

    @Override
    public String toString() {
        if (lastRoll > diceGraphics.length) {
            return "[" + lastRoll + "]";
        } else {
            return diceGraphics[lastRoll - 1];
        }
    }

    public int getValue() {
        return lastRoll;
    }
}