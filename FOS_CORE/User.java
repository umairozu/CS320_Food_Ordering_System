package FOS_CORE;

public abstract class User {

    protected String userID;
    protected String email;
    protected String passwordHash;

    public User() { }

    public User(String userID, String email, String passwordHash) {
        this.userID = userID;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
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