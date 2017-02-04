package app.com.example.vansh.wdyw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LlentDatum {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("lender")
    @Expose
    private Lender lender;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("offerBy")
    @Expose
    private String offerBy;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("accepted")
    @Expose
    private Boolean accepted;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("loan")
    @Expose
    private Loan loan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Lender getLender() {
        return lender;
    }

    public void setLender(Lender lender) {
        this.lender = lender;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOfferBy() {
        return offerBy;
    }

    public void setOfferBy(String offerBy) {
        this.offerBy = offerBy;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

}
