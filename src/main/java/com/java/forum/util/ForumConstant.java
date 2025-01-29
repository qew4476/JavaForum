package com.java.forum.util;

public interface ForumConstant {

    int ACTIVATION_SUCCESS = 0;

    int ACTIVATION_REPEAT = 1;

    int ACTIVATION_FAILURE = 2;

    //default expired time for login tickets
    int DEFAULT_EXPIRED_SECONDS = 3600*12;

    //Remember me:longer expired time for login tickets
    int REMEMBER_EXPIRED_SECONDS = 3600*24*100;

}
