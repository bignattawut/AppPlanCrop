package th.in.nattawut.plancrop.utility;

import com.google.gson.annotations.SerializedName;

public class Plant {

    @SerializedName("no")
    private String no;
    @SerializedName("pdate")
    private String pdate;
    @SerializedName("cid")
    private String cid;
    @SerializedName("yield")
    private String yield;
    @SerializedName("mid")
    private String mid;
    @SerializedName("name")
    private String name;
    @SerializedName("sno")
    private String sno;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lon")
    private String lon;

    @SerializedName("area")
    private String area;
    @SerializedName("crop")
    private String crop;


    public String getNo() {
        return no;
    }

    public String getPdate() {
        return pdate;
    }

    public String getCid() {
        return cid;
    }

    public String getYield() {
        return yield;
    }

    public String getMid() {
        return mid;
    }

    public String getName() {
        return name;
    }

    public String getSno() {
        return sno;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getArea() {
        return area;
    }

    public String getCrop() {
        return crop;
    }
}
