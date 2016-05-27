package Expo;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.Iterator;
import java.util.LinkedList;
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
        enroledUsers = new LinkedList<>();
        coments = new LinkedList<>();
    }
    //**get**

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getResponsableUser() {
        return responsable;
    }

    public Iterator<Comment> getComentIterator() {
        return coments.iterator();
    }

    @Override
    public int getCommentNumber() {
        return coments.size();
    }


    public int getLikeNumber() {
        return likeNumber;
    }

    public int getDeslikeNumber() {
        return deslikeNumber;
    }

    @Override
    public void addComment(String comment, User author) {
// TODO: 27/05/2016
    }

    @Override
    public void likeComment(String author) {
// TODO: 27/05/2016
    }

    @Override
    public void deslikeComment(String author) {
        // TODO: 27/05/2016
    }


    public Iterator<User> getEnroledUsersIterator() {
        return enroledUsers.iterator();
    }

    @Override
    public int getEnroledNumber() {
        return enroledUsers.size();
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

    @Override
    public boolean hasTag(String tag) {
        return false;
    }

    @Override
    public String toString() {
        return name + "; " + responsable.getEmail() + "; " + coments.size() + "; " + enroledUsers.size() + "; " + likeNumber + "; " + deslikeNumber + ";";
    }
}
