package app.com.example.vansh.wdyw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("adharNo")
    @Expose
    private String adharNo;
    @SerializedName("earning")
    @Expose
    private Integer earning;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("lender")
    @Expose
    private LenderDetails lender;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LenderDetails getLender() {
        return lender;
    }

    public void setLender(LenderDetails lender) {
        this.lender = lender;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}