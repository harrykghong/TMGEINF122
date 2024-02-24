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
    public User(String userName){
        this.userName = userName;
        this.userScore = new Score();
    }

    public int getUserScore(){
        return userScore.getScore();
    }
    public String getUserName(){
        return userName;
    }
}
