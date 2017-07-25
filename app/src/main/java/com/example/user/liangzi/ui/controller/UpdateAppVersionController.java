package com.example.user.liangzi.ui.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.liangzi.Constants;
import com.example.user.liangzi.R;
import com.example.user.liangzi.net.BaseApi;
import com.example.user.liangzi.net.NetUtils;
import com.example.user.liangzi.net.injectVo.AppVersionVo;
import com.example.user.liangzi.service.VersionService;
import com.example.user.liangzi.ui.activity.WelcomeActivity;
import com.example.user.liangzi.utils.CommonUtils;
import com.example.user.liangzi.utils.FileUtils;
import com.example.user.liangzi.utils.ToastAlone;
import com.example.user.liangzi.view.LoadingDialog;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by User on 2017/5/8.
 */

public class UpdateAppVersionController {

    //    private Version versionBean;
    private ProgressBar pb;
    private TextView tv;
    private URI url = null;
    private String apkname = "";
    private String saveURI = Environment.getExternalStorageDirectory()
            + Constants.DOWNLOADFILE;
    ;
    private long apkLength;
    private AlertDialog updataAlertdialog;
    private AlertDialog remindDialog;
    private Activity mActivity;
    private FileUtils fileutils;
    private JSONObject jsonObject;
    private boolean isShowToast;
    private boolean hasNewVersion;
    private String upd_mandatory, file_url, description;


    public UpdateAppVersionController(Activity mActivity, boolean isShowToast) {
        this.mActivity = mActivity;
        this.isShowToast = isShowToast;
        fileutils = new FileUtils();
    }


    public void getData() {
        NetUtils.getInstance().postRequest(NetUtils.build().successToast(isShowToast).failToast(isShowToast), new BaseApi() {
            @Override
            public Observable getObservable(Retrofit retrofit) {
                AppVersionVo requestSerives = retrofit.create(AppVersionVo.class);//这里采用的是Java的动态代理模式
                return requestSerives.getVersion(CommonUtils.getVersionCode(mActivity, mActivity.getPackageName()), Constants.CLIENT_ID);//传入我们请求的键值对的值
            }
        }, new NetUtils.NetUtilsListener() {
            @Override
            public void success(JSONObject result, int code) {
                if (isShowToast) {
                    LoadingDialog.progressDismiss();
                }
                if (TextUtils.isEmpty(result.optString("ver_num"))) {
                    ((WelcomeActivity) mActivity).setChecked(true);
                } else {
                    hasNewVersion = true;
                    upd_mandatory = result.optString("upd_mandatory");
                    file_url = result.optString("file_url");
                    description = result.optString("description");
                    DownLoad();
                }


            }

            @Override
            public void success(String msg) {

            }

            @Override
            public void fail(String msg) {
                if (isShowToast) {
                    LoadingDialog.progressDismiss();
                }
                ((WelcomeActivity) mActivity).setChecked(true);
            }
        });

        if (isShowToast) LoadingDialog.progressShow(mActivity);
    }


