package Expo;

/**
 * Created by Cavaco on 23/05/2016 at 15:31 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public interface Comment {
    User getUser();

    int getLikes();

    int getDeslikes();


    void incLikes();

    void incDeslikes();

}
