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

    void setResponsibleUser();

    Iterator<User> getEnroledUsersIterator();

    Iterator<Comment> getComentIterator();

    int getLikeNumber();

    int getDeslikeNumber();

    void incLikes();

    void inceDeslikes();
}
