package Expo;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Cavaco on 23/05/2016 at 15:21 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public class EventClass implements Event {
    //instance variables
    private String name;
    private String description;
    private User responsable;
    private List<User> enroledUsers;
    private List<Comment> coments;
    private int likeNumber;
    private int deslikeNumber;

    //constructor

    public EventClass(String description, String name, User responsable) {
        this.description = description;
        this.name = name;
        this.responsable = responsable;
    }
    //**get**

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getResponsable() {
        return responsable;
    }

    public Iterator<Comment> getComents() {
        return coments.iterator();
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public int getDeslikeNumber() {
        return deslikeNumber;
    }

    public Iterator<User> getEnroledUsers() {
        return enroledUsers.iterator();
    }
    //**set**

    public void setComents(Comment coment) {
        this.coments.add(coment);
    }

    public void setEnroledUsers(User u) {
        this.enroledUsers.add(u);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incLikes() {
        likeNumber++;
    }

    public void incDeslikes() {
        deslikeNumber++;
    }
}
