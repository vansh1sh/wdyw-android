package app.com.example.vansh.wdyw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfilePic {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("file_type")
    @Expose
    private String fileType;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}

