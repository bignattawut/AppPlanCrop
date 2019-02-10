package th.in.nattawut.plancrop.utility;

import th.in.nattawut.plancrop.R;

public class Myconstant {


    private String urlGetUser = "http://192.168.1.111/android/php/getAll.php";
    private String urlRegister = "http://192.168.1.111/android/php/addregister.php";
    private String urlGetPlan = "http://192.168.1.111/android/php/getPlanView.php";
    private String urladdPlan = "http://192.168.1.111/android/php/addPlan.php";
    public static String getUrlCrop ="http://192.168.1.111/android/php/selectCrop.php";
    public static String getUrlCropType = "http://192.168.1.111/android/php/selectcroptype.php";
    public static String urlmid = "http://192.168.1.111/android/php/selectmid.php";


    //เกษตรกร
    private  String urlGetRegister = "http://192.168.1.111/android/php/getregister.php";
    private String[] comlumRegisterString = new String[]{"MID","UserID","PWD","ID","Name","Address","Tel","EMail"};
    //private  String urlGetRegister = "http://192.168.1.111/android/php/selectfarmer.php";
    //private String[] comlumRegisterString = new String[]{"mid","userid","pwd","id","name","address","vid","sid","tel","email","area"};


    //spinner จังหวัด อำเภอ ตำบล
    public static String getUrlProvince = "http://192.168.1.111/android/php/selectprovince.php";
    public static String getUrlAmphur = "http://192.168.1.111/android/php/selectdistrict.php";

    //พืช
    private String urlAddCrop = "http://192.168.1.111/android/php/insertcrop.php";
    //private String urlselectCrop = "http://192.168.1.111/android/php/selectcrop.php";
    //private String[] columnCropString = new String[]{"crop.cid","crop.crop","croptype.tid","croptype.croptype","crop.beginharvest","crop.harvestperiod","crop.yield"};

    private String urlselectCrop = "http://192.168.1.111/android/php/a.php";
    private String[] columnCropString = new String[]{"cid","crop","tid","beginharvest","harvestperiod","yield"};
    //ประเภทพืช
    private String urlAddCropType = "http://192.168.1.111/android/php/insertcroptype.php";
    private String urlselectcroptype = "http://192.168.1.111/android/php/selectcroptype.php";
    private String urlEditCropType = "http://192.168.1.111/android/php/editcroptype.php";
    private String urlDeleteCropType = "http://192.168.1.111/android/php/deletecroptype.php";
    private String[] columnCropTypeString = new String[]{"TID","croptype"};

    //ทดสอบ
    private String urlplantpicture = "http://192.168.1.30/android/php/addPlantPicture.php";
    private String hostnametp = "localhost";
    private String uerFtp = "root";
    private String passFtp = "";
    private int portAnInt = 21;


    private String[] columnPlanString = new String[]{"No", "MID", "PDate", "CID", "Area"};
    private int[] iconInts = new int[]{
            R.drawable.ic_action_drawerplan,
            R.drawable.ic_action_date,
            R.drawable.ic_action_drawerplan,
            R.drawable.ic_action_drawerplan,
            R.drawable.ic_action_drawerplan,
            R.drawable.ic_action_drawerplan,
            R.drawable.ic_action_userregister,
            R.drawable.ic_action_exit};
    private String[] titleStrings = new String[]{
            "วางแผนเพาะปลูก",
            "ปฏิทินการเพาะปลูก",
            "ข้อมูลการเพาะปลูก",
            "เพิ่มข้อมูลพืชเพาะปลูก",
            "เพิ่มข้อมูลประเภทพืช",
            "ข้อมูลประเภทพืช",
            "กิจกรรม",
            "Exit"};

    // getUrl
    public int[] getIconInts() {
        return iconInts;
    }

    public String[] getTitleStrings() {
        return titleStrings;
    }

    public String getUrlGetPlan() {
        return urlGetPlan;
    }

    public String[] getColumnPlanString() {
        return columnPlanString;
    }

    public String getUrlGetUser() {
        return urlGetUser;
    }

    public String getUrlRegister() {
        return urlRegister;
    }

    public String[] getComlumRegisterString() {
        return comlumRegisterString;
    }

    public String getUrlGetRegister() {
        return urlGetRegister;
    }

    public String getUrladdPlan() {
        return urladdPlan;
    }


    //เกษตรกร


    //พืช
    public String getUrlAddCrop() {
        return urlAddCrop;
    }
    public String getUrlselectCrop() {
        return urlselectCrop;
    }
    public String[] getColumnCropString() {
        return columnCropString;
    }

    //ประเภทพืช
    public String getUrlAddCropType() {
        return urlAddCropType;
    }
    public String[] getColumnCropTypeString() {
        return columnCropTypeString;
    }
    public String getUrlselectcroptype() {
        return urlselectcroptype;
    }
    public String getUrlEditCropType() {
        return urlEditCropType;
    }
    public String getUrlDeleteCropType() {
        return urlDeleteCropType;
    }

    //ทดสอบ
    public String getUrlplantpicture() {
        return urlplantpicture;
    }
    public String getHostnametp() {
        return hostnametp;
    }
    public String getUerFtp() {
        return uerFtp;
    }
    public String getPassFtp() {
        return passFtp;
    }
    public int getPortAnInt() {
        return portAnInt;
    }

}
