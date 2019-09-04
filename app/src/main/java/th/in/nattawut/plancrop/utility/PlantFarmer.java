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
    private int yield;
    @SerializedName("crop")
    private String crop;
    @SerializedName("area")
    private float area;
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

    public int getYield() {
        return yield;
    }

    public String getCrop() {
        return crop;
    }

    public String getArea() {
        return (int)Math.floor(area) + "-" + (int)Math.floor((area*400%400)/100) + "-" + (int)(area*400)%100;
    }

    public float getArea1(){
        return area;
    }

    public String getSno() {
        return sno;
    }
}
