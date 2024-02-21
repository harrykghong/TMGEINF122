import java.util.HashMap;
import java.util.Map;

// singleton pattern
public class UserManager {
    private static Map<String, User> userPool;

    private static UserManager userManager;
    private UserManager(){
        userPool = new HashMap<>();
    }
    public boolean addUser(String userName){
        if(!checkIfUserExists(userName)){
            // initialize user with a name and 0 as initial score
            User newRegularUser = new User(userName);
            userPool.put(userName, newRegularUser);
            return true;
        }
        return false;
    }
    public boolean removeUser(String userName){
        if(!checkIfUserExists(userName)){
            return false;
        }
        userPool.remove(userName);
        return true;
    }
    public User getUser(String userName){
        return userPool.get(userName);
    }
    
    public boolean checkIfUserExists(String userName){
        return userPool.containsKey(userName);
    }

    public static UserManager getInstance(){
        if(userManager == null){
            userManager = new UserManager();
        }
        return userManager;
    }
}


