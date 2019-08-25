package th.in.nattawut.plancrop.utility;

import com.google.gson.annotations.SerializedName;

public class PlantResult {

    @SerializedName("crop")
    private String crop;

    @SerializedName("yield")
    private String yield;


    public String getCrop() {
        return crop;
    }

    public String getYield() {
        return yield;
    }
}
