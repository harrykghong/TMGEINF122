package TMGE;

/*
Score is the points that a User has accumulated
Score has an int Score
Score can add or remove points
*/
public class Score {
    private int score = 0;

    public Score() {
    }

    /**
     * Function to add points
     * @param score
     */
    public void addPoints(int score) {
        this.score += score;
    }

    /**
     * Function to remove points
     * @param score
     */
    public void removePoints(int score) {
        this.score -= score;
    }

    /**
     * Function to get score int
     * @return int (score)
     */
    public int getScore() {
        return this.score;
    }
}
