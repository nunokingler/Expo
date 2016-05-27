package Expo;

/**
 * Created by Cavaco on 27/05/2016 at 17:03 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public class DepartmentClass implements Department {
    //instace variables
    String name;
    String initials;
    String location;
//constructors

    public DepartmentClass(String name, String initials, String location) {
        this.name = name;
        this.initials = initials;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getInitials() {
        return initials;
    }

    public String getLocation() {
        return location;
    }
}
