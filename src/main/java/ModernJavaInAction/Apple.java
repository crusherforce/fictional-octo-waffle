package ModernJavaInAction;

public class Apple {

    public static boolean isRed(Apple apple) {
        return Color.RED == apple.getColor();
    }

    public static boolean isHeavy(Apple apple) {
        return 150 <= apple.getWeight();
    }

    public static enum Color {
        RED,
        GREEN,
        BLUE,
        YELLOW,
        WHITE,
        BLACK
    }

    private int weight;
    private Color color;

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Apple(" + weight + "," + color.toString() + ")";
    }
}