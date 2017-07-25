package com.example.user.liangzi.constans;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;

import com.example.user.liangzi.UserManager;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;

import static java.util.regex.Pattern.compile;

/**
 * Created by User on 2016/12/13.
 */

public class ToolsUtils {
    public static void showShare(Context context, View view, boolean silent, String platform,int number) {
        String targetUrl;
        final OnekeyShare oks = new OnekeyShare();
        if(number==1){
            oks.setTitle("邀你一起加入量子互助,预存9元最高获30万健康保障");
            String content="新手加入预存9元,限时送会员vip服务";
            oks.setText(content);
            targetUrl = "https://www.liangzihuzhu.com.cn/xwh5/pages/activity/invite.html"+"?"+"invite_code="+ UserManager.getInstance().getInvite_code();
        }else{
            oks.setTitle("量子互助");
            String customText = "快来加入量子互助吧!";
            oks.setText(customText);
            targetUrl="https://www.liangzihuzhu.com.cn/xwh5/pages/index.html";
        }
        oks.setUrl(targetUrl);
        oks.setImageUrl("https://www.liangzihuzhu.com.cn/publicImg/share.png");
        oks.setComment("快来评论吧!");
        oks.setSilent(silent);
        oks.setTheme(OnekeyShareTheme.CLASSIC);
        if (platform != null) {
            oks.setPlatform(platform);
        }
        // 令编辑页面显示为Dialog模式
        oks.setDialogMode();
        // 在自动授权时可以禁用SSO方式
        oks.disableSSOWhenAuthorize();
        // 去除注释，则快捷分享的操作结果将通过OneKeyShareCallback回调
        //oks.setCallback(new OneKeyShareCallback());
        // 去自定义不同平台的字段内容
        //oks.setShareContentCustomizeCallback(new ShareContentCustomizeDemo());
        oks.setEditPageBackground(view);
        oks.show(context);
    }
    // 手机号码验证
    public static boolean isMobile(String tel) {
        Pattern p =
                    compile("^1\\d{10}");
        Matcher m = p.matcher(tel);
        return m.matches();
    }
    //打电话
    public static void callPhone(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                + url));
        context.startActivity(intent);
    }
    /**
     * 对网络连接状态进行判断
     * @return  true, 可用； false， 不可用
     */
    public static boolean isOpenNetwork(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if(networkInfo!= null) {

            //2.获取当前网络连接的类型信息
            int networkType = networkInfo.getType();
            if(ConnectivityManager.TYPE_WIFI == networkType){
                //当前为wifi网络
            }else if(ConnectivityManager.TYPE_MOBILE == networkType){
                //当前为mobile网络
            }
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
    public static String getResoult(String string) throws Exception {
        if(string != null && !string.equals("") && string.contains("result")) {
            JSONObject json = new JSONObject(string);
            return json.get("result").toString();
        } else {
            return "";
        }
    }
    //判断是否安装支付宝app
    public static boolean checkAliPayInstalled(Context context) {

        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }
}
