package com.reactnativejitsimeet;

import android.os.Bundle;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class represents user information to be passed to {@link RNJitsiMeetConferenceOptions} for
 * identifying a user.
 */
public class RNJitsiMeetUserInfo {
    /**
     * User's display name.
     */
    private String displayName;

    /**
     * User's email address.
     */
    private String email;

    /**
     * User's service name.
     */
    private String serviceName;

    /**
     * User's avatar URL.
     */
    private URL avatar;

    public RNJitsiMeetUserInfo() {}

    public RNJitsiMeetUserInfo(Bundle b) {
        super();

        if (b.containsKey("displayName")) {
            displayName = b.getString("displayName");
        }

        if (b.containsKey("email")) {
            email = b.getString("email");
        }

        if (b.containsKey("serviceName")) {
            serviceName = b.getString("serviceName");
        }

        if (b.containsKey("avatarURL")) {
            String avatarURL = b.getString("avatarURL");
            try {
                avatar = new URL(avatarURL);
            } catch (MalformedURLException e) {
            }
        }
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public URL getAvatar() {
        return avatar;
    }

    public void setAvatar(URL avatar) {
        this.avatar = avatar;
    }

    Bundle asBundle() {
        Bundle b = new Bundle();

        if (displayName != null) {
            b.putString("displayName", displayName);
        }

        if (email != null) {
            b.putString("email", email);
        }

        if (avatar != null) {
            b.putString("avatarURL", avatar.toString());
        }

        if (serviceName != null) {
            b.putString("serviceName", serviceName);
        }

        return b;
    }
}
