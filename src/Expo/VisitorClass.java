package Expo;

import java.util.LinkedList;

/**
 * Created by Cavaco on 22/05/2016 at 01:57 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public class VisitorClass extends UserClass implements Visitor {
    //class variables
    private static int VISITOR_NUMBER = 1;
    private static final String PASSWORD_PREFIX = "visitor";
    private static final String USER_NAME = "VISITOR";

    //instance variables
    private int commentNumber;

    //constructor
    public VisitorClass(String email) {
        super(email, PASSWORD_PREFIX + VISITOR_NUMBER++);
        commentNumber = 0;
    }

    //instance variables
    LinkedList<Event> registedEvents;

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
        return false;
    }

    @Override
    public String toString() {
        return super.getEmail() + "; " + USER_NAME;
    }

    @Override
    public boolean canComment() {
        return true;
    }

    @Override
    public boolean canEnrol() {
        return true;
    }

    @Override
    public int getCommentNumber() {
        return commentNumber;
    }

    @Override
    public void incComment() {
        commentNumber++;
    }
}
