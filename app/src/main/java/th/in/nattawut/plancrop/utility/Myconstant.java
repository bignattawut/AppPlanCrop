package th.in.nattawut.plancrop.utility;

import android.util.Log;

import th.in.nattawut.plancrop.R;

public class Myconstant {


    //ล็อคอิน
    private String urlGetUser = "http://192.168.1.113/android/php/memberlogin.php";


    //วางแผนเพาะปลูก
    private String urladdPlan = "http://192.168.1.113/android/php/insertplan.php";
    public static String getUrlCrop ="http://192.168.1.113/android/php/selectspinnercrop.php";
    public static String getUrlmid = "http://192.168.1.113/android/php/selectmidspinner.php";
    private String urlDeletePlan = "http://192.168.1.113/android/php/deleteplan.php";
    //private String urlselectPlan = "http://192.168.1.108/android/php/selectplanfarmerandroid.php"; //วางแผนเฉาะของใคร
    private String urlselectPlan = "http://192.168.1.113/android/php/selectplanandroid.php";
    private String[] columnPlanString = new String[]{"no","mid","name","crop","pdate","area"};
    private String urlEditPlan = "http://192.168.1.113/android/php/editplan.php";

    //เกษตรกร
    private String urlFarmer = "http://192.168.1.113/android/php/insertfarmer.php";
    private String urlselectFarmer = "http://192.168.1.113/android/php/selectfarmer.php";
    private String[] comlumFarmerString = new String[]{"mid","name","area","tel","email"};//ต้องแก้ไขยังไม่เสร็จ
    public String[] getComlumFarmerString() {
        return comlumFarmerString;
    }

    //สมาชิก
    private String urlRegister = "http://192.168.1.113/android/php/insertmember.php";
    private  String urlGetRegister = "http://192.168.1.113/android/php/getregister.php";
    private String[] comlumRegisterString = new String[]{"MID","UserID","PWD","Name","ID","Address","VID","SID","Tel","EMail"};
    private String urlDeleteFammer = "http://192.168.1.113/android/php/deletefammer.php";
    private String urlEditRegister = "http://192.168.1.113/android/php/editfarmer.php";

    /*
    ///*
    private  String urlGetRegister = "http://10.200.1.38/android/php/selectmember.php";
    private String[] comlumRegisterString = new String[]{"mid","userid","pwd","name","id","address","vid","sid","tel","email"};
    ///
    */


    //spinner จังหวัด อำเภอ ตำบล
    public static String getUrlProvince = "http://192.168.1.113/android/php/selectprovince.php";
    public static String getUrlAmphur = "http://192.168.1.113/android/php/selectdistrict.php";

    public static String getUrlSid = "http://192.168.1.113/android/php/selectsubdistrict.php";

    //public String getUrlVid = "http://192.168.1.108/android/php/selectvillage.php";
    private String[] columnVidString = new String[]{"vid","thai"};
    public String getGetUrlVid() {
        return getUrlVid;
    }
    public String[] getColumnVidString() {
        return columnVidString;
    }

    public static String getUrlVid = "http://192.168.1.113/android/php/selectsitevillage.php";




    //พืช
    private String urlAddCrop = "http://192.168.1.113/android/php/insertcrop.php";
    private String urlselectCrop = "http://192.168.1.113/android/php/selectcrop.php";
    private String[] columnCropString = new String[]{"cid","crop","tid","croptype","beginharvest","harvestperiod","yield"};
    //private String urlselectCrop = "http://10.200.1.38/android/php/a.php";
    private String urlDeleteCrop = "http://192.168.1.113/android/php/deletecrop.php";
    private String urlEditCrop = "http://192.168.1.113/android/php/editcrop.php";


    //ประเภทพืช
    public static String getUrlCropType = "http://192.168.1.113/android/php/selectcroptype.php";
    private String urlAddCropType = "http://192.168.1.113/android/php/insertcroptype.php";
    private String urlselectcroptype = "http://192.168.1.113/android/php/selectcroptype.php";
    private String urlEditCropType = "http://192.168.1.113/android/php/editcroptype.php";
    private String urlDeleteCropType = "http://192.168.1.113/android/php/deletecroptype.php";
    private String[] columnCropTypeString = new String[]{"TID","croptype"};

