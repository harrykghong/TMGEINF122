public class User {
    private final String userName;
    private Score userScore;
    public User(String userName){
        this.userName = userName;
        this.userScore = new Score();
    }

    public Score getUserScore(){
        return userScore;
    }
    public String getUserName(){
        return userName;
    }
}
