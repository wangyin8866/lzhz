package com.example.user.liangzi.net;

/**
 * Created by User on 2017/4/14.
 */

public class NetUrls {
    public static final String URL_IP = "https://www.liangzihuzhu.com.cn/molecule/";//正式环境
//    public static final String URL_IP = "https://devsrv.liangzihuzhu.com.cn/molecule/";//测试环境
    public static final String URL_IP_H5 = "https://www.liangzihuzhu.com.cn/xwh5/pages/";//正式环境
//    public static final String URL_IP_H5 = "https://devsrv.liangzihuzhu.com.cn/xwh5/pages/";//测试环境
//    public static final String URL_IP_H5 = "https://192.168.11.43:8020/fzhz/src/pages/";//wwl环境

//    public static final String URL_IP = "https://192.168.11.81:10010/aos/";//zsc

    public static final String H5_SERVICE_ADDERSS_HELP = URL_IP_H5 + "index.html";
    public static final String H5_SERVICE_ADDERSS_DISCOVER = URL_IP_H5 + "discovery/discovery.html?refresh";
    public static final String H5_SERVICE_ADDERSS_NOTICE = URL_IP_H5 + "mutual/mutual.html?refresh";
    public static final String H5_AMOUNT = URL_IP_H5 + "mine/mutualMoney.html";
    public static final String H5_ADD_USER = URL_IP_H5 + "mine/myGuarantee.html";
    public static final String H5_INVITED = URL_IP_H5 + "mine/HasInvited.html";
    public static final String H5_JIFEN = URL_IP_H5 + "mine/integralDetail.html";
    public static final String H5_GUARANTEE = URL_IP_H5 + "mine/myGuarantee.html";
    public static final String H5_RED_PACKETS = URL_IP_H5 + "plan/HelpGift.html";
    public static final String H5_COUPONS = URL_IP_H5 + "activity/coupons.html";

    public static final String H5_INVITE_FRIENDS = URL_IP_H5 + "activity/inviteFriends.html";
    public static final String H5_ABOUNT_QUANTUM = URL_IP_H5 + "mine/knowQuantum.html";
    public static final String H5_COMMON_QUESTIONS = URL_IP_H5 + "protocol/personalCenterQA.html";

    public static final String GRAPHIC_VERIFY_CODE = "esb/common/getPicCodeInfo.jhtml";


    public static final String LOGIN_SMS_SEND = "esb/common/sendSMSii.jhtml";
    public static final String LOGIN = "esb/common/login.jhtml";

    public static final String USER_PROFILE = "esb/userInfo/myProfile.jhtml";
    public static final String USER_INVITATION = "esb/welfare/invite.jhtml";

    public static final String USER_EXIT = "esb/common/logout.jhtml";

    public static final String VERSION_CHECK = "esb/appVersion/getUpdateCall.jhtml";


}
