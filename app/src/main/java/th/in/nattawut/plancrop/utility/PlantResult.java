package th.in.nattawut.plancrop.utility;

import com.google.gson.annotations.SerializedName;

public class PlantResult {

    @SerializedName("crop")
    private String crop;
    @SerializedName("area")
    private String area;
    @SerializedName("yield")
    private String yield;


    public String getCrop() {
        return crop;
    }

    public String getArea() {
        return area;
    }

    public String getYield() {
        return yield;
    }
}
