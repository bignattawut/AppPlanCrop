package th.in.nattawut.plancrop.utility;

public class Myconstant {


    String ip = "192.168.1.122";

    //ล็อคอิน
    private String urlGetUser = "http://"+ip+"/android/php/memberlogin.php";
    private String nameFileSharePreference = "Plan";


    //วางแผนเพาะปลูก
    private String urladdPlan = "http://"+ip+"/android/php/insertplan.php";
    public static String getUrlCrop ="http://192.168.1.122/android/php/selectspinnercrop.php";
    public static String getUrlmid = "http://192.168.1.122/android/php/selectmidspinner.php";
    private String urlDeletePlan = "http://"+ip+"/android/php/deleteplan.php";
    private String urlselectPlan = "http://"+ip+"/android/php/selectplanandroid.php";
    private String[] columnPlanString = new String[]{"no","mid","name","crop","pdate","area"};
    private String urlEditPlan = "http://"+ip+"/android/php/editplan.php";

    private String urlselectfarmerandroid = "http://"+ip+"/android/php/selectplanfarmerandroid.php";
    private String[] columnPlanfarmerString = new String[]{"no","pdate","cid","crop","area"};
    public String getUrlselectfarmerandroid() {
        return urlselectfarmerandroid;
    }
    public String[] getColumnPlanfarmerString() {
        return columnPlanfarmerString;
    }

    //เกษตรกร
    private String urlFarmer = "http://"+ip+"/android/php/insertfarmer.php";
    private String urlselectFarmer = "http://"+ip+"/android/php/selectfarmer.php";
    private String[] comlumFarmerString = new String[]{"mid","name","thai","tel","email"};//ต้องแก้ไขยังไม่เสร็จ
    public String[] getComlumFarmerString() {
        return comlumFarmerString;
    }
    private String[] comlumFarmerString1 = new String[]{"mid","userid","pwd","id","name","address","pid","did","sid","vid","tel","email","area"};//ต้องแก้ไขยังไม่เสร็จ
    public String[] getComlumFarmerString1() {
        return comlumFarmerString1;
    }
    private String urlEditFarmer = "http://"+ip+"/android/php/editfarmer.php";
    private String selectfarmerandroid = "http://"+ip+"/android/php/selectfarmerandroid.php";
    public String getSelectfarmerandroid() {
        return selectfarmerandroid;
    }
    private String urlEditFarmerAndroid = "http://"+ip+"/android/php/editfarmerandroid.php";

    //สมาชิก
    private String urlRegister = "http://"+ip+"/android/php/insertmember.php";
    private  String urlselectMember = "http://"+ip+"/android/php/selectmember.php";
    private String[] comlumRegisterString = new String[]{"mid","userid","pwd","id","name","address","pid","did","sid","vid","tel","email"};
    private String urlDeleteFammer = "http://"+ip+"/android/php/deletefammer.php";
    private String urlEditRegister = "http://"+ip+"/android/php/editmember.php";
    private String selectMemberAndroid = "http://"+ip+"/android/php/selectmemberandroid.php";
    private String urlEditMemberAndroid = "http://"+ip+"/android/php/editmemberandroid.php";

    //spinner จังหวัด อำเภอ ตำบล
    public static String getUrlProvince = "http://192.168.1.122/android/php/selectprovince.php";
    public static String getUrlAmphur = "http://192.168.1.122/android/php/selectdistrict.php";
    public static String getUrlSid = "http://192.168.1.122/android/php/selectsubdistrict.php";
    public static String getUrlVid = "http://192.168.1.122/android/php/selectsitevillage.php";



    //พืช
    private String urlAddCrop = "http://"+ip+"/android/php/insertcrop.php";
    private String urlselectCrop = "http://"+ip+"/android/php/selectcrop.php";
    private String[] columnCropString = new String[]{"cid","crop","tid","croptype","beginharvest","harvestperiod","yield"};
    //private String urlselectCrop = "http://10.200.1.38/android/php/a.php";
    private String urlDeleteCrop = "http://"+ip+"/android/php/deletecrop.php";
    private String urlEditCrop = "http://"+ip+"/android/php/editcrop.php";

