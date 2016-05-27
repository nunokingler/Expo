package Expo;

import java.util.Iterator;

/**
 * Created by Cavaco on 23/05/2016 at 15:21 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public interface Event {
    String getName();

    String getDescription();

    User getResponsableUser();

    Iterator<User> getEnroledUsersIterator();

    public int getEnroledNumber();

    Iterator<Comment> getComentIterator();

    public int getCommentNumber();

    int getLikeNumber();

    int getDeslikeNumber();

    public void addComment(String comment, User author);

    public void likeComment(String author);

    public void deslikeComment(String author);

    public void setEnroledUsers(User u);

    public void setDescription(String description);

    public void setName(String name);

    void incLikes();

    void incDeslikes();

    boolean hasTag(String tag);

}
