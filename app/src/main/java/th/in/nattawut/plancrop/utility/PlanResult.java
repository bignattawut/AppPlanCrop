package th.in.nattawut.plancrop.utility;

import com.google.gson.annotations.SerializedName;

public class PlanResult {

    @SerializedName("crop")
    private String crop;
    @SerializedName("beginharvest")
    private String beginharvest;
    @SerializedName("harvestperiod")
    private String harvestperiod;
    @SerializedName("yield")
    private String yield;

    public String getCrop() {
        return crop;
    }

    public String getBeginharvest() {
        return beginharvest;
    }

    public String getHarvestperiod() {
        return harvestperiod;
    }

    public String getYield() {
        return yield;
    }
}