    //ประเภทพืช
    public static String getUrlCropType = "http://192.168.1.122/android/php/selectcroptype.php";
    private String urlAddCropType = "http://"+ip+"/android/php/insertcroptype.php";
    private String urlselectcroptype = "http://"+ip+"/android/php/selectcroptype.php";
    private String urlEditCropType = "http://"+ip+"/android/php/editcroptype.php";
    private String urlDeleteCropType = "http://"+ip+"/android/php/deletecroptype.php";
    private String[] columnCropTypeString = new String[]{"TID","croptype"};

    //การเพาะปลูก
    private String urladdPlant = "http://"+ip+"/android/php/insertplant.php";
    public static String getUrlSite ="http://192.168.1.122/android/php/selectspinnersite.php";
    private String urlselectPlant = "http://"+ip+"/android/php/selectplant.php";
    private String[] columnPlantString = new String[]{"no","pdate","cid","yield","crop","area","mid","name","sno","lat","lon"};
    private String urlDeletePlant = "http://"+ip+"/android/php/deleteplant.php";
    private String urlEditPlant = "http://"+ip+"/android/php/editplant.php";


    private String[] columnsitefarmerString = new String[]{"sno","thai"};
    public String[] getColumnsitefarmerString() {
        return columnsitefarmerString;
    }
    public String selectsitefarmer ="http://"+ip+"/android/php/selectsitefarmer.php";//?mid=72
    public String getSelectsitefarmer() {
        return selectsitefarmer;
    }

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
    private String urlAddPlantPicture = "http://"+ip+"/android/php/insertactivity.php";
    private String[] columnPlantPicaString = new String[]{"picno","pdate","description","no"};
   // private String[] columnPlantPicString = new String[]{"SCode","URL"};
    private String[] columnPlantPicString = new String[]{"picno","pdate","description"};
    private String urlselectPlantPic = "http://"+ip+"/android/php/plantactivity.php?no=49";
    private String urlselectImagesPlantPic = "http://"+ip+"/android/php/selectimages.php";
    private String urlPlant = "http://"+ip+"/android/php/plant.php";
    private String urlDeletePlantPic = "http://"+ip+"/android/php/deleteactivity.php";
    private String urlEditPlantPic = "http://"+ip+"/android/php/editactivity.php";
    //private String urlPlant = "http://192.168.1.107/android/php/selectplantfarmer.php";



    //แจ้งความต้องการ
    private String urladdorder = "http://"+ip+"/android/php/insertorder.php";
    private String[] columnOrderString = new String[]{"sdate","edate","crop","qty","name","tel"};
    private String urlselectOrder = "http://"+ip+"/android/php/";
    private String urlDeleteOrder = "http://"+ip+"/android/php/deleteorder.php";
    private String urlEditOrder = "http://"+ip+"/android/php/editorder.php";
    private String urlselectorderreport = "http://"+ip+"/android/php/selectorderreport.php";

    //แปลงเพาะปลูก
    private String urladdSite = "http://"+ip+"/android/php/insertsite.php";
    private String urlselectSite = "http://"+ip+"/android/php/selectsite.php";
    private String urlDeleteSite = "http://"+ip+"/android/php/deletesite.php";
    private String urlEditSite = "http://"+ip+"/android/php/editsite.php";
    private String urlSiteFarmer = "http://"+ip+"/android/php/farmer.php";
    private String urlSelectSiteVillageFarmer = "http://"+ip+"/android/php/selectsitevillagefarmer.php";



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
    public String getUrlEditFarmer() {
        return urlEditFarmer;
    }
    public String getUrlEditFarmerAndroid() {
        return urlEditFarmerAndroid;
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
    public String getSelectMemberAndroid() {
        return selectMemberAndroid;
    }
    public String getUrlEditMemberAndroid() {
        return urlEditMemberAndroid;
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
    public String getUrlPlant() {
        return urlPlant;
    }
    public String getUrlDeletePlantPic() {
        return urlDeletePlantPic;
    }

    public String getUrlEditPlantPic() {
        return urlEditPlantPic;
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
    public String getUrlSiteFarmer() {
        return urlSiteFarmer;
    }
    public String getUrlSelectSiteVillageFarmer() {
        return urlSelectSiteVillageFarmer;
    }
}

