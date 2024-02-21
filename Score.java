public class Score {
    int score = 0;
    public Score() {}

    public void addPoints(int score) {
        this.score += score;
    }

    public void removePoints(int score){
        this.score -= score;
    }
    
}

