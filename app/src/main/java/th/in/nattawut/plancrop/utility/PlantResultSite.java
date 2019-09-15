package th.in.nattawut.plancrop.utility;

import com.google.gson.annotations.SerializedName;

public class PlantResultSite {

    @SerializedName("name")
    private String name;

    @SerializedName("tel")
    private String tel;

    @SerializedName("crop")
    private String crop;

    @SerializedName("yield")
    private int yield;

    @SerializedName("thai")
    private String thai;


    @SerializedName("lon")
    private String lon;

    @SerializedName("lat")
    private String lat;


    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getCrop() {
        return crop;
    }

    public int getYield() {
        return yield;
    }

    public String getThai() {
        return thai;
    }

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }
}
