package app.com.example.vansh.wdyw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Type {
    @SerializedName("organization")
    @Expose
    private String organization;
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
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("adharNo")
    @Expose
    private String adharNo;
    @SerializedName("earning")
    @Expose
    private Integer earning;


    @SerializedName("Occupation")
    @Expose
    private String occupation;
    @SerializedName("Income")
    @Expose
    private Integer income;
    @SerializedName("email")
    @Expose
    private String email;




    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdharNo() {
        return adharNo;
    }

    public void setAdharNo(String adharNo) {
        this.adharNo = adharNo;
    }


    public Integer getEarning() {
        return earning;
    }

    public void setEarning(Integer earning) {
        this.earning = earning;
    }





    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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



}