    /**
     * <p>
     * 下载文件
     * </p>
     *
     * @author 106460
     * @date 2014年12月15日 下午8:47:28
     * @see
     */
    private void DownLoad() {
        NetUtils.getInstance().validateConnectionReceiver(mActivity);
        if (NetUtils.getInstance().isNetworkAvailable()) {
            // 新建线程
            new Thread() {
                /**
                 *
                 * <p>
                 * 传递消息
                 * </p>
                 *
                 * @author 106460
                 * @date 2014年12月16日 下午2:49:26
                 * @see java.lang.Thread#run()
                 */
                public void run() {
                    Looper.prepare();
                    try {
                        Message msg = BroadcastHandler.obtainMessage();
                        // BroadcastHandler.sendMessage(msg);
                        BroadcastHandler.sendMessage(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Looper.loop();
                }
            }.start();
        }
    }

    /**
     *
     */
    private Handler BroadcastHandler = new Handler() {
        /**
         *
         * <p>
         * 获取后台返回的内容
         * </p>
         *
         * @author 106460
         * @date 2014年12月15日 下午8:47:47
         * @param msg
         * @see android.os.Handler#handleMessage(android.os.Message)
         */
        public void handleMessage(Message msg) {
            // 优化 创建弹出框，设定 确定和忽略按钮
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity)
                    .setTitle("新版本更新内容")
                    .setMessage(description)
                    .setCancelable(false)
                    .setNegativeButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                /**
                                 *
                                 * <p>点击确定按钮后，进行内容更新</p>
                                 * @author 106460
                                 * @date 2014年12月15日 下午8:48:14
                                 * @param dialog
                                 * @param which
                                 * @see android.content.DialogInterface.OnClickListener#onClick(android.content.DialogInterface, int)
                                 */
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // 调用开始下载更新方法，进行更新
                                    Beginning();
                                    // 更新完后 隐藏弹出框
                                    dialog.dismiss();
                                }
                            });
            // 如果更新类型是0，则设置忽略按钮
            if ("1".equals(upd_mandatory)) {
                builder.setPositiveButton("忽略",
                        new DialogInterface.OnClickListener() {
                            /**
                             *
                             * <p>
                             * 点击忽略，弹出框隐藏
                             * </p>
                             *
                             * @author 106460
                             * @date 2014年12月15日 下午8:48:24
                             * @param dialog
                             * @param whichButton
                             * @see android.content.DialogInterface.OnClickListener#onClick(android.content.DialogInterface,
                             *      int)
                             */
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                dialog.dismiss();
                                // 如果回调接口不为空 则返回
                                ((WelcomeActivity) mActivity).setChecked(true);
                            }
                        });
            }
            remindDialog = builder.create();
            remindDialog.show();
        }
    };

    /**
     * <p>
     * 开始更新新版本，并建立进度条提示更新进度
     * </p>
     *
     * @author 106460
     * @date 2014年12月15日 下午8:48:34
     * @see
     */
    public void Beginning() {
        // 进度条
        LinearLayout ll = (LinearLayout) LayoutInflater.from(mActivity)
                .inflate(R.layout.layout_loadapk, null);
        pb = (ProgressBar) ll.findViewById(R.id.down_pb);
        tv = (TextView) ll.findViewById(R.id.tv);
        // 建立弹出框，显示更新进度
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity)
                .setTitle("版本更新进度提示").setCancelable(false).setView(ll);

        updataAlertdialog = builder.create();
        updataAlertdialog.show();
        new Thread() {
            /**
             *
             * <p>
             * 获取更新的url\apkname,将文件下载到默认文件夹下
             * </p>
             *
             * @author 106460
             * @date 2014年12月15日 下午8:48:47
             * @see java.lang.Thread#run()
             */
            public void run() {
                String urString = file_url;
                apkname = urString.substring(urString.lastIndexOf("/") + 1);
                // 调用下载方法
                loadFile(urString, Constants.DOWNLOADFILE, apkname);
            }
        }.start();
    }

    // 回调监听器对象
    FileUtils.DialogOption dialogoption = new FileUtils.DialogOption() {

        @Override
        /**
         *
         * <p>监听返回的数据情况</p>
         * @author 106460
         * @date 2014年12月15日 下午8:48:52
         * @param flag
         * @param c
         * @param error
         * @see com.deppon.common.utils.FileUtils.DialogOption#showData(int, int, java.lang.String)
         */
        public void showData(int flag, int c, String error) {
            sendMsg(1, c, null);
        }
    };

    @SuppressWarnings("unused")
    /**
     *
     * <p>下载文件，对文件是否已经存在进行判断，如果已经存在则进行提示</p>
     * @author 106460
     * @date 2014年12月15日 下午8:48:59
     * @param url
     * @param path
     * @param fileName
     * @see
     */
    public void loadFile(String url, String path, String fileName) {
        FileUtils fileUtils = new FileUtils();
        try {
            if (false) {// fileUtils.isFileExist(path+fileName)
                sendMsg(-1, 0, "文件已经存在");
                sendMsg(2, 0, null);
            } else {
                fileutils.deleteFile(path + fileName);
                InputStream input = null;
                input = getInputStream(url);
                fileUtils.setDialogoption(dialogoption);
                // 将一个InputStream里面的数据写入到SD卡中
                File resultFile = fileUtils.write2SDFromInput(path, fileName,
                        input, apkLength);
                // 如果写入SD卡空，提示下载失败
                if (resultFile == null) {
                    sendMsg(-1, 0, "下载失败");
                } else {
                    sendMsg(2, 0, null);
                }
            }

        } catch (Exception e) {
            sendMsg(-1, 0, e.getMessage());
        }
    }

    /**
     * <p>
     * 传递消息给handler
     * </p>
     *
     * @param flag
     * @param c
     * @param error
     * @author 106460
     * @date 2014年12月15日 下午8:49:11
     * @see
     */
    public void sendMsg(int flag, int c, String error) {
        Message msg = new Message();
        msg.what = flag;
        msg.arg1 = c;
        if (error != null) {
            Bundle bundle = new Bundle();
            bundle.putString("error", error);
            msg.setData(bundle);
        }
        handler.sendMessage(msg);
    }

    private Handler handler = new Handler() {
        @Override
        /**
         *
         * <p>根据传递的消息代码 进行不同的处理操作</p>
         * @author 106460
         * @date 2014年12月15日 下午8:49:24
         * @param msg
         * @see android.os.Handler#handleMessage(android.os.Message)
         */
        public void handleMessage(Message msg) {// 定义一个Handler，用于处理下载线程与UI间通讯
            if (!Thread.currentThread().isInterrupted()) {
                switch (msg.what) {
                    case 1:
                        pb.setProgress(msg.arg1);
                        VersionService.loading_process = msg.arg1;
                        tv.setText("已为您加载了：" + VersionService.loading_process + "%");
                        break;
                    case 2:
                        updataAlertdialog.dismiss();
                        Intent intent = new Intent();
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String targetDir = saveURI + apkname;
                        intent.setDataAndType(Uri.fromFile(new File(targetDir)),
                                "application/vnd.android.package-archive");
                        mActivity.startActivity(intent);
                        break;
                    case -1:
                        ((WelcomeActivity) mActivity).finish();
                        String error = msg.getData().getString("error");
                        Toast.makeText(mActivity, error, Toast.LENGTH_LONG).show();
                        break;
                }
            }
            super.handleMessage(msg);
        }
    };

    // 由于得到一个InputStream对象是所有文件处理前必须的操作，所以将这个操作封装成了一个方法
    public InputStream getInputStream(String urlStr) throws IOException,
            URISyntaxException {
        InputStream is = null;
        try {
            url = new URI(urlStr);
            HttpClient client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            client.getParams().setParameter(
                    CoreConnectionPNames.CONNECTION_TIMEOUT, 150000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                    150000);
            HttpResponse response = client.execute(httpget);

            HttpEntity entity = response.getEntity();
            apkLength = entity.getContentLength();
            is = entity.getContent();
        } catch (MalformedURLException e) {
            e.printStackTrace();
//            Logger.d("读取流文件异常");
        }
        return is;
    }

    /**
     * <p>
     * 检查版本更新
     * </p>
     *
     * @author 106460
     * @date 2014年12月15日 下午8:49:38
     * @see
     */
    public void checkVersion() {
        if (VersionService.isDownLoading) {
            ToastAlone.showToast(mActivity, "正在下载中...", 1);
        } else {
            getData();
        }
    }

    /**
     * 判断是否显示出来
     *
     * @return
     * @Description
     * @author wangwx
     */
    public boolean isShow() {
        return (remindDialog != null && remindDialog.isShowing())
                || (updataAlertdialog != null && updataAlertdialog.isShowing());
    }


}
