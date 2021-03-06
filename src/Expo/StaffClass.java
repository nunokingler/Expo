package Expo;

/**
 * Created by Cavaco on 22/05/2016 at 01:57 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public class StaffClass extends UserClass implements Staff {
    //class variables
    private static int STAFF_NUMBER = 1;
    private static final String PASSWORD_PREFIX = "staff";
    private static final String USER_NAME = "STAFF";

    //intance variables
    private String Department;

    public StaffClass(String email, String Department) {
        super(email, PASSWORD_PREFIX + STAFF_NUMBER++);
        this.Department = Department;
    }

    @Override
    public String toString() {
        return super.getEmail() + "; " + USER_NAME;
    }

    @Override
    public boolean canAddDepartments() {
        return false;
    }

    @Override
    public boolean canListUsers() {
        return false;
    }

    @Override
    public boolean canRegisterEvent() {
        return true;
    }

    @Override
    public String getDepartment() {
        return Department;
    }
}
