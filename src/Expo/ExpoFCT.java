package Expo;

import Exeptions.*;

import java.util.Iterator;

/**
 * Created by Cavaco on 21/05/2016 at 22:54 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public interface ExpoFCT {
    /**
     * Registers a user to participate in ExpoFCT
     *
     * @param email      Email of the user (works as username)
     * @param type       Type of user (0 for Admin, 1 for Staff and 2 for Visitors)
     * @param Department Used for user wich require aditional information
     * @return The new user's password
     * @throws UserLoggedIn           If a user is already logged in
     * @throws EmailTaken             If that email has already been taken by other user
     * @throws DepartmentDoesNotExist If prompted to Create a staff user withing a non existing department
     */
    public String AddUser(String email, int type, String... Department) throws UserLoggedIn, EmailTaken, DepartmentDoesNotExist;

    /**
     * Attempts to log in with provided credentials
     *
     * @param email    User's email
     * @param password User's password
     * @throws UserLoggedIn     If a user is already logged in
     * @throws WrongPassword    If the password is incorrect
     * @throws UserDoesNotExist If the email is not associated with any user
     */
    public void LogIn(String email, String password) throws AnotherUserLoggedIn, UserLoggedIn, WrongPassword, UserDoesNotExist;

    /**
     * Attempts to log out the current user
     *
     * @throws NoUserLoggedIn If no User has logged in yet
     */
    public String Logout() throws NoUserLoggedIn;

    /**
     * Adds a department to the expo
     *
     * @param Department The name of the department to be added
     * @param initials The initials of the department
     * @param location The location of the department
     * @throws UserNotAllowed          If the current logged in user is not allowed to add new departments
     * @throws DepartmentAlreadyExists If a department with that name has already been added
     */
    public void AddDepartment(String Department, String initials, String location) throws UserNotAllowed, DepartmentAlreadyExists;

    /**
     * Returns an Iterator to the visitor acounts
     *
     * @return Iterator to visitors
     * @throws UserNotAllowed If logged user is not Admin
     */
    public Iterator<String> VisitorIterator() throws UserNotAllowed;

    /**
     * Returns an Iterator to the Admin acounts
     *
     * @return Iterator to Admins
     * @throws UserNotAllowed If logged user is not Admin
     */
    public Iterator<String> AdminIterator() throws UserNotAllowed;

    /**
     * Returns an Iterator to the Staff acounts
     *
     * @return Iterator to Staff
     * @throws UserNotAllowed If logged user is not Admin
     */
    public Iterator<String> StaffIterator() throws UserNotAllowed;

    public void RegisterEvent(String name, String description, String... s) throws UserNotAllowed, EventNameTaken;
}
