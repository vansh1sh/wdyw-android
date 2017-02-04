package app.com.example.vansh.wdyw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BData {


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private Long phone;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("pro_pic")
    @Expose
    private String proPic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
    }

}