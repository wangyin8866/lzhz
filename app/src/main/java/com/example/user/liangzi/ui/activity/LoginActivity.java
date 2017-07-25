package com.example.user.liangzi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.liangzi.R;
import com.example.user.liangzi.UserManager;
import com.example.user.liangzi.constans.ToolsUtils;
import com.example.user.liangzi.net.BaseApi;
import com.example.user.liangzi.net.NetUrls;
import com.example.user.liangzi.net.NetUtils;
import com.example.user.liangzi.net.injectVo.GraphicVerifyCodeVo;
import com.example.user.liangzi.net.injectVo.LoginVo;
import com.example.user.liangzi.net.injectVo.SmsVerifyCode;
import com.example.user.liangzi.ui.BaseActivity;
import com.example.user.liangzi.utils.SharedPUtils;
import com.example.user.liangzi.view.CustomerDialog;
import com.example.user.liangzi.view.LoadingDialog;

import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.Retrofit;


/**
 * Created by User on 2016/12/12.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_phone,et_code;
    private Button but_login,but_code;
    private TextView tv_call;
    private String phone,code,message,ms,appcode,res,juid;
    private CustomerDialog dialog;
    private String codeAddress,verifyCode;
    private String oldId;



    @Override
    public void getIntentData(Bundle savedInstanceState) {

    }

    @Override
    public void loadXml() {
        setContentView(R.layout.activity_login);
    }


    @Override
    public void init(){
        et_phone= (EditText) findViewById(R.id.et_phone);
        et_code= (EditText) findViewById(R.id.et_code);
        but_code= (Button) findViewById(R.id.but_send_code);
        but_login= (Button) findViewById(R.id.but_login);
        tv_call= (TextView) findViewById(R.id.tv_call);
        dialog=new CustomerDialog(this);

    }

    @Override
    public void setListener() {
        but_code.setOnClickListener(this);
        but_login.setOnClickListener(this);
        tv_call.setOnClickListener(this);
    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }


    private void applyCode(){
        LoadingDialog.loadDialog(LoginActivity.this,"正在请求,请稍候...");
        NetUtils.getInstance().postRequest(NetUtils.build(), new BaseApi() {
            @Override
            public Observable getObservable(Retrofit retrofit) {
                GraphicVerifyCodeVo requestSerives = retrofit.create(GraphicVerifyCodeVo.class);//这里采用的是Java的动态代理模式
                return requestSerives.getGraphicVerifyCode();//传入我们请求的键值对的值
            }
        }, new NetUtils.NetUtilsListener() {
            @Override
            public void success(JSONObject result, int code) {
                LoadingDialog.removeDialog();
                String url = result.optString("pic_verify_url_");
                String id = result.optString("id_");
                if(!TextUtils.isEmpty(url)){
                    url = url.substring(1);
                }
                StringBuffer pic_url = new StringBuffer(NetUrls.URL_IP);
                pic_url.append(url).append("?id_=").append(id).append("&oldId_=").append(oldId);
                codeAddress = pic_url.toString();
                oldId = id;
                if(!TextUtils.isEmpty(codeAddress)) {
                    if(dialog != null ) {
                        dialog.refreshCode(codeAddress);
                    }else {

                    }
                }

               if(!dialog.isShowing()) {
                   dialog.showVerifyView(codeAddress,new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           switch (v.getId()) {
                               case R.id.cancel:
                                   dialog.clearViews();
                                   dialog.dismiss();
                                   break;
                               case R.id.apply_code:
                                   applyCode();
                                   break;
                               case R.id.btn_confirm:
                                   verifyCode = dialog.getImg_et_code().getText().toString();
                                   dialog.clearViews();
                                   dialog.dismiss();
                                   LoadingDialog.loadDialog(LoginActivity.this,"正在请求,请稍候...");
                                   sendCode();
                                   break;
                               default:
                                   break;
                           }
                       }
                   });
               }


            }

            @Override
            public void success(String msg) {


            }

            @Override
            public void fail(String msg) {
                LoadingDialog.removeDialog();
            }
        });
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.but_send_code:
               if(ToolsUtils.isOpenNetwork(this)){
                   phone = et_phone.getText().toString().trim();
                   if (TextUtils.isEmpty(phone)) {
                       Toast.makeText(LoginActivity.this, "电话号码不能为空!", Toast.LENGTH_SHORT).show();
                       return;
                   } else if (!ToolsUtils.isMobile(phone)) {
                       Toast.makeText(LoginActivity.this, "电话号码输入有误!", Toast.LENGTH_SHORT).show();
                       return;
                   }
                   applyCode();
               }else{
                   Toast.makeText(LoginActivity.this,"请检查您的网络是否连接!",Toast.LENGTH_LONG).show();
               }
               break;
           case R.id.but_login:
               login();
               break;
           case R.id.tv_call:
               dialog.showHotLineDialog(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       if (view.getId() == R.id.cancel) {
                           dialog.dismiss();
                       } else if (view.getId() == R.id.tv_title) {
                           ToolsUtils.callPhone(LoginActivity.this, "4009008690");
                           dialog.dismiss();
                       }
                   }
               });
               break;
           default:
               break;
       }
    }
    private void sendCode() {
        but_code.setOnClickListener(null);
        NetUtils.getInstance().postRequest(NetUtils.build(), new BaseApi() {
            @Override
            public Observable getObservable(Retrofit retrofit) {
                SmsVerifyCode requestSerives = retrofit.create(SmsVerifyCode.class);//这里采用的是Java的动态代理模式
                return requestSerives.getSmsVerifyCode(phone,"MSG_LOGIN_",verifyCode,oldId);//传入我们请求的键值对的值
            }
        }, new NetUtils.NetUtilsListener() {
            @Override
            public void success(JSONObject result, int code) {
                MyCount count = new MyCount(60 * 1000, 1000);
                count.start();
                LoadingDialog.removeDialog();
            }

            @Override
            public void success(String msg) {

            }

            @Override
            public void fail(String msg) {
                but_code.setOnClickListener(LoginActivity.this);
                but_code.setClickable(true);
                LoadingDialog.removeDialog();
            }
        });
    }


    private void login(){
        phone = et_phone.getText().toString().trim();
        code = et_code.getText().toString().trim();
        if(TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "电话号码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!ToolsUtils.isMobile(phone)) {
            Toast.makeText(LoginActivity.this, "电话号码输入有误!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(code)) {
            Toast.makeText(this, "验证码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
           System.out.println("xiongzhu login"  );
            LoadingDialog.loadDialog(LoginActivity.this,"正在登录,请稍候...");
            NetUtils.getInstance().postRequest(NetUtils.build().successToast(true), new BaseApi() {
                @Override
                public Observable getObservable(Retrofit retrofit) {
                    LoginVo  requestSerives = retrofit.create(LoginVo.class);//这里采用的是Java的动态代理模式
                    return requestSerives.login(phone,code,"MSG_LOGIN_");//传入我们请求的键值对的值
                }
            }, new NetUtils.NetUtilsListener() {
                @Override
                public void success(JSONObject result, int code) {
                    LoadingDialog.removeDialog();
                    UserManager.getInstance().setJuid(result.optString("juid"));
                    SharedPUtils.getInstance(LoginActivity.this).setString("juid",UserManager.getInstance().getJuid());
                    UserManager.getInstance().setJoin_no(result.optString("join_no"));
                    UserManager.getInstance().setLogin(true);
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(0,0);
                }

                @Override
                public void success(String msg) {

                }

                @Override
                public void fail(String msg) {
                    LoadingDialog.removeDialog();
                }
            });



    }



    private class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            but_code.setText(l / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            but_code.setText("重发验证码");
            but_code.setOnClickListener(LoginActivity.this);
            but_code.setClickable(true);
        }
    }
}
