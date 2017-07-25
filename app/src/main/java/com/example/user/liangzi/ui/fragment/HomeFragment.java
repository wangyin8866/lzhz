package com.example.user.liangzi.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.liangzi.UserManager;
import com.example.user.liangzi.constans.ToolsUtils;
import com.example.user.liangzi.net.BaseApi;
import com.example.user.liangzi.net.NetUrls;
import com.example.user.liangzi.net.NetUtils;
import com.example.user.liangzi.net.injectVo.LoginOutVo;
import com.example.user.liangzi.ui.BaseActivity;
import com.example.user.liangzi.ui.BaseFragment;
import com.example.user.liangzi.R;
import com.example.user.liangzi.ui.activity.WebActivity;
import com.example.user.liangzi.ui.activity.WelcomeActivity;
import com.example.user.liangzi.ui.controller.UserInfoController;
import com.example.user.liangzi.utils.CommonUtils;
import com.example.user.liangzi.utils.SharedPUtils;
import com.example.user.liangzi.view.CustomerDialog;

import org.json.JSONObject;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;


/**
 * Created by User on 2017/4/19.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout gift_jifen_layout,mutual_certificate_layout,mutual_red_packets_layout,gift_certificate_layout,invite_friends_layout,understand_us_layout,common_questions_layout;
    private LinearLayout service_phone_layout,mutual_amount_layout,add_user_layout,invite_user_layout,help_user_layout;
    private ImageView portrait;
    private TextView name,phone,sequence,amount,user_amount,invite_amount,help_amount,top_insure,exit;
    private TextView join1,join10,join2,join3,join4,join5,join6,join7,join8,join9;
    private TextView[] joins = new TextView[11];
    private CustomerDialog dialog;


    @Override
    public void loadXml() {
      setContentView(R.layout.fragment_home);
    }

    @Override
    public void init() {
        portrait = (ImageView) rootView.findViewById(R.id.profile_image);
        mutual_certificate_layout = (RelativeLayout) rootView.findViewById(R.id.mutual_certificate_layout);
        mutual_red_packets_layout = (RelativeLayout) rootView.findViewById(R.id.mutual_red_packets_layout);
        gift_certificate_layout = (RelativeLayout) rootView.findViewById(R.id.gift_certificate_layout);
        gift_jifen_layout = (RelativeLayout) rootView.findViewById(R.id.gift_jifen_layout);
        invite_friends_layout = (RelativeLayout) rootView.findViewById(R.id.invite_friends_layout);
        understand_us_layout = (RelativeLayout) rootView.findViewById(R.id.understand_us_layout);
        common_questions_layout = (RelativeLayout) rootView.findViewById(R.id.common_questions_layout);
        service_phone_layout = (LinearLayout) rootView.findViewById(R.id.service_phone_layout);
        mutual_amount_layout = (LinearLayout) rootView.findViewById(R.id.mutual_amount_layout);
        add_user_layout = (LinearLayout) rootView.findViewById(R.id.add_user_layout);
        invite_user_layout = (LinearLayout) rootView.findViewById(R.id.invite_user_layout);
        help_user_layout = (LinearLayout) rootView.findViewById(R.id.help_user_layout);

        mutual_certificate_layout.setOnClickListener(this);
        mutual_red_packets_layout.setOnClickListener(this);
        gift_certificate_layout.setOnClickListener(this);
        gift_jifen_layout.setOnClickListener(this);
        invite_friends_layout.setOnClickListener(this);
        understand_us_layout.setOnClickListener(this);
        common_questions_layout.setOnClickListener(this);
        service_phone_layout.setOnClickListener(this);
        mutual_amount_layout.setOnClickListener(this);
        add_user_layout.setOnClickListener(this);
        invite_user_layout.setOnClickListener(this);
        help_user_layout.setOnClickListener(this);

        name = (TextView) rootView.findViewById(R.id.name);
        phone = (TextView) rootView.findViewById(R.id.phone);
        sequence = (TextView) rootView.findViewById(R.id.sequence);
        amount = (TextView) rootView.findViewById(R.id.amount);
        user_amount = (TextView) rootView.findViewById(R.id.user_amount);
        invite_amount = (TextView) rootView.findViewById(R.id.invite_amount);
        top_insure = (TextView) rootView.findViewById(R.id.top_insure);
        help_amount = (TextView) rootView.findViewById(R.id.help_amount);
        exit = (TextView) rootView.findViewById(R.id.exit);
        exit.setOnClickListener(this);

        join1 = (TextView) rootView.findViewById(R.id.join1);
        join2 = (TextView) rootView.findViewById(R.id.join2);
        join3 = (TextView) rootView.findViewById(R.id.join3);
        join4 = (TextView) rootView.findViewById(R.id.join4);
        join5 = (TextView) rootView.findViewById(R.id.join5);
        join6 = (TextView) rootView.findViewById(R.id.join6);
        join7 = (TextView) rootView.findViewById(R.id.join7);
        join8 = (TextView) rootView.findViewById(R.id.join8);
        join9 = (TextView) rootView.findViewById(R.id.join9);
        join10 = (TextView) rootView.findViewById(R.id.join10);
        joins[1] = join1;
        joins[2] = join2;
        joins[3] = join3;
        joins[4] = join4;
        joins[5] = join5;
        joins[6] = join6;
        joins[7] = join7;
        joins[8] = join8;
        joins[9] = join9;
        joins[10] = join10;

        refresh();
        dialog = new CustomerDialog(mContext);
    }

    private void refresh(){
        name.setText(UserManager.getInstance().getName());
        phone.setText(UserManager.getInstance().getMobile());
        sequence.setText(CommonUtils.matcherSearchText(getResources().getColor(R.color.orange_light),"我是第"+UserManager.getInstance().getRank()+"位加入计划的会员",String.valueOf(UserManager.getInstance().getRank())));
        amount.setText(CommonUtils.getInstance().getNumFormat().format(UserManager.getInstance().getAmount()));
        user_amount.setText(String.valueOf(UserManager.getInstance().getInsure_num()));
        invite_amount.setText(String.valueOf(UserManager.getInstance().getCount_invite()));
        help_amount.setText(UserManager.getInstance().getCount_help());
        top_insure.setText(UserManager.getInstance().getTop_insure());
        if(!TextUtils.isEmpty(UserManager.getInstance().getHead_pic())) {
            x.image().bind(portrait,UserManager.getInstance().getHead_pic(),new ImageOptions.Builder().setConfig(Bitmap.Config.RGB_565).setCircular(true).build());
        }

        if(UserManager.getInstance().getFamily_join() != null  && UserManager.getInstance().getFamily_join().length > 0) {
            for(int i = 0;i<UserManager.getInstance().getFamily_join().length ;i++) {
                if(!TextUtils.isEmpty(UserManager.getInstance().getFamily_join()[i])) {
                    joins[Integer.parseInt(UserManager.getInstance().getFamily_join()[i])].setSelected(true);
                }
            }

        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public void setData() {

    }

    @Override
    public void setOther() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.mutual_certificate_layout:
                intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("openUrl", NetUrls.H5_GUARANTEE+"?"+"juid="+ UserManager.getInstance().getJuid()+"&src=1");
                startActivity(intent);
                break;
            case R.id.mutual_red_packets_layout:
                intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("openUrl", NetUrls.H5_RED_PACKETS+"?"+"juid="+ UserManager.getInstance().getJuid()+"&src=1");
                startActivity(intent);

                break;
            case R.id.gift_certificate_layout:
                intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("openUrl", NetUrls.H5_COUPONS+"?"+"juid="+ UserManager.getInstance().getJuid()+"&src=1");
                startActivity(intent);
                break;
            case R.id.gift_jifen_layout:
                intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("openUrl", NetUrls.H5_JIFEN+"?"+"juid="+ UserManager.getInstance().getJuid()+"&src=1");
                startActivity(intent);
                break;
            case R.id.invite_friends_layout:
                intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("openUrl", NetUrls.H5_INVITE_FRIENDS+"?"+"juid="+ UserManager.getInstance().getJuid()+"&src=1");
                startActivity(intent);
                break;
            case R.id.understand_us_layout:
                intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("openUrl", NetUrls.H5_ABOUNT_QUANTUM);
                startActivity(intent);
                break;
            case R.id.common_questions_layout:
                intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("openUrl", NetUrls.H5_COMMON_QUESTIONS+"");
                startActivity(intent);
                break;
            case R.id.service_phone_layout:
                dialog.showHotLineDialog(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view.getId() == R.id.cancel) {
                            dialog.dismiss();
                        } else if (view.getId() == R.id.tv_title) {
                            ToolsUtils.callPhone(mContext, "4009008690");
                            dialog.dismiss();
                        }
                    }
                });
                break;
            case R.id.mutual_amount_layout:
                 intent = new Intent(mContext, WebActivity.class);
                 intent.putExtra("openUrl", NetUrls.H5_AMOUNT+"?"+"juid="+ UserManager.getInstance().getJuid());
                 startActivity(intent);
                break;
            case R.id.add_user_layout:
                intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("openUrl", NetUrls.H5_ADD_USER+"?"+"juid="+ UserManager.getInstance().getJuid());
                startActivity(intent);
                break;
            case R.id.invite_user_layout:
                intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("openUrl", NetUrls.H5_INVITED+"?"+"juid="+ UserManager.getInstance().getJuid());
                startActivity(intent);
                break;
            case R.id.help_user_layout:
                break;
            case R.id.exit:
                isExitDialog();
                break;

        }
    }


    private void isExitDialog() {
        dialog.showChoiceDialog(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.ll_cancel) {
                    dialog.dismiss();
                } else if (view.getId() == R.id.ll_sure) {
                    if (!UserManager.getInstance().isLogin()) return;
                    try {
                        loginOut(mContext);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new String[]{"您确认要退出登录吗？", "", "取消", "确定"});
        dialog.show();
    }


    private void loginOut(final BaseActivity context){
        NetUtils.getInstance().postRequest(NetUtils.build(), new BaseApi() {
            @Override
            public Observable getObservable(Retrofit retrofit) {
                LoginOutVo requestSerives = retrofit.create(LoginOutVo.class);//这里采用的是Java的动态代理模式
                return requestSerives.exit(UserManager.getInstance().getJuid());//传入我们请求的键值对的值
            }
        }, new NetUtils.NetUtilsListener() {
            @Override
            public void success(JSONObject result, int code) {
                dialog.dismiss();
                UserManager.clear();
                UserInfoController.clear();
                SharedPUtils.getInstance(context).setString("juid","");
                Intent intent = new Intent(context, WelcomeActivity.class);
                intent.putExtra("isLogin",true);
                context.startActivity(intent);
                context.finish();
            }

            @Override
            public void success(String msg) {

            }

            @Override
            public void fail(String msg) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshInfo();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
       if(!hidden) {
           refreshInfo();
       }
    }


    private void refreshInfo(){
        UserInfoController.setConsumer(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                refresh();
            }
        });
        UserInfoController.getUserProfile();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
