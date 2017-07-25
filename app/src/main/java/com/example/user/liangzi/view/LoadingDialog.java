package com.example.user.liangzi.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;


/**
 * 加载框
 *
 * @author wangwx
 * @Description
 * @date 2014年6月25日 下午3:03:56
 */
public class LoadingDialog {
    public static Activity myActivity;
    public static ProgressDialog dialog;
    public static View dialogView;
    public static ImageView frame;
    // public static DialogLoadingCircleView circleView;

    /**
     * 显示加载框
     *
     * @param context
     */
    public static void progressShow(Context context) {
        try {
            if (dialog == null && context != null) {
                dialog = new ProgressDialog(context);
                dialog.setMessage("加载中，请稍后...");
                dialog.setIndeterminate(true);
                dialog.setCancelable(true);
            }
        } catch (Exception e) {
//            Logger.d(e.getMessage());
        }
    }

    /**
     * 取消加载框
     */
    public static void progressDismiss() {
        try {
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
//            Logger.d(e.getMessage());
        }
    }


    /**
     * 显示加载框
     *
     * @param context
     */
    public static void newProgressShow(Context context) {
        try {
            if (dialog == null && context != null) {
                dialog = new ProgressDialog(context);
                dialog.setMessage("加载中，请稍后...");
                dialog.setIndeterminate(true);
                dialog.setCancelable(true);
                dialog.show();

            }
        } catch (Exception e) {
//            Logger.d(e.getMessage());
        }
    }

    /**
     * 取消加载框
     */
    public static void newProgressDismiss() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    /**
     * 提交信息加载提示框
     *
     * @param context
     * @param message
     * @return
     */
    public static synchronized ProgressDialog loadDialog(Context context, String message) {
        dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        return dialog;
    }

    public static synchronized void removeDialog() {
        dialog.dismiss();
    }
}
