package th.in.nattawut.plancrop.utility;

import th.in.nattawut.plancrop.R;

public class Myconstant {


    private String urlGetUser = "http://192.168.1.30/android/php/getAll.php";
    private String urlRegister = "http://192.168.1.30/android/php/addregister.php";
    private String urlGetPlan = "http://192.168.1.30/android/php/getPlanView.php";
    private String urladdPlan = "http://192.168.1.30/android/php/addPlan.php";
    public static String getUrlCrop ="http://192.168.1.30/android/php/selectCrop.php";
    public static String getUrlCropType = "http://192.168.1.30/android/php/selectcroptype.php";

    public static String urlmid = "http://192.168.1.30/android/php/selectmid.php";
    private  String urlGetRegister = "http://192.168.1.30/android/php/getregister.php";

    //spinner จังหวัด อำเภอ ตำบล
    public static String getUrlProvince = "http://192.168.1.30/android/php/selectprovince.php";
    public static String getUrlAmphur = "http://192.168.1.30/android/php/selectdistrict.php";

    //พืช
    private String urlAddCrop = "http://192.168.1.30/android/php/insertcrop.php";

    //ประเภทพืช
    private String urlAddCropType = "http://192.168.1.30/android/php/insertcroptype.php";
    private String urlselectcroptype = "http://192.168.1.30/android/php/selectcroptype.php";
    private String urlEditCropType = "http://192.168.1.30/android/php/editcroptype.php";
    private String urlDeleteCropType = "http://192.168.130/android/php/deletecroptype.php";
    private String[] columnCropTypeString = new String[]{"TID","croptype"};

    //ทดสอบ
    private String urlplantpicture = "http://192.168.1.30/android/php/addPlantPicture.php";
    private String hostnametp = "localhost";
    private String uerFtp = "root";
    private String passFtp = "";
    private int portAnInt = 21;



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


    //พืช


    public String getUrlAddCrop() {
        return urlAddCrop;
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
