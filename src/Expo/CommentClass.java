package Expo;

/**
 * Created by Cavaco on 23/05/2016 at 15:32 in nunokingler's pc.
 * File originaly created to fulfill project #ExpoFCT in pacage Expo.
 * 1996-Present no rights reserved
 **/
public class CommentClass implements Comment {
    //instance variaables
    int likes;
    int deslikes;
    User Author;


    @Override
    public User getUser() {
        return Author;
    }

    @Override
    public int getLikes() {
        return likes;
    }

    @Override
    public int getDeslikes() {
        return deslikes;
    }

    @Override
    public void incLikes() {
        likes++;
    }

    @Override
    public void incDeslikes() {
        deslikes++;
    }
}
