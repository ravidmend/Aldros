package com.example.newfuckingaldros;
/**
 *  GlobalUser
 *  class that help approaching the users data from any place in the app
 */
public class GlobalUser {
    private static User user;


    public static void setUser(User user) {
        GlobalUser.user = user;
    }
    public static User getUser() {
        return(GlobalUser.user);
    }
}
