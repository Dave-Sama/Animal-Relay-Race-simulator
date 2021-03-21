package Tournamets;

public class TournamentThread implements Runnable{
    private Scores score;
    private Boolean startSignal;
    private int groups;

    @Override
    public void run() {
    notifyAll();
    startSignal = true;
    }
}
