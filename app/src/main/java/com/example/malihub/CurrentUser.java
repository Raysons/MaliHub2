package com.example.rayson.malihub;

/**
 * Created by rayson 20/06/2019
 */
class CurrentUser {

    private static String UserEmail;
    private static String UserName;
    private static String UserImageURL;

    public static String getUserEmail() {
        return UserEmail;
    }

    public static void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public static String getUserName() {
        return UserName;
    }

    public static void setUserName(String userName) {
        UserName = userName;
    }

    public static String getUserImageURL() {
        return UserImageURL;
    }

    public static void setUserImageURL(String userImageURL) {
        UserImageURL = userImageURL;
    }

    public CurrentUser() {

    }
}
