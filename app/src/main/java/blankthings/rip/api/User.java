package blankthings.rip.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iosifvilcea on 6/23/16.
 */
public class User {

    @SerializedName("id")
    int id;

    @SerializedName("businessName")
    String username;

    @SerializedName("email")
    String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
