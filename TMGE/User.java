package TMGE;
// User class

/*
    User is the player.
    Attributes are the userName, userScore
    Methods are getUserScore, getUserName
*/

// Imports

public class User {
    private final String userName;
    private Score userScore;

    public User(String userName) {
        this.userName = userName;
        this.userScore = new Score();
    }

    /**
     * Function to get a user's score.
     * @return int (score)
     */
    public int getUserScore() {
        return userScore.getScore();
    }

    /**
     * Function to get a user's username.
     * @return String (username)
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Function to add to a user's score.
     * @param score
     */
    public void addScore(int score) {
        this.userScore.addPoints(score);
    }

    public void clearScore() {
        this.userScore = new Score();
    }
}
