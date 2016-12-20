package app.com.example.vansh.wdyw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Occupation {

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("Income")
    @Expose
    private Integer income;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("adharNo")
    @Expose
    private String adharNo;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getAdharNo() {
        return adharNo;
    }

    public void setAdharNo(String adharNo) {
        this.adharNo = adharNo;
    }

}