package app.com.example.vansh.wdyw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BorrowerLoanData {


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("loanType")
    @Expose
    private String loanType;
    @SerializedName("loanAmt")
    @Expose
    private Integer loanAmt;
    @SerializedName("expectedInterest")
    @Expose
    private String expectedInterest;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("customer")
    @Expose
    private Customer2 customer;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getExpectedInterest() {
        return expectedInterest;
    }

    public void setExpectedInterest(String expectedInterest) {
        this.expectedInterest = expectedInterest;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Customer2 getCustomer() {
        return customer;
    }

    public void setCustomer(Customer2 customer) {
        this.customer = customer;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}