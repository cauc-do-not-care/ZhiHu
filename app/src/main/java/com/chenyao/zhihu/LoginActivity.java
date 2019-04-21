package com.chenyao.zhihu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {
    EditText userName = null;
    EditText passwd = null;
    Button login = null;
    Button register = null;
    TextView textView = null;

    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.sign_in_and_sign_up);

        //set relevance between button and code

        userName = (EditText) findViewById(R.id.editText);
        passwd = (EditText)findViewById(R.id.editText2);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new loginOnClick());
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new registerOnClick());
        textView = (TextView)findViewById(R.id.other_way);
        textView.setOnClickListener(new otherWayToLogin());
    }

    /*
     * login frame without logic judgement
     * Next we should input some logical judgement and access to the database
     * logic:
     *
     */

    public class loginOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(TextUtils.isEmpty(userName.getText())){
                new AlertDialog.Builder(LoginActivity.this).setTitle("提示").setMessage("     用户名为空！").setPositiveButton("确定", null).show();

            }
            else if(TextUtils.isEmpty(passwd.getText())){
                new AlertDialog.Builder(LoginActivity.this).setTitle("提示").setMessage( "     密码为空！").setPositiveButton("确定", null).show();

            }
            else {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                login.setEnabled(false);
            }
        }
    }

    public class registerOnClick implements View.OnClickListener {
        public void onClick(View v){
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("http://www.baidu.com"));
            startActivity(intent);
            register.setEnabled(false);

        }
    }

    public class otherWayToLogin implements View.OnClickListener{
        public void onClick(View v){
            Intent intent = new Intent(LoginActivity.this, OtherwayActivity.class);
            startActivity(intent);
            textView.setEnabled(false);
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

        return super .onTouchEvent(event);
    }

}
