package app.com.example.vansh.wdyw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LloanIdRequest {

    @SerializedName("loan")
    @Expose
    private String loan;
    @SerializedName("customer")
    @Expose
    private String customer;
    @SerializedName("lender")
    @Expose
    private String lender;

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }


    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

}

