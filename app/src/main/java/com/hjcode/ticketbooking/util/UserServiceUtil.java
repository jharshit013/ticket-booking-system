package com.hjcode.ticketbooking.util;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt()); // hash the password using bcrypt, this is
                                                          // a one-way hashing algorithm, it is not
                                                          // possible to get the original password
                                                          // from the hashed password, this is
                                                          // important for security reasons, if the
                                                          // database is compromised, the attackers
                                                          // will not be able to get the original
                                                          // passwords of the users
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword); // check if the password matches the hashed password, this is
                                                         // used for login, when the user enters the password, we hash
                                                         // it and compare it with the hashed password in the database,
                                                         // if they match, the user is authenticated
    }
}
