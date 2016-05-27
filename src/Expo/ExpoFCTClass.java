package Expo;

import Exeptions.*;

import java.util.*;

/**
 * Created by Cavaco on 21/05/2016 at 22:54 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public class ExpoFCTClass implements ExpoFCT {
    //finals
    private final int MAX_USERS = 10;

    //instance variables
    private Map<String, User> userHashMap;
    private List<Department> Departments;
    private User log;
    private Map<String, Event> eventMap;
    private Map<String, List<Event>> tags;

    //constructor


    public ExpoFCTClass() {
        userHashMap = new TreeMap<>();
        Departments = new ArrayList<>();
        log = null;
        eventMap = new HashMap<>();
        tags = new HashMap<>();
    }
    //Interface methods

    @Override
    /**
     *  Registers a user to participate in ExpoFCT
     * @param email Email of the user (works as username)
     * @param type Type of user (0 for Admin, 1 for Staff and 2 for Visitors)
     * @param Department Used for user wich require aditional information
     * @return The new user's password
     * @throws UserLoggedIn If a user is already logged in
     * @throws EmailTaken   If that email has already been taken by other user
     * @throws DepartmentDoesNotExist   If prompted to Create a staff user withing a non existing department
     */
    public String AddUser(String email, int type, String... Department) throws UserLoggedIn, EmailTaken, DepartmentDoesNotExist {
        if (log != null)
            throw new UserLoggedIn();
        if (userHashMap.containsKey(email))
            throw new EmailTaken();
        User u = null;
        boolean suported = true;
        switch (type) {
            case 0:
                u = new AdminClass(email);
                break;
            case 1:
                if (Department.length != 1 || !Departments.contains(new DepartmentClass(Department[0], "", "")))
                    throw new DepartmentDoesNotExist();
                u = new StaffClass(email, Department[0]);
                break;
            case 2:
                u = new VisitorClass(email);
                break;
            default:
                suported = false;
        }
        if (suported) {
            userHashMap.put(email, u);
            return u.getPassword();
        }
        return null;
    }

    @Override
    /**
     * Attempts to log in with provided credentials
     * @param email User's email
     * @param password  User's password
     * @throws UserLoggedIn If a user is already logged in
     * @throws WrongPassword    If the password is incorrect
     * @throws UserDoesNotExist If the email is not associated with any user
     */
    public void LogIn(String email, String password) throws UserLoggedIn, WrongPassword, UserDoesNotExist {
        if (log != null)
            throw new UserLoggedIn();
        if (!userHashMap.containsKey(email))
            throw new UserDoesNotExist();
        User u = userHashMap.get(email);
        if (!u.getPassword().equals(password))
            throw new WrongPassword();
        this.log = u;
    }

    @Override
    /**
     * Attempts to log out the current user
     * @throws NoUserLoggedIn   If no User has logged in yet
     */
    public String Logout() throws NoUserLoggedIn {
        if (log == null)
            throw new NoUserLoggedIn();
        String ret = log.getEmail();
        log = null;
        return ret;
    }

    @Override
    /**
     * Adds a department to the expo
     * @param Department The name of the department to be added
     * @param initials The initials of the department
     * @param location The location of the department
     * @throws UserNotAllowed   If the current logged in user is not allowed to add new departments
     * @throws DepartmentAlreadyExists  If a department with that name has already been added
     */
    public void AddDepartment(String Department, String initials, String location) throws UserNotAllowed, DepartmentAlreadyExists {
        if (log == null || !log.canAddDepartments())
            throw new UserNotAllowed();
        Expo.Department d = new DepartmentClass(Department, initials, location);
        if (Departments.contains(d))
            throw new DepartmentAlreadyExists();
        this.Departments.add(d);
    }

    @Override
    public Iterator<String> VisitorIterator() throws UserNotAllowed {
        if (log == null || !log.canListUsers())
            throw new UserNotAllowed();
        Iterator<Map.Entry<String, User>> it = userHashMap.entrySet().iterator();
        List<String> link = new LinkedList<>();
        User u;
        while (it.hasNext()) {
            u = (User) it.next().getValue();
            if (u instanceof Visitor)
                link.add(u.toString());
        }
        return link.iterator();
    }

    @Override
    public Iterator<String> AdminIterator() throws UserNotAllowed {
        if (log == null || !log.canListUsers())
            throw new UserNotAllowed();
        Iterator<Map.Entry<String, User>> it = userHashMap.entrySet().iterator();
        List<String> link = new LinkedList<>();
        User u;
        while (it.hasNext()) {
            u = (User) it.next().getValue();
            if (u instanceof Admin)
                link.add(u.toString());
        }
        return link.iterator();
    }

    @Override
    public Iterator<String> StaffIterator() throws UserNotAllowed {
        if (log == null || !log.canListUsers())
            throw new UserNotAllowed();
        Iterator<Map.Entry<String, User>> it = userHashMap.entrySet().iterator();
        List<String> link = new LinkedList<>();
        User u;
        while (it.hasNext()) {
            u = (User) it.next().getValue();
            if (u instanceof Staff)
                link.add(u.toString());
        }
        return link.iterator();
    }
    @Override
    public void RegisterEvent(String name, String description, String... Tags) throws UserNotAllowed, EventNameTaken {
        if (log == null || !log.canRegisterEvent())
            throw new UserNotAllowed();
        if (eventMap.containsKey(name))
            throw new EventNameTaken();
        Staff stf = (Staff) log;
        Event e;
        if (Tags.length == 0) {
            e = new EventClass(description, name, log);
        } else {
            e = new Activity(description, name, log, stf.getDepartment(), Tags);
            if (Tags.length == 0)
                tags.get("").add(e);
            for (String s : Tags) {
                LinkedList<Event> a = (LinkedList<Event>) tags.get(s);
                if (a != null) {
                    tags.remove(s);
                } else
                    a = new LinkedList<>();
                a.add(e);
                tags.put(s, a);
            }
        }
        eventMap.put(name, e);
        int i = 0;
        i++;
    }

    @Override
    public Iterator<String> EventIterator(String... Tags) {
        Iterator<Map.Entry<String, Event>> it = eventMap.entrySet().iterator();
        LinkedList<Event> link = new LinkedList<>();
        while (it.hasNext()) {
            Map.Entry<String, Event> c = it.next();
            if (Tags.length == 0)
                link.add(c.getValue());
            else if (c.getValue().hasTag(Tags[0]))
                link.add(c.getValue());
        }
        boolean changed = false;
        while (!changed) {
            changed = true;
            for (int i = 0; i < link.size() - 1; i++) {
                if (link.get(i).getEnroledNumber() > link.get(i + 1).getEnroledNumber()) {
                    Collections.swap(link, i, i + 1);
                    changed = false;
                }
            }
        }
        LinkedList<String> str = new LinkedList<>();
        Iterator iter = link.iterator();
        while (iter.hasNext())
            str.add(iter.next().toString());
        return str.iterator();
    }

    @Override
    public void CommentOnEvent(String EventName, String Comment) throws EventDoesntExist, UserNotAllowed {
        if (log == null || !log.canComment())
            throw new UserNotAllowed();
        if (eventMap.containsKey(EventName))
            throw new EventDoesntExist();

        Event e = eventMap.get(EventName);
        e.addComment(Comment, log);
        eventMap.put(EventName, e);
    }

    @Override
    public void EnrolOnEvent(String EventName) throws EventDoesntExist, UserNotAllowed {
        if (!eventMap.containsKey(EventName))
            throw new EventDoesntExist();
        if (log == null || !log.canEnrol())
            throw new UserNotAllowed();

        Event e = eventMap.get(EventName);
        e.setEnroledUsers(log);
        eventMap.put(EventName, e);
    }

    @Override
    public void LikeEvent(String EventName) throws EventDoesntExist {
        if (!eventMap.containsKey(EventName))
            throw new EventDoesntExist();
        Event e = eventMap.get(EventName);
        e.incLikes();
        eventMap.put(EventName, e);
    }

    @Override
    public void DeslikeEvent(String EventName) throws EventDoesntExist {
        if (!eventMap.containsKey(EventName))
            throw new EventDoesntExist();
        Event e = eventMap.get(EventName);
        e.incDeslikes();
        eventMap.put(EventName, e);
    }
}
