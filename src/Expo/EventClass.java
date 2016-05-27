package Expo;

import Exeptions.CommentDoesNotExist;
import com.sun.xml.internal.bind.v2.TODO;

import java.util.*;

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
    private Map<String, User> enroledUsers;
    private Map<String, Comment> coments;
    private int likeNumber;
    private int deslikeNumber;

    //constructor

    public EventClass(String description, String name, User responsable) {
        this.description = description;
        this.name = name;
        this.responsable = responsable;
        enroledUsers = new HashMap<>();
        coments = new HashMap<>();
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
        Iterator<Map.Entry<String, Comment>> it = coments.entrySet().iterator();
        LinkedList<Comment> link = new LinkedList<>();
        while (it.hasNext())
            link.add(it.next().getValue());
        return link.iterator();
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
        coments.put(author.getEmail(), new CommentClass(author, comment));
    }

    @Override
    public void likeComment(String author) throws CommentDoesNotExist {
        if (!coments.containsKey(author))
            throw new CommentDoesNotExist();
        Comment c = coments.get(author);
        c.incLikes();
        coments.put(author, c);
    }

    @Override
    public void deslikeComment(String author) throws CommentDoesNotExist {
        if (!coments.containsKey(author))
            throw new CommentDoesNotExist();
        Comment c = coments.get(author);
        c.incDeslikes();
        coments.put(author, c);
    }


    public Iterator<User> getEnroledUsersIterator() {
        Iterator<Map.Entry<String, User>> it = enroledUsers.entrySet().iterator();
        LinkedList<User> link = new LinkedList<>();
        while (it.hasNext()) {
            link.add(it.next().getValue());
        }
        return link.iterator();
    }

    @Override
    public int getEnroledNumber() {
        return enroledUsers.size();
    }
    //**set**

    public void setComents(Comment coment, User u) {
        this.coments.put(u.getEmail(), coment);
    }

    public void setEnroledUsers(User u) {
        this.enroledUsers.put(u.getEmail(), u);
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
