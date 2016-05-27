package Expo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cavaco on 23/05/2016 at 15:21 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public class Activity extends EventClass {
    LinkedList<String> Tags;
    String department;

    public Activity(String description, String name, User responsable, String Department, String... Tags) {
        super(description, name, responsable);
        this.Tags = new LinkedList<>();
        department = Department;
        for (String s : Tags)
            this.Tags.add(s);
    }

    @Override
    public String toString() {
        return super.getName() + "; " + super.getResponsableUser().getEmail() + "; " + department + super.getCommentNumber() + super.getEnroledNumber() + super.getLikeNumber() + getDeslikeNumber();
    }
}
