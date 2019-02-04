package th.in.nattawut.plancrop.utility;

import th.in.nattawut.plancrop.R;

public class Myconstant {


    private String urlGetUser = "http://192.168.1.110/android/php/getAll.php";
    private String urlRegister = "http://192.168.1.110/android/php/addregister.php";
    private String urlGetPlan = "http://192.168.1.110/android/php/getPlanView.php";
    private String urlAddCrpo = "http://192.168.1.110/android/php/addcrop.php";
    private String urladdPlan = "http://192.168.1.110/android/php/addPlan.php";
    public static String getUrlCrop ="http://192.168.137.1/android/php/selectCrop.php";
    public static String getUrlCropType = "http://192.168.137.1/android/php/selectcroptype.php";
    public static String getUrlProvince = "http://192.168.137.1/android/php/selectprovince.php";
    public static String urlmid = "http://192.168.137.1/android/php/selectmid.php";
    private  String urlGetRegister = "http://192.168.1.110/android/php/getregister.php";

    //ประเภทพืช
    private String urlAddCropType = "http://192.168.1.110/android/php/insertcroptype.php";
    private String urlselectcroptype = "http://192.168.1.110/android/php/selectcroptype.php";
    private String urlEditCropType = "http://192.168.1.110/android/php/editcroptype.php";
    private String urlDeleteCropType = "http://192.168.1.110/android/php/deletecroptype.php";

    //ทดสอบ
    private String urlplantpicture = "http://192.168.1.110/android/php/addPlantPicture.php";
    private String hostnametp = "localhost";
    private String uerFtp = "root";
    private String passFtp = "";
    private int portAnInt = 21;

    //รอ
    public static String getUrlAmphur = "http://192.168.1.130/android/php/selectdistrict.php";

    private String[] columnCropTypeString = new String[]{"TID","croptype"};
    private String[] columnPlanString = new String[]{"No", "MID", "PDate", "CID", "Area"};
    private String[] comlumRegisterString = new String[]{"MID","UserID","PWD","ID","Name","Address","Tel","EMail"};



    private int[] iconInts = new int[]{
            R.drawable.ic_action_drawerplan,
            R.drawable.ic_action_date,
            R.drawable.ic_action_drawerdata,
            R.drawable.ic_action_drawerplan,
            R.drawable.ic_action_drawerplan,
            R.drawable.ic_action_drawerplan,
            R.drawable.ic_action_userregister,
            R.drawable.ic_action_exit};

    private String[] titleStrings = new String[]{
            "วางแผนเพาะปลูก",
            "ปฏิทินการเพาะปลูก",
            "ข้อมูลการเพาะปลูก",
            "ข้อมูลพืชเพาะปลูก",
            "เพิ่มข้อมูลประเภทพืช",
            "เข้อมูลประเภทพืช",
            "ข้อมูลเพาะปลูก",
            "Exit"};

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

    public String getUrlAddCrpo() {
        return urlAddCrpo;
    }

    public String getUrladdPlan() {
        return urladdPlan;
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
