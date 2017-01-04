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