package Tournamets;

public class Referee implements Runnable{
    private String name;
    private Scores score;

    public Referee(String name, Scores score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public void run() {
        score.Add(name);
    }
}
