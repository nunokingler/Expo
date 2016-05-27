package Expo;

/**
 * Created by Cavaco on 22/05/2016 at 01:57 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public class AdminClass extends UserClass implements Admin {
    //class variables
    private static int ADMIN_NUMBER = 1;
    private static final String PASSWORD_PREFIX = "admin";
    private static final String USER_NAME = "ADMIN";

    //constructor
    public AdminClass(String email) {
        super(email, PASSWORD_PREFIX + ADMIN_NUMBER++);
    }

    @Override
    public boolean canAddDepartments() {
        return true;
    }

    @Override
    public boolean canListUsers() {
        return true;
    }

    @Override
    public String toString() {
        return super.getEmail() + "; " + USER_NAME;
    }
}
