package com.example.user.liangzi.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.liangzi.R;
import com.example.user.liangzi.constans.ToolsUtils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


/**
 * 作者：User on 2015/9/15 14:54
 */
public class CustomerDialog extends Dialog {

    public Context mContext;
    public LinearLayout ll_cancel, ll_sure;
    public Window window;
    private EditText img_et_code;
    private ImageView img_btn_code;

    public CustomerDialog(Context context) {
        super(context, R.style.transparentFrameWindowStyle);
        this.mContext = context;
        window = getWindow();
    }

    public void showHotLineDialog(View.OnClickListener onClickListener) {
        TextView cancel, tv_title;
        setContentView(R.layout.dialog_hot_line);
        cancel = (TextView) findViewById(R.id.cancel);
        tv_title = (TextView) findViewById(R.id.tv_title);
        window.setGravity(Gravity.BOTTOM);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        CustomerDialog.this.show();
        cancel.setOnClickListener(onClickListener);
        tv_title.setOnClickListener(onClickListener);
    }
    public void showShareDialog(final int number) {
        RelativeLayout share_weixin_friendster_layout, share_weixin_friends_layout, share_qq_friends_layout, share_qzone_layout, share_xinlang_weibo_layout;
        ImageView share_weixin_friendster_pic, share_weixin_friends_pic, share_qq_friends_pic, share_qzone_pic, share_xinlang_weibo_pic, share_copy_address_pic;
        TextView share_weixin_friendster_value, share_weixin_friends_value, share_qq_friends_value, share_qzone_value, share_xinlang_weibo_value, share_copy_address_value;
        Button button_cancel;
        setContentView(R.layout.share_popupmenu);
        share_weixin_friendster_layout = (RelativeLayout) findViewById(R.id.share_weixin_friendster_layout);
        share_weixin_friends_layout = (RelativeLayout) findViewById(R.id.share_weixin_friends_layout);
        share_xinlang_weibo_layout = (RelativeLayout) findViewById(R.id.share_xinlang_weibo_layout);

        share_weixin_friendster_pic = (ImageView) findViewById(R.id.share_weixin_friendster_pic);
        share_weixin_friends_pic = (ImageView) findViewById(R.id.share_weixin_friends_pic);
        share_xinlang_weibo_pic = (ImageView) findViewById(R.id.share_xinlang_weibo_pic);
        share_weixin_friendster_value = (TextView) findViewById(R.id.share_weixin_friendster_value);
        share_weixin_friends_value = (TextView) findViewById(R.id.share_weixin_friends_value);
        share_xinlang_weibo_value = (TextView) findViewById(R.id.share_xinlang_weibo_value);
        button_cancel = (Button) findViewById(R.id.button_cancel);
        window.setGravity(Gravity.BOTTOM);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.share_weixin_friends_layout:
                    case R.id.share_weixin_friends_pic:
                    case R.id.share_weixin_friends_value:
                        ToolsUtils.showShare(mContext, view, true, Wechat.NAME,number);
                        break;
                    case R.id.share_weixin_friendster_layout:
                    case R.id.share_weixin_friendster_pic:
                    case R.id.share_weixin_friendster_value:
                        ToolsUtils.showShare(mContext, view, true, WechatMoments.NAME,number);
                        break;
                    case R.id.share_xinlang_weibo_layout:
                    case R.id.share_xinlang_weibo_pic:
                    case R.id.share_xinlang_weibo_value:
                        ToolsUtils.showShare(mContext, view, true, SinaWeibo.NAME,number);
                        break;
                }
                dismiss();
            }
        };
        share_weixin_friendster_layout.setOnClickListener(onClickListener);
        share_weixin_friends_layout.setOnClickListener(onClickListener);

        share_weixin_friendster_pic.setOnClickListener(onClickListener);
        share_weixin_friends_pic.setOnClickListener(onClickListener);

        share_weixin_friendster_value.setOnClickListener(onClickListener);
        share_weixin_friends_value.setOnClickListener(onClickListener);

        button_cancel.setOnClickListener(onClickListener);


    }

    public void showVerifyView(String imagePath,View.OnClickListener onClickListener) {
        ImageView cancel;

        TextView apply_code;
        Button btn_confirm;
        setContentView(R.layout.dialog_verify_code);
        img_btn_code = (ImageView) findViewById(R.id.img_btn_code);
        img_et_code = (EditText) findViewById(R.id.img_et_code);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        cancel = (ImageView) findViewById(R.id.cancel);
        apply_code = (TextView) findViewById(R.id.apply_code);
        window.setGravity(Gravity.CENTER);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        CustomerDialog.this.show();
        cancel.setOnClickListener(onClickListener);
        apply_code.setOnClickListener(onClickListener);
        btn_confirm.setOnClickListener(onClickListener);
        x.image().bind(img_btn_code,imagePath,new ImageOptions.Builder().setConfig(Bitmap.Config.RGB_565).build());
    }


    public EditText getImg_et_code() {
        return img_et_code;
    }

    public void refreshCode(String path) {
        if(img_btn_code == null)return;
        x.image().bind(img_btn_code,path,new ImageOptions.Builder().setConfig(Bitmap.Config.RGB_565).build());
    }

    public void clearViews(){
        img_et_code = null;
        img_btn_code = null;

    }


    /**
     * 用户信息提示
     *
     * @param onClickListener
     * @param msgs
     */
    public void showChoiceDialog(View.OnClickListener onClickListener, String[] msgs) {
        TextView cancel, next_action, msg;
        LinearLayout choice_layout;
        LinearLayout ll_cancel, ll_sure;
        setContentView(R.layout.dialog_show_notice);
        ll_cancel = (LinearLayout) findViewById(R.id.ll_cancel);
        ll_sure = (LinearLayout) findViewById(R.id.ll_sure);
        TextView tv_message = (TextView) findViewById(R.id.tv_message);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        cancel = (TextView) findViewById(R.id.cancel);
        next_action = (TextView) findViewById(R.id.next_action);
        msg = (TextView) findViewById(R.id.msg);
        choice_layout = (LinearLayout) findViewById(R.id.choice_layout);
        if (msgs.length == 3) {
            tv_message.setText(msgs[0]);
            tv_title.setText(msgs[1]);
            msg.setText(msgs[2]);
            msg.setVisibility(View.VISIBLE);
            msg.setOnClickListener(onClickListener);
            choice_layout.setVisibility(View.GONE);
        } else if (msgs.length == 4) {
            tv_message.setText(msgs[0]);
            if (TextUtils.isEmpty(msgs[1])) {
                tv_title.setVisibility(View.GONE);
            } else {
                tv_title.setText(msgs[1]);
            }

            cancel.setText(msgs[2]);
            next_action.setText(msgs[3]);
            msg.setVisibility(View.GONE);
            choice_layout.setVisibility(View.VISIBLE);
        }

        window.setGravity(Gravity.CENTER);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        ll_sure.setOnClickListener(onClickListener);
        ll_cancel.setOnClickListener(onClickListener);
    }


}

