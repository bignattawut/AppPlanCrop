package th.in.nattawut.plancrop.utility;

import th.in.nattawut.plancrop.R;

public class Myconstant {


    private String urlGetUser = "http://192.168.1.121/android/php/getAll.php";
    private String urlRegister = "http://192.168.1.121/android/php/addregister.php";
    private String urlGetPlan = "http://192.168.1.121/android/php/getPlanView.php";
    private String urlAddCrpo = "http://192.168.1.121/android/php/addcrop.php";
    private String urladdPlan = "http://192.168.1.121/android/php/addPlan.php";
    public static String getUrlCrop ="http://192.168.1.121/android/php/selectCrop.php";
    public static String getUrlCropType = "http://192.168.1.121/android/php/selectcroptype.php";
    public static String getUrlProvince = "http://192.168.1.121/android/php/selectprovince.php";
    public static String urlmid = "http://192.168.1.121/android/php/selectmid.php";
    private  String urlGetRegister = "http://192.168.1.121/android/php/getregister.php";


    //ทดสอบ
    private String urlplantpicture = "http://192.168.1.121/android/php/addPlantPicture.php";

    private String hostnametp = "localhost";
    private String uerFtp = "root";
    private String passFtp = "";
    private int portAnInt = 21;
    //

    public static String getUrlAmphur = "http://192.168.1.130/android/php/selectdistrict.php";

    private String[] columnPlanString = new String[]{"No", "MID", "PDate", "CID", "Area"};
    private String[] comlumRegisterString = new String[]{"MID","UserID","PWD","ID","Name","Address","Tel","EMail"};
    private int[] iconInts = new int[]{
            R.drawable.ic_action_drawerplan,
            R.drawable.ic_action_date,
            R.drawable.ic_action_drawerdata,
            R.drawable.ic_action_drawerplan,
            R.drawable.ic_action_userregister,
            R.drawable.ic_action_exit};

    private String[] titleStrings = new String[]{
            "วางแผนเพาะปลูก",
            "ปฏิทินการเพาะปลูก",
            "ข้อมูลการเพาะปลูก",
            "ข้อมูลพืชเพาะปลูก",
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
