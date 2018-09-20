package com.mobitechs.ashAppointment.sessionManager;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.mobitechs.ashAppointment.Login;
import com.mobitechs.ashAppointment.MainActivity;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;      // Shared pref mode
    SessionManager sessionManager;

    // Sharedpref file name
    private static final String PREF_NAME = "Preference";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Email address (make variable public to access from outside)
    public static final String KEY_USERID = "userId";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MOBILENO = "mobileNo";
    public static final String KEY_DEPTTYPE = "type";

    String userType;

    public SessionManager(Context c) {
        this.context = c;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String saveUserId, String userFullName, String saveEmail, String saaveMobileNo, String deptType) {
        editor.putBoolean(IS_LOGIN, true);
        editor.commit();

        editor.putString(KEY_USERID, saveUserId);
        editor.putString(KEY_NAME, userFullName);
        editor.putString(KEY_EMAIL, saveEmail);
        editor.putString(KEY_MOBILENO, saaveMobileNo);
        editor.putString(KEY_DEPTTYPE, deptType);

        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_USERID, pref.getString(KEY_USERID, null));
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_MOBILENO, pref.getString(KEY_MOBILENO, null));
        user.put(KEY_DEPTTYPE, pref.getString(KEY_DEPTTYPE, null));

        // return user
        return user;
    }



    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, Login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);
        } else {


            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

        }
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(context, Login.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}