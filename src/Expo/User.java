package Expo;

/**
 * Created by Cavaco on 22/05/2016 at 01:57 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public interface User {
    //**set**
    public void setPassword(String password);

    //**gets**
    public String getEmail();

    public String getPassword();

    //usefull methods
    @Override
    public boolean equals(Object obj);

    public boolean canAddDepartments();

    boolean canListUsers();

    boolean canRegisterEvent();

    boolean canComment();

    public boolean canEnrol();

}
