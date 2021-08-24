package ModernJavaInAction;

public class Player {
    public Player(String name, Country country, int startOfPlay) {
        this.name = name;
        this.country = country;
        this.startOfPlay = startOfPlay;
    }

    private String name;
    public String name() {
        return name;
    }
    private Country country;
    public Country country() {
        return country;
    }
    private int startOfPlay;
    public int startOfPlay() {
        return startOfPlay;
    }
    @Override
    public String toString() {
        return 
            "(" 
                + name 
                + "," 
                + country 
                + "," 
                + startOfPlay 
                + 
            ")";
    }
}