    //การเพาะปลูก
    private String urladdPlant = "http://192.168.1.113/android/php/insertplant.php";
    public static String getUrlSite ="http://192.168.1.113/android/php/selectspinnersite.php";
    private String urlselectPlant = "http://192.168.1.113/android/php/selectplant.php";
    private String[] columnPlantString = new String[]{"no","pdate","cid","yield","crop","area","mid","name","sno","lat","lon"};
    private String urlDeletePlant = "http://192.168.1.113/android/php/deleteplant.php";



    public String[] getColumnPlantString() {
        return columnPlantString;
    }
    public String getUrlselectPlant() {
        return urlselectPlant;
    }
    public String getUrladdPlant() {
        return urladdPlant;
    }
    public String getUrlDeletePlant() {
        return urlDeletePlant;
    }

    //กิจกรรม
    private String urlAddPlantPicture = " http://192.168.1.113/android/php/insertactivity.php";
    private String[] columnPlantPicaString = new String[]{"picno","pdate","description","no"};
    private String[] columnPlantPicString = new String[]{"SCode","URL"};
    private String urlselectPlantPic = "http://192.168.1.113/android/php/plantactivity.php";
    private String urlselectImagesPlantPic = "http://192.168.1.113/android/php/selectimages.php";




    //drawerLayout
    private int[] iconInts = new int[]{
            R.drawable.ic_action_draweruser,
            R.drawable.ic_action_drawerplan,
            R.drawable.ic_action_date,
            R.drawable.ic_action_drawerpicture,
            R.drawable.ic_action_userregister,
            R.drawable.ic_action_exit};
    private String[] titleStrings = new String[]{
            "ข้อมูลส่วนตัว",
            "วางแผนเพาะปลูก",
            "ปฏิทินการเพาะปลูก",
            "บันทึกการเพาะปลูก",
            "admin",
            "Exit"};

    // drawerLayout
    public int[] getIconInts() {
        return iconInts;
    }
    public String[] getTitleStrings() {
        return titleStrings;
    }


    //ล็อคอิน
    public String getUrlGetUser() {
        return urlGetUser;
    }

    //เกษตรกร
    public String getUrlFarmer() {
        return urlFarmer;
    }
    public String getUrlselectFarmer() {
        return urlselectFarmer;
    }


    //สามชิก
    public String getUrlRegister() {
        return urlRegister;
    }
    public String[] getComlumRegisterString() {
        return comlumRegisterString;
    }
    public String getUrlGetRegister() {
        return urlGetRegister;
    }
    public String getUrlDeleteFammer() {
        return urlDeleteFammer;
    }
    public String getUrlEditRegister() {
        return urlEditRegister;
    }

    //วางแผนเพาะปลูก
    public String[] getColumnPlanString() {
        return columnPlanString;
    }
    /*public String getUrlGetPlan() {
        return urlGetPlan;
    }*/
    public String getUrlDeletePlan() {
        return urlDeletePlan;
    }
    public String getUrladdPlan() {
        return urladdPlan;
    }
    public String getUrlselectPlan() {
        return urlselectPlan;
    }
    public String getUrlEditPlan() {
        return urlEditPlan;
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
    public String getUrlDeleteCrop() {
        return urlDeleteCrop;
    }
    public String getUrlEditCrop() {
        return urlEditCrop;
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

    //กิจกกรม
    public String getUrlAddPlantPicture() {
        return urlAddPlantPicture;
    }
    public String[] getColumnPlantPicString() {
        return columnPlantPicString;
    }
    public String getUrlselectImagesPlantPic() {
        return urlselectImagesPlantPic;
    }
    public String getUrlselectPlantPic() {
        return urlselectPlantPic;
    }
    public String[] getColumnPlantPicaString() {
        return columnPlantPicaString;
    }
}
