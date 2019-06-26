package th.in.nattawut.plancrop.utility;

import com.google.gson.annotations.SerializedName;

public class PlantFarmer {

    @SerializedName("no")
    private String no;
    @SerializedName("pdate")
    private String pdate;
    @SerializedName("cid")
    private String cid;
    @SerializedName("yield")
    private String yield;
    @SerializedName("crop")
    private String crop;
    @SerializedName("area")
    private String area;
    @SerializedName("sno")
    private String sno;


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

    public String getCrop() {
        return crop;
    }

    public String getArea() {
        return area;
    }

    public String getSno() {
        return sno;
    }
}
