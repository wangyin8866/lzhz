package com.example.user.liangzi.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

/**
 * Created by User on 2017/4/15.
 */

public class CommonUtils {
    private DecimalFormat numFormat = new DecimalFormat("#.##");

    private static CommonUtils instance;
    private CommonUtils(){

    }

    public static CommonUtils getInstance(){
        if(instance == null) {
            synchronized (CommonUtils.class) {
                if(instance == null) {
                    instance = new CommonUtils();
                }
            }
        }
        return instance;
    }

    public DecimalFormat getNumFormat() {
        return numFormat;
    }

    // 手机号码验证
    public static boolean isMobile(String tel) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(tel);
        return m.matches();
    }

    public static CharSequence matcherSearchText(int color, String string, String keyWord) {
        SpannableStringBuilder builder = new SpannableStringBuilder(string);
        int indexOf = string.indexOf(keyWord);
        if (indexOf != -1) {
            builder.setSpan(new ForegroundColorSpan(color), indexOf, indexOf + keyWord.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }

    public static String getVersionCode(Context _context, String _package)// 获取版本号
    {
        try {
            return String.valueOf(_context.getPackageManager().getPackageInfo(_package, 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0";
        }
    }


}
