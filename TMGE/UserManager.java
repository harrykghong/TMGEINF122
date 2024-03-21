package TMGE;
// UserManager Singleton class

/*
    Usermanager holds all users, essentially a User Factory
*/

// Imports
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static Map<String, User> userPool;
    private static UserManager userManager;

    private UserManager() {
        userPool = new HashMap<>();
    }

    /**
     * Function to add a new user to the manager.
     * @param userName
     * @return boolean (true if no user exists)
     */
    public boolean addUser(String userName) {
        if (!checkIfUserExists(userName)) {
            User newRegularUser = new User(userName);
            userPool.put(userName, newRegularUser);
            return true;
        }
        return false;
    }

    /**
     * Function to remove user's on given username
     * @param userName
     * @return boolean (if successful)
     */
    public boolean removeUser(String userName) {
        if (!checkIfUserExists(userName)) {
            return false;
        }
        userPool.remove(userName);
        return true;
    }

    /**
     * Function to get a user based on username.
     * @param userName
     * @return User
     */
    public User getUser(String userName) {
        return userPool.get(userName);
    }

    /**
     * Function to check if a user under the given username exists.
     * @param userName
     * @return boolean (if exists)
     */
    public boolean checkIfUserExists(String userName) {
        return userPool.containsKey(userName);
    }

    /**
     * Function to get the instance of UserManager.
     * @return Current instance of UserManager
     */
    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }
}
