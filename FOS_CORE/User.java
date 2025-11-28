package FOS_CORE;

public class User {

    protected int userID;
    protected String email;
    protected String passwordHash;

    public User() { }

    public User(int userID, String email, String passwordHash) {
        this.userID = userID;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}