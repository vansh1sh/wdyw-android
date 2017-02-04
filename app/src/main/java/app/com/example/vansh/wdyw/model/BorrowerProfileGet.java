package app.com.example.vansh.wdyw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BorrowerProfileGet {


    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private BData data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BData getData() {
        return data;
    }

    public void setData(BData data) {
        this.data = data;
    }

}