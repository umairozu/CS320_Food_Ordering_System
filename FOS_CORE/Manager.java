package FOS_CORE;

public class Manager extends User {

    public Manager() { }

    public Manager(String userID, String email, String passwordHash) {
        super(userID, email, passwordHash);
    }
}