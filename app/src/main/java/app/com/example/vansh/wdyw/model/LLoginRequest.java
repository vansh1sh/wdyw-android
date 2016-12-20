package app.com.example.vansh.wdyw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LLoginRequest {


    @SerializedName("phone")
    @Expose
    private Integer phone;
    @SerializedName("password")
    @Expose
    private String password;

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}