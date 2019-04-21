package com.chenyao.zhihu;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import supportCode.CountDownTimerUtils;


public class OtherwayActivity extends AppCompatActivity {
    Button getTestCode = null;
    TextView phoneNumber = null;
    TextView returnUser = null;
    TextView more = null;
    private PopupWindow popupWindow = null;
    private View contentView = null;
    MotionEvent event = null;

    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.otherway_to_login);
        //建立代码与控件关联
        phoneNumber = (TextView)findViewById(R.id.phoneNumber);
        phoneNumber.setOnClickListener(new LookForUnclosedWindow());
        getTestCode = (Button)findViewById(R.id.getTestCode);
        getTestCode.setOnClickListener(new GetTestCodeListener());
        returnUser = (TextView)findViewById(R.id.returnUser);
        returnUser.setOnClickListener(new ReturnUserName());
        more  =(TextView)findViewById(R.id.moreAction);
        more.setOnClickListener(new MoreAction());
    }


    public static boolean isMobileNO(String mobiles) {

        //正则表达式："[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个
        // "\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][3568]\\d{9}";
        if (mobiles.isEmpty()){
            return false;
        }else{
            return mobiles.matches(telRegex);
        }
    }



    public class GetTestCodeListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if(!TextUtils.isEmpty(phoneNumber.getText()) && isMobileNO(phoneNumber.getText().toString())) {
                CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(getTestCode, 60000, 1000);
                countDownTimerUtils.start();
            }
            else{
                new AlertDialog.Builder(OtherwayActivity.this).setTitle("提示").setMessage("     手机号输入错误！").setPositiveButton("确定", null).show();
                phoneNumber.setText("");
            }

        }
    }

    private class ReturnUserName implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(OtherwayActivity.this, LoginActivity.class);
            startActivity(intent);
            returnUser.setEnabled(false);
        }
    }

    private class MoreAction implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            show();

        }
    }
    private void show() {
        more.setEnabled(false);
        //加载弹出框的布局
        contentView = LayoutInflater.from(OtherwayActivity.this).inflate(R.layout.other_action_on_login,null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        //获得焦点
        popupWindow.setFocusable(true);
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画，指定刚才定义的style
        popupWindow.setAnimationStyle(R.style.dialog);
        if(this.getCurrentFocus() != null){
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }

    }

    public void getPasswd(View view){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }
    public void cannotLogin(View view){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://www.hao123.com"));
        startActivity(intent);
    }

    public void closeThisWindow(View view){
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            more.setEnabled(true);
        }
    }


    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        if (popupWindow != null && popupWindow.isShowing()) {
            /*
            * 点击空白位置，隐藏弹出窗口*/
            popupWindow.dismiss();
            more.setEnabled(true);
        }
        return super .onTouchEvent(event);
    }

    private class LookForUnclosedWindow implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (popupWindow != null && popupWindow.isShowing()) {
                /*
                 * 点击空白位置，隐藏弹出窗口*/
                popupWindow.dismiss();
                more.setEnabled(true);
            }
        }
    }
}
