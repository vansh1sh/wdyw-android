package app.com.example.vansh.wdyw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanPostRequest {


    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("loanType")
    @Expose
    private String loanType;
    @SerializedName("loanAmt")
    @Expose
    private Integer loanAmt;
    @SerializedName("expectedinterest")
    @Expose
    private Integer expectedinterest;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("duration")
    @Expose
    private String duration;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
   public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Integer getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(Integer loanAmt) {
        this.loanAmt = loanAmt;
    }

    public Integer getExpectedinterest() {
        return expectedinterest;
    }

    public void setExpectedinterest(Integer expectedinterest) {
        this.expectedinterest = expectedinterest;
    }

}