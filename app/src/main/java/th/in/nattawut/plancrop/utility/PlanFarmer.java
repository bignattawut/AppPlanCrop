package th.in.nattawut.plancrop.utility;

import com.google.gson.annotations.SerializedName;

public class PlanFarmer {


    @SerializedName("no")
    private String no;
    @SerializedName("pdate")
    private String pdate;
    @SerializedName("cid")
    private String cid;
    @SerializedName("crop")
    private String crop;
    @SerializedName("area")
    private String area;


    public String getNo() {
        return no;
    }

    public String getPdate() {
        return pdate;
    }

    public String getCid() {
        return cid;
    }

    public String getCrop() {
        return crop;
    }

    public String getArea() {
        return area;
    }
}
