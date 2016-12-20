package app.com.example.vansh.wdyw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LSignupRequest {


    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("type")
    @Expose
    private Type type;

    @SerializedName("recognization")
    @Expose
    private String recognization;
    @SerializedName("accHolderName")
    @Expose
    private String accHolderName;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("website")
    @Expose
    private String website;

    public String getRecognization() {
        return recognization;
    }

    public void setRecognization(String recognization) {
        this.recognization = recognization;
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


}