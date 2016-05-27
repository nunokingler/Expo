package Expo;

/**
 * Created by Cavaco on 22/05/2016 at 01:57 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public abstract class UserClass implements User {
    //instance variables
    private String email;
    private String password;

    //constructor
    public UserClass(String email) {
        this.email = email;
    }

    public UserClass(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //**set**
    public void setPassword(String password) {
        this.password = password;
    }

    //**gets**
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //useful methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        else {
            UserClass u;
            if (obj instanceof UserClass)
                u = (UserClass) obj;
            else return false;
            return this.equals(u);
        }
    }

    private boolean equals(UserClass u) {
        return u.getEmail().equals(this.email);
    }

    public boolean canComment() {
        return false;
    }

    public boolean canEnrol() {
        return false;
    }
}
