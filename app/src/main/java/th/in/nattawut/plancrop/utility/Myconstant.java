package th.in.nattawut.plancrop.utility;

public class Myconstant {


    //ล็อคอิน
    private String urlGetUser = "http://192.168.1.144/android/php/memberlogin.php";
    private String nameFileSharePreference = "Plan";



    //วางแผนเพาะปลูก
    private String urladdPlan = "http://192.168.1.144/android/php/insertplan.php";
    public static String getUrlCrop ="http://192.168.1.144/android/php/selectspinnercrop.php";
    public static String getUrlmid = "http://192.168.1.144/android/php/selectmidspinner.php";
    private String urlDeletePlan = "http://192.168.1.144/android/php/deleteplan.php";
    private String urlselectPlan = "http://192.168.1.144/android/php/selectplanandroid.php";
    private String[] columnPlanString = new String[]{"no","mid","name","crop","pdate","area"};
    private String urlEditPlan = "http://192.168.1.144/android/php/editplan.php";

    private String urlselectfarmerandroid = "http://192.168.1.144/android/php/selectplanfarmerandroid.php";
    private String[] columnPlanfarmerString = new String[]{"no","pdate","cid","crop","area"};
    public String getUrlselectfarmerandroid() {
        return urlselectfarmerandroid;
    }
    public String[] getColumnPlanfarmerString() {
        return columnPlanfarmerString;
    }

    //เกษตรกร
    private String urlFarmer = "http://192.168.1.144/android/php/insertfarmer.php";
    private String urlselectFarmer = "http://192.168.1.144/android/php/selectfarmer.php";
    private String[] comlumFarmerString = new String[]{"mid","name","thai","tel","email"};//ต้องแก้ไขยังไม่เสร็จ
    public String[] getComlumFarmerString() {
        return comlumFarmerString;
    }

    private String[] comlumFarmerString1 = new String[]{"mid","userid","pwd","id","name","address","vid","sid","tel","email","area"};//ต้องแก้ไขยังไม่เสร็จ
    public String[] getComlumFarmerString1() {
        return comlumFarmerString1;
    }

    //สมาชิก
    private String urlRegister = "http://192.168.1.144/android/php/insertmember.php";
    private  String urlselectMember = "http://192.168.1.144/android/php/selectmember.php";
    private String[] comlumRegisterString = new String[]{"mid","userid","pwd","id","name","address","pid","did","sid","vid","tel","email"};
    private String urlDeleteFammer = "http://192.168.1.144/android/php/deletefammer.php";
    private String urlEditRegister = "http://192.168.1.144/android/php/editfarmer.php";

    //spinner จังหวัด อำเภอ ตำบล
    public static String getUrlProvince = "http://192.168.1.144/android/php/selectprovince.php";
    public static String getUrlAmphur = "http://192.168.1.144/android/php/selectdistrict.php";
    public static String getUrlSid = "http://192.168.1.144/android/php/selectsubdistrict.php";
    public static String getUrlVid = "http://192.168.1.144/android/php/selectsitevillage.php";



    //พืช
    private String urlAddCrop = "http://192.168.1.144/android/php/insertcrop.php";
    private String urlselectCrop = "http://192.168.1.144/android/php/selectcrop.php";
    private String[] columnCropString = new String[]{"cid","crop","tid","croptype","beginharvest","harvestperiod","yield"};
    //private String urlselectCrop = "http://10.200.1.38/android/php/a.php";
    private String urlDeleteCrop = "http://192.168.1.144/android/php/deletecrop.php";
    private String urlEditCrop = "http://192.168.1.144/android/php/editcrop.php";


    //ประเภทพืช
    public static String getUrlCropType = "http://192.168.1.144/android/php/selectcroptype.php";
    private String urlAddCropType = "http://192.168.1.144/android/php/insertcroptype.php";
    private String urlselectcroptype = "http://192.168.1.144/android/php/selectcroptype.php";
    private String urlEditCropType = "http://192.168.1.144/android/php/editcroptype.php";
    private String urlDeleteCropType = "http://192.168.1.144/android/php/deletecroptype.php";
    private String[] columnCropTypeString = new String[]{"TID","croptype"};

    //การเพาะปลูก
    private String urladdPlant = "http://192.168.1.144/android/php/insertplant.php";
    public static String getUrlSite ="http://192.168.1.144/android/php/selectspinnersite.php";
    private String urlselectPlant = "http://192.168.1.144/android/php/selectplant.php";
    private String[] columnPlantString = new String[]{"no","pdate","cid","yield","crop","area","mid","name","sno","lat","lon"};
    private String urlDeletePlant = "http://192.168.1.144/android/php/deleteplant.php";
    private String urlEditPlant = "http://192.168.1.144/android/php/editplant.php";


    //การเพาะปลูก
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
    public String getUrlEditPlant() {
        return urlEditPlant;
    }

    //กิจกรรม
    private String urlAddPlantPicture = " http://192.168.1.144/android/php/insertactivity.php";
    private String[] columnPlantPicaString = new String[]{"picno","pdate","description","no"};
    private String[] columnPlantPicString = new String[]{"SCode","URL"};
    private String urlselectPlantPic = "http://192.168.1.144/android/php/plantactivity.php";
    private String urlselectImagesPlantPic = "http://192.168.1.144/android/php/selectimages.php";



    //แจ้งความต้องการ
    private String urladdorder = "http://192.168.1.144/android/php/insertorder.php";
    private String[] columnOrderString = new String[]{"sdate","edate","crop","qty","name","tel"};
    private String urlselectOrder = "http://192.168.1.144/android/php/";
    private String urlDeleteOrder = "http://192.168.1.144/android/php/deleteorder.php";
    private String urlEditOrder = "http://192.168.1.144/android/php/editorder.php";
    private String urlselectorderreport = "http://192.168.1.144/android/php/selectorderreport.php";

    //แปลงเพาะปลูก
    private String urladdSite = "http://192.168.1.144/android/php/insertsite.php";
    private String urlselectSite = "http://192.168.1.144/android/php/selectsite.php";
    private String urlDeleteSite = "http://192.168.1.144/android/php/deletesite.php";
    private String urlEditSite = "http://192.168.1.144/android/php/editsite.php";

    //ล็อคอิน
    public String getUrlGetUser() {
        return urlGetUser;
    }
    public String getNameFileSharePreference() {
        return nameFileSharePreference;
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
    public String getUrlselectMember() {
        return urlselectMember;
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


    //แจ้งความต้องการ
    public String getUrladdorder() {
        return urladdorder;
    }
    public String getUrlselectOrder() {
        return urlselectOrder;
    }
    public String[] getColumnOrderString() {
        return columnOrderString;
    }
    public String getUrlDeleteOrder() {
        return urlDeleteOrder;
    }
    public String getUrlEditOrder() {
        return urlEditOrder;
    }
    public String getUrlselectorderreport() {
        return urlselectorderreport;
    }


    //แปลงเพาะปลูก

    public String getUrladdSite() {
        return urladdSite;
    }
    public String getUrlselectSite() {
        return urlselectSite;
    }
    public String getUrlDeleteSite() {
        return urlDeleteSite;
    }
    public String getUrlEditSite() {
        return urlEditSite;
    }
}

